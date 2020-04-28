
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Livre extends Media {

	protected Long ISBN;
	protected String resume_livre;
	protected int tome_livre;

	protected Editeur editeur=new Editeur();


	protected static ArrayList<Editeur> liste_editeur = new ArrayList<>();


	public Editeur getEditeur() {
		return editeur;
	}
	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public static ArrayList<Editeur> getListe_editeur() {
		return liste_editeur;
	}
	public static void setListe_editeur(ArrayList<Editeur> liste_editeur) {
		Livre.liste_editeur = liste_editeur;
	}

	public String[] toRowData() {
		String[] data = {this.oeuvre.getNom(), this.ISBN + "", this.editeur.getNom(), this.etat.getNom(), this.annee_sortie_media};
		return data;
	}

	public String[] toRowDataForm() {
		String[] data = { this.getTitre(), this.getISBN() + "", this.getAnnee_sortie_media(), this.saga.getNom(), this.getNomunivers(), this.getTome_livre()+ ""};
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

	public Long getISBN() {
		return ISBN;
	}

	public void setISBN(Long iSBN) {
		ISBN = iSBN;
	}


	@Override
	public String toString() {
		return "Livre [ISBN=" + ISBN + ", resume_livre=" + resume_livre + ", tome_livre=" + tome_livre + ", toString()=" + super.toString() + "]";
	}

	@Override
	public boolean creation() {
		boolean success = false;
		try {
			Connection co = ConnexionBDD.getConnection();
			String query;
			PreparedStatement statement;
			ResultSet generatedKeys;

			//creation media, univers, saga, image, oeuvre
			query = "INSERT INTO nestix_media(annee_sortie_media, admin_id, univers_id, saga_id, image_id, etat_id, oeuvre_id, type_media) \n" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			statement = (PreparedStatement) co.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 3);
			ConnexionBDD.prepareInt(statement, 3, this.univers.getId());
			ConnexionBDD.prepareInt(statement, 4, this.saga.getId());
			ConnexionBDD.prepareInt(statement, 5, this.image.getId());
			statement.setInt(6, (this.etat.getId() == 0) ? 2 : this.etat.getId());
			ConnexionBDD.prepareInt(statement, 7, this.oeuvre.getId());
			statement.setString(8, this.getType());

			success = (statement.executeUpdate() > 0);
			generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				success = true;
				this.id_media = (int) generatedKeys.getLong(1);
			}else {
				throw new SQLException("Erreur création media pour livre");
			}
			//modification livre
			if(success) {
				query = "UPDATE nestix_livre \n" + 
						"SET isbn = ?, resume_livre = ?, tome_livre = ?, editeur_id = ? \n" + 
						"WHERE livre_id = ?";
				statement = (PreparedStatement) ConnexionBDD.getConnection().prepareStatement(query);
				ConnexionBDD.prepareLong(statement, 1, this.ISBN);
				statement.setString(2, this.resume_livre);
				ConnexionBDD.prepareInt(statement, 3, this.tome_livre);
				ConnexionBDD.prepareInt(statement, 4, this.editeur.getId());
				statement.setInt(5, this.id_media);
				success = (statement.executeUpdate() > 0);
			}else {
				System.out.println("Erreur récupération id du media créé");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean modification() {
		boolean success = false;
		try {
			Connection co = ConnexionBDD.getConnection();
			String query;
			PreparedStatement statement;
			//modification media
			query = "UPDATE nestix_media \n" +
					"SET annee_sortie_media = ?, admin_id = ?, univers_id = ?, saga_id = ?, image_id = ?, etat_id = ?, oeuvre_id = ? \n" + 
					"WHERE id_media=?";
			statement = (PreparedStatement) co.prepareStatement(query);
			statement.setString(1, this.annee_sortie_media);
			statement.setInt(2, 3);
			ConnexionBDD.prepareInt(statement, 3, this.univers.getId());
			ConnexionBDD.prepareInt(statement, 4, this.saga.getId());
			ConnexionBDD.prepareInt(statement, 5, this.image.getId());
			statement.setInt(6, (this.etat.getId() == 0) ? 2 : this.etat.getId());
			ConnexionBDD.prepareInt(statement, 7, this.oeuvre.getId());
			statement.setInt(8, this.id_media);
			
			success = (statement.executeUpdate() > 0);
			
			if(success) {
				query = "UPDATE nestix_livre \n" +
						"SET isbn = ?, resume_livre = ?, tome_livre = ?, editeur_id = ? \n" +
						"WHERE livre_id = ?";
				statement = (PreparedStatement) co.prepareStatement(query);
				statement.setLong(1, this.ISBN);
				statement.setString(2, this.resume_livre);
				statement.setInt(3, this.tome_livre);
				statement.setInt(4, this.editeur.getId());
				statement.setInt(5, this.id_media);
				
				success = (statement.executeUpdate() > 0);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean lireUn(int id) {
		boolean success = true;

		try {
			Connection co = ConnexionBDD.getConnection();
			String query = "SELECT * FROM nestix_vue_media_livre WHERE livre_id = ?";

			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();

			this.id_media = result.getInt("livre_id");
			this.oeuvre.setProp(result.getInt("id_oeuvre"), result.getString("nom_oeuvre"));
			this.date_crea_media = result.getString("date_crea_media");
			this.annee_sortie_media = result.getString("annee_sortie_media").substring(0,4);
			this.image.setProp(result.getInt("id_image"), result.getString("nom_image"), result.getString("path_image"), result.getString("alt_image"));
			this.univers.setProp(result.getInt("id_univers"), result.getString("nom_univers"));
			this.saga.setProp(result.getInt("id_saga"), result.getString("nom_saga"));
			this.etat.setProp(result.getInt("id_etat"), result.getString("nom_etat"));

			//this.concat_artistes
			//this.concat_genre

			this.ISBN = result.getLong("isbn");
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
		boolean success=false;
		try {
			String query="DELETE FROM `nestix_media` WHERE id_media=?";
			PreparedStatement statement=(PreparedStatement) ConnexionBDD.getConnection().prepareStatement(query);
			statement.setInt(1, this.id_media);
			success=(statement.executeUpdate()>0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {
		ArrayList<I_recherche> livreList = new ArrayList<>();
		try {
			Connection co = ConnexionBDD.getConnection();
			String query = "SELECT * FROM nestix_vue_media_livre \n" +
							"GROUP BY\n"+
							"livre_id LIMIT ?";

			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Livre livre = new Livre();

				livre.id_media = result.getInt("livre_id");
				livre.oeuvre.setProp(result.getInt("id_oeuvre"), result.getString("nom_oeuvre"));
				//livre.date_crea_media = result.getString("date_crea_media");
				livre.annee_sortie_media = result.getString("annee_sortie_media").substring(0,4);
				//livre.image.setProp(result.getInt("id_image"), result.getString("nom_image"), result.getString("path_image"), result.getString("alt_image"));
				//livre.univers.setProp(result.getInt("id_univers"), result.getString("nom_univers"));
				//livre.saga.setProp(result.getInt("id_saga"), result.getString("nom_saga"));
				livre.etat.setProp(result.getInt("id_etat"), result.getString("nom_etat") );
				//livre.artistes
				//livre.genres
				//livre.concat_artistes
				//livre.concat_genre

				livre.ISBN = result.getLong("isbn");
				//livre.resume_livre = result.getString("resume_livre");
				//livre.tome_livre = result.getInt("tome_livre");

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

	@Override
	protected String getType() {

		return "livre";
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
