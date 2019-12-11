package modele;

import java.util.ArrayList;

import view.I_dataListable;

public abstract class Media implements I_requeteSQL,I_dataListable,I_recherche {
	
	protected int id_media;
	protected String titre_media;
	protected String date_crea_media;
	protected String annee_sortie_media;
	protected Univers univers;
	protected Saga saga;
	protected String titre_image;
	protected Etat etat;
	protected ArrayList<Artiste> artistes;
	protected ArrayList<Genre> genres=new ArrayList<>();

	public int getId_media() {
		return id_media;
	}

	private void setId_media(int id_media) {
		this.id_media = id_media;
	}

	public String getTitre_media() {
		return titre_media;
	}

	public void setTitre_media(String titre_media) {
		this.titre_media = titre_media;
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

	public String getImage_id() {
		return titre_image;
	}

	public void setImage_id(String image_id) {
		this.titre_image = image_id;
	}

	@Override
	public String toString() {
		return "Media [id_media=" + id_media + ", titre_media=" + titre_media + ", date_crea_media=" + date_crea_media
				+ ", annee_sortie_media=" + annee_sortie_media + ", artistes=" + artistes + ", genres=" + genres + "]";
	}
	
	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}
	public void addArtiste(Artiste artiste) {
		this.artistes.add(artiste);
	}
}
