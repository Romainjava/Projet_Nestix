package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Etat extends Info{
	
	protected static ArrayList<Etat> liste_etat = new ArrayList<>();
	
	public Etat() {
		super();
	}

	public Etat(int pId, String pNom) {
		super(pId, pNom);
	}

	@Override
	protected String getTableName() {
		
		return "etat";
	}

	public static ArrayList<Etat> getListe_etat() {
		return liste_etat;
	}

	public static void setListe_etat(ArrayList<Etat> liste_etat) {
		Etat.liste_etat = liste_etat;
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
	public static ArrayList<Etat> lectureToutListe() {
		ArrayList<Etat> etatList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT id_etat, nom_etat FROM nestix_etat";
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				etatList.add(new Etat(result.getInt("id_etat"), result.getString("nom_etat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur lire tout etat liste");
		}
		
		return etatList;
	}
	public static String[] getAllNom() {
		String[] noms = new String[Etat.getListe_etat().size()];
		for(int i = 0; i < Etat.getListe_etat().size(); i++) {
			noms[i] = Etat.getListe_etat().get(i).getNom();
		}
		
		return noms;
	}
	public static int getIdInList(String pName) {
		int id = 0;
		for(Etat etat: Etat.getListe_etat()) {
			if(etat.getNom() == pName) {
				id = etat.getId();
			}
		}
		
		return id;
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

}
