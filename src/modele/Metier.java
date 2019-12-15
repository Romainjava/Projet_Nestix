package modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import requete.M_media;

public class Metier extends Info {
	private Artiste artiste = new Artiste();
	private Media media;

	public Metier() {
		super();
	}

	public Metier(int pId, String pNom) {
		super(pId, pNom);
	}

	@Override
	public boolean suppression(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return  this.nom+ " : "+ media.getTitre() + " (" + media.getType() + ")";
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
	protected String getTableName() {
		return "metier";
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	/**
	 * Permet d'instancier le bon media en fonction du type mis en parametre
	 * Recupere l'id du media avec le fetchId
	 * @param titre:String
	 * @param type:String
	 */
	public void addMedia(String titre, String type) {
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setNom(titre);
		type = type.toLowerCase();
		int id = M_media.fetchId(titre, type);
		if (type.equals("livre")) {
			Livre livre = new Livre();
			livre.setOeuvre(oeuvre);
			livre.setId_media(id);
			this.setMedia(livre);
		} else if (type.equals("film")) {
			Film film = new Film();
			film.setOeuvre(oeuvre);
			film.setId_media(id);
			this.setMedia(film);
		} else if (type.equals("musique")) {
			Musique musique = new Musique();
			musique.setOeuvre(oeuvre);
			musique.setId_media(id);
			this.setMedia(musique);
		}else {
			JOptionPane.showMessageDialog(null, "erreur de type" + type);
		}
		
	}
}
