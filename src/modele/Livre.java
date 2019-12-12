package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Livre extends Media {
	
	protected int ISBN;
	protected String resume_livre;
	protected int tome_livre;
	protected Editeur editeur;
	
	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public String[] toRowData() {
		String[] data = {this.oeuvre.getNom(), this.ISBN + "", this.annee_sortie_media};
		return data;
	}
	
	public String[] toHeaderData() {
		String[] data = {"Titre","ISBN", "Genre", "Annee"};
		return data;
	}
	
	public String getResume_livre() {
		return resume_livre;
	}

	public void setResume_livre(String resume_livre) {
		this.resume_livre = resume_livre;
	}

	public int getTome_livre() {
		return tome_livre;
	}

	public void setTome_livre(int tome_livre) {
		this.tome_livre = tome_livre;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}


	@Override
	public String toString() {
		return "Livre [ISBN=" + ISBN + ", resume_livre=" + resume_livre + ", tome_livre=" + tome_livre + ", toString()=" + super.toString() + "]";
	}

	@Override
	public boolean creation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lireUn(int id) {
		boolean success = true;
		try {
			String query = "SELECT * FROM nestix_media JOIN nestix_livre ON id_media = livre_id WHERE id_media = " + id;
			Statement state = ConnexionBDD.getConnexion().createStatement();
			ResultSet result = state.executeQuery(query);
			
			ResultSetMetaData resultMeta = result.getMetaData();
		    System.out.println("\n**********************************");
		    for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
		      System.out.print(resultMeta.getColumnName(i).toUpperCase() + " *");
		    }
		    System.out.println("\n**********************************");
		    while(result.next()){         
		      for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
		    	  if(result.getObject(i) != null) {
		    		  System.out.print(result.getObject(i).toString() + "\t |");
		    	  }else {
		    		  System.out.print("NULL" + "\t |");
		    	  }
		      }
		      System.out.println("\n---------------------------------");
		    }
		    result.absolute(1);
		    if(result.getObject(12) != null) {
		    	System.out.println(result.getObject(12).toString());
		    	setISBN(result.getInt(12));
		    }else {
		    	System.out.println("NULL");
		    	setISBN(result.getInt(12));
		    }
		    
		    System.out.println(getISBN());
	
		    result.close();
		    state.close();
			
		} catch (Exception e) {
			System.out.println("erreur");
			success = false;
		}
		return success;
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		ArrayList<I_recherche> livreList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_oeuvre,\n" + 
					"date_crea_media, annee_sortie_media, nom_admin, nom_univers, nom_saga, nom_img, pseudo_utilisateur,\n" + 
					"livre_id, isbn, resume_livre, tome_livre, id_editeur, nom_editeur FROM nestix_livre\n" + 
					"LEFT JOIN nestix_media ON nestix_media.id_media = livre_id\n" + 
					"LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id\n" + 
					"LEFT JOIN nestix_admin ON nestix_admin.id_admin = nestix_media.admin_id\n" + 
					"LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.univers_id\n" + 
					"LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id\n" + 
					"LEFT JOIN nestix_image ON nestix_image.id_image = nestix_media.image_id\n" + 
					"LEFT JOIN nestix_utilisateur ON nestix_utilisateur.id_utilisateur = nestix_media.utilisateur_id\n"+
					"LEFT JOIN nestix_editeur ON nestix_editeur.id_editeur = nestix_livre.editeur_id\n"+
					"GROUP BY\n"+ 
					"nestix_media.id_media LIMIT ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Livre livre = new Livre();
				
				livre.id_media = result.getInt("livre_id");
				//livre.titre_media = result.getString("nom_oeuvre");
				livre.date_crea_media = result.getString("date_crea_media");
				livre.annee_sortie_media = result.getString("annee_sortie_media");
				//livre.titre_image = result.getString("nom_img");
				//livre.univers
				//livre.saga
				//livre.etat
				//livre.artistes
				//livre.genres
				//livre.concat_artistes
				//livre.concat_genre
				
				livre.ISBN = result.getInt("isbn");
				livre.resume_livre = result.getString("resume_livre");
				livre.tome_livre = result.getInt("tome_livre");
				
				livre.editeur = new Editeur(result.getInt("id_editeur"), result.getString("nom_editeur"));
				
				//livre.concat_genre=result.getString("nom_genre");
				//livre.setTitre_media(result.getString("nom_oeuvre"));
				//livre.concat_artistes=result.getString("surnom_artiste");
				//livre.etat.setNom(result.getString("nom_etat"));
				//livre.setAnnee_sortie_media(result.getString("annee_sortie_media"));
				
				livreList.add(livre);
				
				System.out.println(livre.toString());
			}
		//success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livreList;
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static void main(String[] args) {
		Livre livre = new Livre();
		
		livre.lireUn(3);
		
		livre.lectureTout(50);
		//System.out.println(livre.lectureTout(50));
		
//		ConnexionBDD.startConnection();
//		musique.setTitre_media("test");
//		Genre genre = new Genre();
//		genre.setNom_genre("rock");
//		Genre genre2 = new Genre();
//		genre2.setNom_genre("pop");
//		musique.addGenre(genre);
//		musique.addGenre(genre2);
//		ConnexionBDD.startConnection();
//		musique.setAnnee_sortie_media("1060");
//		for (String iterable_element : musique.toRowData()) {
//			System.out.println(iterable_element);
//		}
	}

}
