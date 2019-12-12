package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.I_recherche;
import modele.Musiques;
import view.AsidePanel;
import view.ComboListField;
import view.DualLinkModule;
import view.FooterPanel;
import view.GridPanel;
import view.HeaderPanel;
import view.ImageModule;
import view.LinkModule;
import view.MainPanel;
import view.Module;
import view.TextListField;

public class C_musique {
	
	private JPanel musiques_panel;
	
	// Données
	Musiques musique = new Musiques();
	ArrayList<I_recherche> musiques = new ArrayList<>();
	 
	// Composants
	JTable musique_results_table;
	ArrayList<JTextField> musique_titre_textfield;
	String[] header={ "Titre", "Duree","Album","Univers","Annee de sortie" };
	HeaderPanel musique_header;
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
		musique_header = new HeaderPanel(this.musiques_panel, "Cet onglet permet de renseigner des musiques",
				header, elmsSize);
		ArrayList<JTextField> liste = musique_header.getJtextArrray();
		this.musique_titre_textfield = liste;
	}

	public void ajoutMainPanel() {
		MainPanel musique_main = new MainPanel(this.musiques_panel);
		// Add element
		//ligne 1
		musique_main.addModule(new DualLinkModule("Personne"), 0, 0, 2, 1);
		
		musique_main.addModule(new ImageModule(), 2, 0);
		//ligne 2
		musique_main.addModule(new LinkModule("Genre"), 0, 1);
		
		GridPanel relationComple = new GridPanel(new double[] {1.0, 1.0}, new double[] {1.0, 1.0, 1.0});
		musique_main.add(relationComple, musique_main.addElement(1, 1));
		relationComple.add(new ComboListField(new String[] {"etat1", "etat2", "etat3"}), relationComple.addElement(0, 0));
		relationComple.add(new TextListField(), relationComple.addElement(0, 1));
		relationComple.add(new TextListField(), relationComple.addElement(1, 1));
		relationComple.add(new TextListField(), relationComple.addElement(0, 2));
		
		musique_main.addModule(new Module(), 2, 1);
	}
	public void ajouteTab() {
		AsidePanel musiques_aside = new AsidePanel(this.musiques_panel);
		musiques_aside.setEntetes(musique.toHeaderData());
		musiques=musique.lectureTout(50);
		musiques_aside.setDonnees(musiques);
		// Ajout d'un evenemment
		this.musique_results_table = musiques_aside.getTable_result();
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
	public void actualiseMusique() {
		// Actualise le titre
		 musique_header.autoCompleteFormHeader(musique.toRowDataForm());
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
			musique.lireUn(musiques.get(row).getId());
			this.controller.actualiseMusique();
			// Plus tard faire appelle à la méthode actualise livre qui actualise tous les champs
		}
	}

}
