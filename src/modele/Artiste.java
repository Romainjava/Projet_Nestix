package modele;

import java.util.ArrayList;

public class Artiste implements I_requeteSQL, I_recherche {

	private int id_artiste;
	private String nom_artiste;
	private String prenom_artiste;
	private String surnom_artiste;
	private String dob_artiste;
	private ArrayList<Metier> metiers_artiste;
	private Metier metier;
	private String etat;

	public int getId_artiste() {
		return id_artiste;
	}

	public void setId_artiste(int id_artiste) {
		this.id_artiste = id_artiste;
	}

	public String getNom_artiste() {
		return nom_artiste;
	}

	public void setNom_artiste(String nom_artiste) {
		this.nom_artiste = nom_artiste;
	}

	public String getPrenom_artiste() {
		return prenom_artiste;
	}

	public void setPrenom_artiste(String prenom_artiste) {
		this.prenom_artiste = prenom_artiste;
	}

	public String getSurnom_artiste() {
		return surnom_artiste;
	}

	public void setSurnom_artiste(String surnom_artiste) {
		this.surnom_artiste = surnom_artiste;
	}

	public String getDob_artiste() {
		return dob_artiste;
	}

	public void setDob_artiste(String dob_artiste) {
		this.dob_artiste = dob_artiste;
	}

	public Metier getArtiste() {
		return metier;
	}

	public void setArtiste(Metier artiste) {
		this.metier = artiste;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public ArrayList<Metier> getMetiers_artiste() {
		return metiers_artiste;
	}

	public void setMetiers_artiste(ArrayList<Metier> metiers_artiste) {
		this.metiers_artiste = metiers_artiste;
	}

	@Override
	public String toString() {
		return "Artiste [id_artiste=" + id_artiste + ", nom_artiste=" + nom_artiste + ", prenom_artiste="
				+ prenom_artiste + ", surnom_artiste=" + surnom_artiste + ", dob_artiste=" + dob_artiste + "]";
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

		return M_artiste.lireTout(limit);
	}

	@Override
	public Object[] toRowData() {
		Object[] tab = { this.nom_artiste, this.prenom_artiste, this.surnom_artiste, this.etat, this.dob_artiste };
		return tab;
	}

	@Override
	public String[] toHeaderData() {
		return new String[] { "Nom", "Prenom", "Surnom", "Etat", "Date de naissance" };
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rechercheParMetier() {
//		à coder
		return false;
	}

}
