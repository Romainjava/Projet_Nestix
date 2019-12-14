package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
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

	public int getId() {
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
	
	public Etat getEtat() {
		return this.etat;
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
	
	public String getTitre() {
		return this.oeuvre.getNom();
	}
	
	public String getNomunivers() {
		return this.univers.getNom();
	}
	
	/* setter with result*/
	public void setUnivers(String nom) {
		this.univers.setInfo(nom);
	}
	
	public void setSaga(String nom) {
		this.saga.setInfo(nom);
	}
	
	public void setOeuvre(String nom) {
		this.oeuvre.setInfo(nom);
	}
	
	public void setEtat(int id) {
		this.etat.setId(id);
	}
	
	public int getEtatId() {
		
		return this.etat.getId();
	}
	
	public void setUnivers(ResultSet result) {
		try {
			this.univers.setId(result.getInt("nestix_media.univers_id"));
			this.univers.setNom(result.getString("nom_univers"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void setImage(ResultSet result) {
		try {
			this.image.setId(result.getInt("image_id"));
			this.image.setNom(result.getString("nom_image"));
			this.image.setPath_image(result.getString("path_image"));
			this.image.setAlt_image(result.getString("alt_image"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setEtat(ResultSet result) {
		try {
			this.etat.setId(result.getInt("id_etat"));
			this.etat.setNom(result.getString("nom_etat"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setSaga(ResultSet result) {
		try {
			this.saga.setId(result.getInt("saga_id"));
			this.saga.setNom(result.getString("nom_saga"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setOeuvre(ResultSet result) {
		try {
			this.oeuvre.setId(result.getInt("oeuvre_id"));
			this.oeuvre.setNom(result.getString("nom_oeuvre"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
