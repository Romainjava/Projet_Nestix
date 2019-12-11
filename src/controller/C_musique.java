package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


import modele.Musiques;
import view.AsidePanel;
import view.FooterPanel;
import view.HeaderPanel;
import view.ImageModule;
import view.LinkModule;
import view.MainPanel;
import view.Module;

public class C_musique {
	
	private JPanel musiques_panel;
	
	// Données
	Musiques musique = new Musiques();
	ArrayList<Musiques> musiques = new ArrayList<>();
	 
	// Composants
	JTable musique_results_table;
	ArrayList<JTextField> musique_titre_textfield;
	String[] header={ "Titre", "Duree","Album", "Annee de sortie" };
	
	public JTable getMusique_results_table() {
		return musique_results_table;
	}
	
	public ArrayList<JTextField> getMusique_titre_textfield() {
		return musique_titre_textfield;
	}
	

	public C_musique(JPanel musiques_panel) {
		this.musiques_panel = musiques_panel;
		
		// Faire une requete qui va  remplir la liste de livre "livres"
		
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();		
	}
	
	public void ajouteHeader() {
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel musique_header = new HeaderPanel(this.musiques_panel, "Cet onglet permet de renseigner des musiques",
				header, elmsSize);
		ArrayList<JTextField> liste = musique_header.getJtextArrray();
		this.musique_titre_textfield = liste;
	}

	public void ajoutMainPanel() {
		MainPanel musique_main = new MainPanel(this.musiques_panel);
		// Add element
		musique_main.addModule(new LinkModule("Personne"), 0, 0);
		musique_main.addModule(new Module(), 1, 0);
		musique_main.addModule(new ImageModule(), 2, 0);

		musique_main.addModule(new LinkModule("Genre"), 0, 1);
		musique_main.addModule(new Module(), 1, 1);
		musique_main.addModule(new Module(), 2, 1);
	}
	public void ajouteTab() {
		AsidePanel livres_aside_panel = new AsidePanel(this.musiques_panel);
		livres_aside_panel.setEntetes(header);
		livres_aside_panel.setDonnees(new Object[][] { { "La boussole d'or", "toto en vacance", "valide", "2010" }, });// TODO il faut le mettre dans l'attribut "livres"
		livres_aside_panel.ajouterLigne(new Object[] { "toto", "tata et toto", null, null });

		// Ajout d'un evenemment
		this.musique_results_table = livres_aside_panel.getTable_result();
		musique_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel livre_footer_panel = new FooterPanel(this.musiques_panel, textBouton, elmsSizeFooter);
	}
	
	/**
	 * Actualise le formulaire de musique
	 */
	public void actualiseMusique(String titre) {
		// Actualise le titre
		this.getMusique_titre_textfield().get(0).setText(titre);
	}
	
	
	/**
	 * Classe interne
	 * @author Romain
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter{
		
		C_musique controller;
		
		public MouseAdapterTableau(C_musique controller) {
			this.controller = controller;
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// PERMET DE RECUP LA POSITION DANS LA MATRICE DU TABLEAU
			int row = this.controller.getMusique_results_table().rowAtPoint(e.getPoint());
			// int column = tableau.columnAtPoint(e.getPoint());
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String titre = (String)this.controller.getMusique_results_table().getValueAt(row, 0);
			this.controller.actualiseMusique(titre);
			// Plus tard faire appelle à la méthode actualise livre qui actualise tous les champs
		}
	}

}
