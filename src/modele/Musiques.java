package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class Musiques extends Media {

	private int duree_musique;
	private String nom_album;

	public int getDuree_musique() {
		return duree_musique;
	}

	public void setDuree_musique(int duree_musique) {
		this.duree_musique = duree_musique;
	}

	public String getAlbum_musique() {
		return nom_album;
	}

	public void setAlbum_musique(String album_musique) {
		this.nom_album = album_musique;
	}

	@Override
	public String[] toRowData() {
		String[] data = { this.oeuvre.getNom(), this.concat_genre,this.concat_artistes,this.etat.getNom(),this.annee_sortie_media };
		return data;
	}

	@Override
	public String[] toHeaderData() {
		String[] data = { "Titre", "Genre", "Interprete", "Etat","Date de sortie" };
		return data;
	}

	@Override
	public boolean creation() {
		boolean success=false;
		int id_oeuvre=0;
			id_oeuvre=0;
		 System.out.println(id_oeuvre);
		try {
			
			String query = "INSERT INTO nestix_media(annee_sortie_media,admin_id,oeuvre_id) VALUES(?,?,?)";
			PreparedStatement statement = (PreparedStatement) ConnexionBDD.getConnexion().prepareStatement(query);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 4);
			statement.setInt(3, id_oeuvre);
			success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean modification() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void fetchGenre() {
		
	}
	private void fetchArtiste() {
		
	}
	@Override
	public boolean lireUn(int id) {
		boolean sucess=false;
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query="SELECT\r\n" + 
					"    id_media,\r\n" + 
					"    annee_sortie_media,\r\n" + 
					"    admin_id,\r\n" + 
					"    nestix_media.univers_id,\r\n" + 
					"    nom_univers,\r\n" + 
					"    saga_id,\r\n" + 
					"    nom_saga,\r\n" + 
					"    image_id,\r\n" + 
					"    path_image,\r\n" + 
					"    nom_image,\r\n" + 
					"    alt_image,\r\n" + 
					"    utilisateur_id,\r\n" + 
					"    nom_oeuvre,\r\n" + 
					"    id_etat,\r\n" + 
					"    nom_etat,\r\n" + 
					"    oeuvre_id\r\n" + 
					"FROM\r\n" + 
					"    `nestix_media`\r\n" + 
					"LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.oeuvre_id\r\n" + 
					"LEFT JOIN nestix_etat ON nestix_media.etat_id = nestix_etat.id_etat\r\n" + 
					"LEFT JOIN nestix_univers ON nestix_univers.id_univers = nestix_media.id_media\r\n" + 
					"LEFT JOIN nestix_saga ON nestix_saga.id_saga = nestix_media.saga_id\r\n" + 
					"LEFT JOIN nestix_image ON nestix_image.id_image=nestix_media.image_id\r\n" + 
					"WHERE\r\n" + 
					"    id_media = ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				sucess=true;
				this.id_media=result.getInt("id_media");
				this.annee_sortie_media=result.getString("annee_sortie_media");
				this.univers.setId(result.getInt("nestix_media.univers_id"));
				this.univers.setNom(result.getString("nom_univers"));
				this.saga.setId(result.getInt("saga_id"));
				this.saga.setNom(result.getString("nom_saga"));
				this.image.setId(result.getInt("image_id"));
				this.image.setNom(result.getString("nom_image"));
				this.image.setPath_image(result.getString("path_image"));
				this.image.setAlt_image(result.getString("alt_image"));
				this.etat.setId(result.getInt("id_etat"));
				this.etat.setNom(result.getString("nom_etat"));				
				
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return sucess;
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		ArrayList<I_recherche> musiqueList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = "SELECT\r\n" + 
					"		duree_musique, \r\n" + 
					"		annee_sortie_media, \r\n" + 
					"		id_media, \r\n" + 
					"		nom_oeuvre,\r\n" + 
					"		GROUP_CONCAT(DISTINCT surnom_artiste)AS surnom_artiste, \r\n" + 
					"		GROUP_CONCAT(nom_genre)AS nom_genre,\r\n" + 
					"		id_genre,\r\n" + 
					"		nom_genre,\r\n" + 
					"		nom_etat\r\n" + 
					"FROM \r\n" + 
					"		`nestix_musique` \r\n" + 
					"LEFT JOIN nestix_media ON nestix_media.id_media = media_id \r\n" + 
					"LEFT JOIN nestix_oeuvre ON nestix_oeuvre.id_oeuvre = nestix_media.id_media\r\n" + 
					"LEFT JOIN nestix_artiste_metier_media ON nestix_artiste_metier_media.media_id = nestix_media.id_media\r\n" + 
					"LEFT JOIN nestix_artiste ON nestix_artiste.id_artiste = nestix_artiste_metier_media.artiste_id \r\n" + 
					"LEFT JOIN nestix_media_genre ON nestix_media.id_media = nestix_media_genre.media_id\r\n" + 
					"LEFT JOIN nestix_genre ON nestix_media_genre.genre_id = nestix_genre.id_genre \r\n" + 
					"LEFT JOIN nestix_etat ON nestix_media.etat_id=nestix_etat.id_etat \r\n" + 
					"GROUP BY\r\n" + 
					" nestix_media.id_media LIMIT ?";
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Musiques musique=new Musiques();
				musique.id_media=result.getInt("id_media");
				musique.concat_genre=result.getString("nom_genre");
				musique.oeuvre.setNom(result.getString("nom_oeuvre"));
				musique.concat_artistes=result.getString("surnom_artiste");
				musique.etat.setNom(result.getString("nom_etat"));
				musique.setAnnee_sortie_media(result.getString("annee_sortie_media"));
				musiqueList.add(musique);
			}
			//success = (statement.executeUpdate()>1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musiqueList;
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		Musiques musique= new Musiques();
//		musique.setoeuvre.getNom()("test");
//		Genre genre = new Genre();
//		genre.setNom("romann");
//		genre.getId();
//		System.out.println(genre);
//		genre.setNom("Roman");
//		genre.modification();
//		System.out.println(genre);
		ArrayList<I_recherche> testArrayList=musique.lectureTout(50);
		System.out.println(musique.lireUn(2));
//		Genre genre2 = new Genre();
//		genre2.setNom_genre("pop");
//		musique.addGenre(genre);
//		musique.addGenre(genre2);
//		ConnexionBDD.startConnection();
//		musique.setAnnee_sortie_media("1060");
//		for (String iterable_element : musique.toRowData()) {
//			System.out.println(iterable_element);
//		}
//		System.out.println(GlobalRequete.creaRapide("id_oeuvre", "nestix_oeuvre","nom_oeuvre", "pour que tu m'aime encore"));
//		musique.addGenre(genre);
//		musique.setoeuvre.getNom()("Agnes veux ca aussoi");
//		musique.setAnnee_sortie_media("2019");
//		musique.saga.setNom_saga("bref");
//		System.out.println(musique.creation());
		
	}

	@Override
	public String toString() {
		return "Musiques [duree_musique=" + duree_musique + ", nom_album=" + nom_album +super.toString()+ "]";
	}
}
