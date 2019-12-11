package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GlobalRequete {
	
	protected static int creaRapide(String colonne,String table,String attrNom,String value) {
		int id=0;
		PreparedStatement statement;
		ResultSet result;
		String query;
		try {
			query="SELECT "+colonne.toLowerCase()+" FROM "+table.toLowerCase()+" WHERE "+attrNom.toLowerCase()+"=?";
			statement = (PreparedStatement) ConnexionBDD.startConnection().prepareStatement(query);
			statement.setString(1, value.toLowerCase());
			result = statement.executeQuery();
			if(result.next()) {
				id= result.getInt(1);
			}else {
				query="INSERT INTO "+table+"("+attrNom+") VALUES(?)";				
				statement = (PreparedStatement) ConnexionBDD.startConnection().prepareStatement(query);
				statement.setString(1, value);
				result = statement.executeQuery();
				id=creaRapide(colonne, table, attrNom, value);
			}
			ConnexionBDD.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
