package modele;

import java.util.ArrayList;

import view.I_dataListable;

public abstract class Media implements I_requeteSQL,I_dataListable,I_recherche {
	
	protected int id_media;
	protected Oeuvre oeuvre=new Oeuvre();
	protected String date_crea_media;
	protected String annee_sortie_media;
	protected Univers univers=new Univers();
	protected Saga saga=new Saga();
	protected Image image=new Image();
	protected Etat etat=new Etat();
	protected ArrayList<Artiste> artistes=new ArrayList<>();
	protected ArrayList<Genre> genres=new ArrayList<>();
//	donné à utiliser pour le tableau
	protected String concat_artistes;
	protected String concat_genre;

	public int getId_media() {
		return id_media;
	}

	private void setId_media(int id_media) {
		this.id_media = id_media;
	}

	public String getDate_crea_media() {
		return date_crea_media;
	}

	public void setDate_crea_media(String date_crea_media) {
		this.date_crea_media = date_crea_media;
	}

	public String getAnnee_sortie_media() {
		return annee_sortie_media;
	}

	public void setAnnee_sortie_media(String annee_sortie_media) {
		this.annee_sortie_media = annee_sortie_media;
	}

	public ArrayList<Artiste> getArtistes() {
		return artistes;
	}

	public void setArtistes(ArrayList<Artiste> artistes) {
		this.artistes = artistes;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Media [id_media=" + id_media + ", titre_media=" + oeuvre.getNom() + ", date_crea_media=" + date_crea_media
				+ ", annee_sortie_media=" + annee_sortie_media + ", artistes=" + artistes + ", genres=" + genres + "]";
	}
	
	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}
	public void addArtiste(Artiste artiste) {
		this.artistes.add(artiste);
	}

	public Oeuvre getOeuvre() {
		return oeuvre;
	}

	public void setOeuvre(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}
}
