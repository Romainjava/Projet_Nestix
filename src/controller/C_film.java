package controller;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.C_Livre.MouseAdapterTableau;
import modele.Film;
import view.AsidePanel;
import view.FooterPanel;
import view.HeaderPanel;
import view.ImageModule;
import view.LinkModule;
import view.MainPanel;
import view.Module;

public class C_film {
	private JPanel films_panel;
	
	Film film = new Film();
	ArrayList<Film> films = new ArrayList<Film>();
	
	JTable film_results_table;
	JTextField film_titre_textfield;
	
	
	public JTable getFilm_results_table() {
		return film_results_table;
	}
	
	public JTextField getFilm_titre_textfield() {
		return film_titre_textfield;
	}
	
	public C_film(JPanel films_panel) {
		this.films_panel = films_panel;
		
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
	}
	
	public void ajouteHeader() {
		String tabHeader[] = { "Titre", "Durée", "Année de sortie", "Saga" };
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel livre_header = new HeaderPanel(this.films_panel, "Cet onglet permet de renseigner des films",
				tabHeader, elmsSize);
		ArrayList<JTextField> liste = livre_header.getJtextArrray();
		this.film_titre_textfield = liste.get(0);
	}

	public void ajoutMainPanel() {
		MainPanel livre_main = new MainPanel(this.films_panel);
		// Add element
		livre_main.addModule(new LinkModule("Personne"), 0, 0);
		livre_main.addModule(new Module(), 1, 0);
		livre_main.addModule(new ImageModule(), 2, 0);

		livre_main.addModule(new LinkModule("Genre"), 0, 1);
		livre_main.addModule(new Module(), 1, 1);
		livre_main.addModule(new Module(), 2, 1);
	}
	public void ajouteTab() {
		AsidePanel livres_aside_panel = new AsidePanel(this.films_panel);
		livres_aside_panel.setEntetes( new String[] { "Titre", "ISBN", "Genre", "Etat", "Annee" });
		livres_aside_panel.setDonnees(new Object[][] { { "La boussole d'or", "toto en vacance", "valide", "2010" }, });// TODO il faut le mettre dans l'attribut "livres"
		livres_aside_panel.ajouterLigne(new Object[] { "toto", "tata et toto", null, null });

		// Ajout d'un evenemment
		//this.film_results_table = films_aside_panel.getTable_result();
		//film_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel livre_footer_panel = new FooterPanel(this.films_panel, textBouton, elmsSizeFooter);
	}
}
