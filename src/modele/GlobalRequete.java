package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GlobalRequete {
	
	protected static int creaRapide(String nomColonne,String table,String nomId,String value) {
		int id=0;
		PreparedStatement statement;
		ResultSet result;
		String query;
		try {
			query="SELECT "+nomId.toLowerCase()+" FROM nestix_"+table.toLowerCase()+" WHERE "+nomColonne.toLowerCase()+"=?";
			statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setString(1, value.toLowerCase());
			result = statement.executeQuery();
			if(result.next()) {
				id= result.getInt(1);
			}else {
				query="INSERT INTO nestix_"+table+"("+nomId+") VALUES(?)";				
				statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
				statement.setString(1, value);
				result = statement.executeQuery();
				
				id=creaRapide(nomColonne,table, nomId, value);
			}
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	protected static String getNameById(String nomColonne,String table,String nomId,int id) {
		PreparedStatement statement;
		ResultSet result;
		String nom=null;
		String query;
		try {
			query="SELECT "+nomColonne.toLowerCase()+" FROM nestix_"+table.toLowerCase()+" WHERE "+nomId.toLowerCase()+"=?";
			statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			if(result.next()) {
				nom= result.getString(1);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nom;
	}

}
