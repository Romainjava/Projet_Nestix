package modele;

import java.util.ArrayList;

public class Metier extends Info {
	private Artiste artiste;
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
	 * 
	 * @param titre:String
	 * @param type:String
	 */
	public void addMedia(String titre, String type) {
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setNom(titre);

		if (type.equals("livre")) {
			Livre livre = new Livre();
			livre.setOeuvre(oeuvre);
			this.setMedia(livre);
		} else if (type.equals("film")) {
			Film film = new Film();
			film.setOeuvre(oeuvre);
			this.setMedia(film);
		} else if (type.equals("musique")) {
			Musique musique = new Musique();
			musique.setOeuvre(oeuvre);
			this.setMedia(musique);
		}

	}
}
