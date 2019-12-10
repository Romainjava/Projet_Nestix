package modele;

import java.util.ArrayList;

import view.I_dataListable;

public abstract class Media implements I_requeteSQL,I_dataListable,I_recherche {
	
	private int id_media;
	private String titre_media;
	private String date_crea_media;
	private String annee_sortie_media;
	private ArrayList<Artiste> artistes;
	private ArrayList<Genre> genres;

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

	@Override
	public String toString() {
		return "Media [id_media=" + id_media + ", titre_media=" + titre_media + ", date_crea_media=" + date_crea_media
				+ ", annee_sortie_media=" + annee_sortie_media + ", artistes=" + artistes + ", genres=" + genres + "]";
	}
	
}
