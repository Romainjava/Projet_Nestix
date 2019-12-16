package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Musiques.Query;

public class Film extends Media {
	
	private String resume_film;
	private int duree_film;

	public String getResume_film() {
		return resume_film;
	}

	public void setResume_film(String resume_film) {
		this.resume_film = resume_film;
	}

	public int getDuree_film() {
		return duree_film;
	}

	public void setDuree_film(int duree_film) {
		this.duree_film = duree_film;
	}
	@Override
	public String toString() {
		return "Film [resume_film=" + resume_film + ", duree_film=" + duree_film + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public String[] toRowData() {
		String[] data = { this.oeuvre.getNom(), this.concat_genre, this.concat_artistes, this.etat.getNom(),
				this.annee_sortie_media.substring(0,4) };
		return data;
	}

	@Override
	public String[] toHeaderData() {
		String[] data = { "Titre", "Genre", "realisateur", "Date de sortie", "Etat" };
		return data;
	}
	
	private void fetchGenre(int id) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = Query.queryFetchGenre();
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			this.getGenres().clear();
			while (result.next()) {
				Genre genre = new Genre();
				genre.setInfo(result);
				this.addGenre(genre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fetchArtiste(int id) {
		try {
			Connection co = ConnexionBDD.getConnexion();
			String query = Query.queryFetchArtiste();
			PreparedStatement statement = (PreparedStatement) co.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			this.getArtistes().clear();
			while (result.next()) {
				Artiste artiste = new Artiste();
				artiste.setSurnom_artiste(result.getString("surnom_artiste"));
				artiste.setId_artiste(result.getInt("artiste_id"));
				artiste.getAllMetierById(id);
				this.addArtiste(artiste);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean creation() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean lireUn(int id) {
		// TODO Auto-generated method stub
		return false;
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
	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modification() {
		// TODO Auto-generated method stub
		return false;
	}

}
