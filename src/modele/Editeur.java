package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Editeur extends Info{
	
	public Editeur() {
		super();
	}

	public Editeur(int pId, String pNom) {
		super(pId, pNom);
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String[] lectureTout() {
		ArrayList<String> editeurList = new ArrayList<>();
		editeurList.add("");
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_editeur FROM nestix_editeur";
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				editeurList.add(result.getString("nom_editeur"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur lire tout editeur String");
		}
		
		return editeurList.toArray(new String[0]);
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getTableName() {
		return "editeur";
	}
	
	

}
