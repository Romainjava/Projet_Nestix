package modele;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import requete.M_artiste;


public class Artiste implements I_requeteSQL, I_recherche {

	private int id_artiste;
	private String nom_artiste;
	private String prenom_artiste;
	private String surnom_artiste;
	private String dob_artiste;
	private ArrayList<Metier> metiers_artiste = new ArrayList<>();
	private Metier metier;
	private String etat;
	private String group_concact;
	
	
	/**
	 * Fonction qui va verifier le format de la date avec une regex 
	 * @return boolean
	 */
	public boolean verifDateForm() {
		boolean reponse = false;
		if(dob_artiste.equals("")) {
			reponse = true;
		}else {
			Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
			Matcher matcher = pattern.matcher(dob_artiste);
			reponse = matcher.matches();
		}
		return reponse;
	}
	
	@Override
	public String toString() {
		return "Artiste [id_artiste=" + id_artiste + ", nom_artiste=" + nom_artiste + ", prenom_artiste="
				+ prenom_artiste + ", surnom_artiste=" + surnom_artiste + ", dob_artiste=" + dob_artiste + "]";
	}

	@Override
	public boolean creation() {
		return M_artiste.creation(this);
	}
	
	/**
	 * Permet de faire une creation rapide  par rapport à l'attribut surnom de l'objet
	 */
	public void creationRapide() {
		M_artiste.creationRapide(this);
	}
	
	/**
	 * Permet de faire une creation rapide via le surnom en parametre
	 * @param nom:String
	 */
	public void creationRapide(String surnom) {
		this.setSurnom_artiste(surnom);
		M_artiste.creationRapide(this);
	}
	
	@Override
	public boolean modification() {
		return (M_artiste.modifier(this) > 0);
	}

	@Override
	public boolean lireUn(int id) {
		return (M_artiste.lireUn(this));
	}

	@Override
	public boolean suppression(int id) {

		return (M_artiste.supprime(this));
	}

	@Override
	public ArrayList<I_recherche> lectureTout(int limit) {

		return M_artiste.lireTout(limit);
	}

	@Override
	/**
	 * Affiche dynamiquement les données de l'instance artiste ligne par ligne
	 */
	public Object[] toRowData() {
		Object[] tab = { this.surnom_artiste,this.group_concact, this.etat, this.dob_artiste };
		return tab;
	}

	@Override
	/**
	 * Affiche les entêtes des textfield
	 */
	public String[] toHeaderData() {
		return new String[] { "Nom", "Prenom", "Surnom", "Etat", "Date de naissance" };
	}

	@Override
	public boolean recherchePar(int limit) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rechercheParMetier() {
		//TODO	à coder
		return false;
	}

	public void getAllMetierById(int id) {
		M_artiste.getAllMetierById(this, id);

	}

	
	// ==== MUTATEUR && ACCESSEURS ==== //
	
	public int getId_artiste() {
		return id_artiste;
	}

	public int getId() {
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

	public void setMetiers_artiste(Metier metiers_artiste) {
		this.metiers_artiste.add(metiers_artiste);
	}

	public String getGroup_concact() {
		return group_concact;
	}

	public void setGroup_concact(String group_concact) {
		this.group_concact = group_concact;
	}


}
