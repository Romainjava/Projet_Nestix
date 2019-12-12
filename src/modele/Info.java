package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Info implements I_requeteSQL, I_recherche {


	protected int id;
	protected String nom;
	
	public Info() {
		
	}
	public Info(int pId, String pNom) {
		this.id = pId;
		this.nom = pNom;
	}

	public int getId() {
		if (this.id == 0) {
			PreparedStatement statement;
			ResultSet result;
			String query;
			try {
				if(this.nom==null) {
					throw new Exception("nom obligatoire");
				}
				query = "SELECT " + this.getIdName() + " FROM nestix_" + this.getTableName() + " WHERE "
						+ this.getColumnName() + "=?";
				System.out.println(query);
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				statement.setString(1, this.nom);
				result = statement.executeQuery();
				if(result.getRow() == 0) {
					System.out.println("id non trouvé class info ligne 32");
				}
				if (result.next()) {
					this.id = result.getInt(1);
				}
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		if (this.nom == null) {
			PreparedStatement statement;
			ResultSet result;
			String query;
			try {
				if(this.id==0) {
					throw new Exception("id obligatoire");
				}
				query = "SELECT " + this.getColumnName() + " FROM nestix_" + this.getTableName() + " WHERE "
						+ this.getIdName() + "=?";
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				statement.setInt(1, this.id);
				result = statement.executeQuery();
				if (result.next()) {
					this.nom = result.getString(1);
				}
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	@Override
	public String toString() {
		return this.getTableName() + " [id=" + id + ", nom=" + nom + "]";
	}
	public String toStringCustom() {
		return nom ;
	}

	public boolean creation() {
		int id = 0;
		String value = this.getNom();
		PreparedStatement statement;
		ResultSet result;
		String query;
		try {
			query = "INSERT IGNORE INTO nestix_" + this.getTableName() + "(" + this.getColumnName() + ") VALUES(?)";
			statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setString(1, value);
			result = statement.executeQuery();
			if (result.next()) {
				id = result.getInt(1);
				this.id = id;
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (id > 0);
	}

	public boolean lireUn(int id) {
		PreparedStatement statement;
		ResultSet result;
		String query;
		try {
			query = "SELECT " + this.getColumnName() + " FROM nestix_" + this.getTableName() + " WHERE "
					+ this.getIdName() + "=?";
			statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			if (result.next()) {
				this.nom = result.getString(1);
				this.id = id;
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (this.nom != null);
	}

	public boolean modification() {
		PreparedStatement statement;
		int result = 0;
		String query;
		try {
			if (this.id == 0) {
				throw new Exception("id obligatoire");
			}
			query = "UPDATE nestix_" + this.getTableName() + " SET " + this.getColumnName() + "=? WHERE "
					+ this.getIdName() + "=?";
			statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setString(1, this.nom);
			statement.setInt(2, this.id);
			result = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (result > 0);

	}
	public boolean supression() {
		//TODO
		return false;
	}
	abstract protected String getTableName();

	protected String getColumnName() {
		return "nom_" + this.getTableName();
	}

	protected String getIdName() {
		return "id_" + this.getTableName();
	}
	
}
