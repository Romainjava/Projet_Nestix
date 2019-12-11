package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Info implements I_requeteSQL,I_recherche {

	@Override
	public String toString() {
		return this.getTableName()+" [id=" + id + ", nom=" + nom + "]";
	}
	protected int id;
	protected String nom;
	
	public int getId() {
		return (this.nom!=null&&this.id==0)?GlobalRequete.creaRapide(this.getColumnName(),this.getTableName(),this.getIdName(), this.nom):this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return (this.nom==null&&this.id!=0)?GlobalRequete.getNameById(this.getColumnName(),this.getTableName(),this.getIdName(), this.id):this.nom;

	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public boolean creation() {
			int id=0;
			String value = this.getNom();
			PreparedStatement statement;
			ResultSet result;
			String query;
			try {
				query="INSERT INTO nestix_"+this.getTableName()+"("+this.getColumnName()+") VALUES(?)";	
				statement = (PreparedStatement) ConnexionBDD.startConnection().prepareStatement(query);
				statement.setString(1, value);
				result = statement.executeQuery();	
				if(result.next()) {
					id= result.getInt(1);
				}
				statement.close();
				ConnexionBDD.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.id=id;
		return (id>0);
	}
	public boolean lireUn(int id) {
		PreparedStatement statement;
		ResultSet result;
		String query;
		try {
			query="SELECT "+this.getColumnName()+this.getIdName()+" FROM nestix_"+this.getTableName()+" WHERE "+this.getIdName()+"=?";
			statement = (PreparedStatement) ConnexionBDD.startConnection().prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			if(result.next()) {
				this.nom= result.getString(1);
			}
			statement.close();
			ConnexionBDD.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (this.nom!=null);
	}
	abstract protected String getTableName();
	protected String getColumnName() {
		return "nom_"+this.getTableName();
	}
	protected String getIdName() {
		return "id_"+this.getTableName();
	}
}
