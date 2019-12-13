package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Etat extends Info{

	@Override
	protected String getTableName() {
		
		return "etat";
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		ArrayList<I_recherche> etatList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT id_etat, nom_etat FROM nestix_etat\n" +
						"GROUP BY\n"+ 
						"nestix_etat.id_etat LIMIT ?";
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Etat etat = new Etat();
				
				etat.id = result.getInt("id_etat");
				etat.nom = result.getString("nom_etat");
				
				etatList.add(etat);
				
				//System.out.println(etat.toString());
			}
		//success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur lire tout etat");
		}
		return etatList;
	}
	
	public static String[] lectureTout() {
		ArrayList<String> etatList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_etat FROM nestix_etat";
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				etatList.add(result.getString("nom_etat"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur lire tout etat String");
		}
		
		return etatList.toArray(new String[0]);
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

}
