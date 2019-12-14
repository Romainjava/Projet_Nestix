package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String[] data = {this.oeuvre.getNom(), this.ISBN + "", this.editeur.getNom(), this.etat.getNom(), this.annee_sortie_media};
		return data;
	}
	
	public String[] toRowDataForm() {
		String[] data = { this.getTitre(), this.getISBN() + "", this.getAnnee_sortie_media(), this.saga.getNom(), this.getNomunivers()};
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
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT nom_oeuvre, id_oeuvre\n" + 
					"date_crea_media, annee_sortie_media, nom_admin, pseudo_utilisateur,\n" + 
					"id_univers, nom_univers, " + "id_saga, nom_saga,\n" +
					"id_image, nom_image, path_image, alt_image,\n" + 
					"id_etat, nom_etat,\n" + 
					"livre_id, isbn, resume_livre, tome_livre, id_editeur, nom_editeur FROM nestix_livre\n" + 
					
					"LEFT JOIN nestix_media ON nestix_media.id_media = livre_id\n" + 
					"LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id\n" + 
					"LEFT JOIN nestix_admin ON nestix_admin.id_admin = nestix_media.admin_id\n" + 
					"LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.univers_id\n" + 
					"LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id\n" + 
					"LEFT JOIN nestix_image ON nestix_image.id_image = nestix_media.image_id\n" + 
					"LEFT JOIN nestix_utilisateur ON nestix_utilisateur.id_utilisateur = nestix_media.utilisateur_id\n"+
					"LEFT JOIN nestix_editeur ON nestix_editeur.id_editeur = nestix_livre.editeur_id\n"+
					"LEFT JOIN nestix_etat ON nestix_etat.id_etat = nestix_media.etat_id\n"+
					
					"WHERE livre_id =  ?";
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			
			this.id_media = result.getInt("livre_id");
			this.oeuvre.setProp(result.getInt("id_oeuvre"), result.getString("nom_oeuvre"));
			this.date_crea_media = result.getString("date_crea_media");
			this.annee_sortie_media = result.getString("annee_sortie_media");
			this.image.setProp(result.getInt("id_image"), result.getString("nom_image"), result.getString("path_image"), result.getString("alt_image"));
			this.univers.setProp(result.getInt("id_univers"), result.getString("nom_univers"));
			this.saga.setProp(result.getInt("id_saga"), result.getString("nom_saga"));
			this.etat.setProp(result.getInt("id_etat"), result.getString("nom_etat"));
			
			//this.concat_artistes
			//this.concat_genre
			
			this.ISBN = result.getInt("isbn");
			this.resume_livre = result.getString("resume_livre");
			this.tome_livre = result.getInt("tome_livre");
			
			this.editeur = new Editeur(result.getInt("id_editeur"), result.getString("nom_editeur"));
			
			//Genre
			this.genres.clear();
			query = "SELECT id_genre, nom_genre FROM nestix_media_genre\n" + 
					"LEFT JOIN nestix_genre ON nestix_genre.id_genre = genre_id \n" +
					"WHERE media_id = ?";
			
			statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				Genre tGenre = new Genre();
				tGenre.setProp(result.getInt("id_genre"), result.getString("nom_genre"));
				this.genres.add(tGenre);
			}
			//Artiste
			this.artistes.clear();
			query = "SELECT DISTINCT id_artiste, surnom_artiste FROM nestix_artiste_metier_media\n" + 
					"LEFT JOIN nestix_artiste ON nestix_artiste.id_artiste = artiste_id \n" +
					"WHERE media_id = ?";
			
			statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				Artiste tArtiste = new Artiste();
				tArtiste.setId_artiste(result.getInt("id_artiste"));
				tArtiste.setSurnom_artiste(result.getString("surnom_artiste"));
				this.artistes.add(tArtiste);
			}
			//Metier
			for(Artiste artiste: artistes) {
				artiste.getAllMetierById(this.id_media);
//				artiste.getMetiers_artiste().clear();
//				query = "SELECT id_metier, nom_metier FROM nestix_artiste_metier_media\n" + 
//						"LEFT JOIN nestix_metier ON nestix_metier.id_metier = metier_id \n" +
//						"WHERE artiste_id = ?";
//				
//				statement = (PreparedStatement) co.prepareStatement(query);
//				statement.setInt(1, artiste.getId());
//				result = statement.executeQuery();
//				while (result.next()) {
//					artiste.setMetiers_artiste(new Metier(result.getInt("id_artiste"), result.getString("surnom_artiste")));
//				}
			}
			
			//livre.concat_genre=result.getString("nom_genre");
			//livre.concat_artistes=result.getString("surnom_artiste");
			
			
			//System.out.println(this.toString());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur lire un livre");
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
			String query = "SELECT nom_oeuvre, id_oeuvre\n" + 
					"date_crea_media, annee_sortie_media, nom_admin, pseudo_utilisateur,\n" + 
					"id_univers, nom_univers, " + "id_saga, nom_saga,\n" +
					"id_image, nom_image, path_image, alt_image,\n" + 
					"id_etat, nom_etat,\n" + 
					"livre_id, isbn, resume_livre, tome_livre, id_editeur, nom_editeur FROM nestix_livre\n" + 
					
					"LEFT JOIN nestix_media ON nestix_media.id_media = livre_id\n" + 
					"LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id\n" + 
					"LEFT JOIN nestix_admin ON nestix_admin.id_admin = nestix_media.admin_id\n" + 
					"LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.univers_id\n" + 
					"LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id\n" + 
					"LEFT JOIN nestix_image ON nestix_image.id_image = nestix_media.image_id\n" + 
					"LEFT JOIN nestix_utilisateur ON nestix_utilisateur.id_utilisateur = nestix_media.utilisateur_id\n"+
					"LEFT JOIN nestix_editeur ON nestix_editeur.id_editeur = nestix_livre.editeur_id\n"+
					"LEFT JOIN nestix_etat ON nestix_etat.id_etat = nestix_media.etat_id\n"+
					
					"GROUP BY\n"+ 
					"nestix_media.id_media LIMIT ?";
			
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Livre livre = new Livre();
				
				livre.id_media = result.getInt("livre_id");
				livre.oeuvre.setProp(result.getInt("id_oeuvre"), result.getString("nom_oeuvre"));
				livre.date_crea_media = result.getString("date_crea_media");
				livre.annee_sortie_media = result.getString("annee_sortie_media");
				livre.image.setProp(result.getInt("id_image"), result.getString("nom_image"), result.getString("path_image"), result.getString("alt_image"));
				livre.univers.setProp(result.getInt("id_univers"), result.getString("nom_univers"));
				livre.saga.setProp(result.getInt("id_saga"), result.getString("nom_saga"));
				livre.etat.setProp(result.getInt("id_etat"), result.getString("nom_etat") );
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
				
				//System.out.println(livre.toString());
			}
		//success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur lire tout livre");
		}
		return livreList;
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
//	public static void main(String[] args) {
//		Livre livre = new Livre();
//		
//		livre.lireUn(3);
//		
//		livre.lectureTout(50);
//		//System.out.println(livre.lectureTout(50));
//		
////		ConnexionBDD.startConnection();
////		musique.setTitre_media("test");
////		Genre genre = new Genre();
////		genre.setNom_genre("rock");
////		Genre genre2 = new Genre();
////		genre2.setNom_genre("pop");
////		musique.addGenre(genre);
////		musique.addGenre(genre2);
////		ConnexionBDD.startConnection();
////		musique.setAnnee_sortie_media("1060");
////		for (String iterable_element : musique.toRowData()) {
////			System.out.println(iterable_element);
////		}
//	}

}

