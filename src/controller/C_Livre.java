package controller;

import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Livre;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import view.AsidePanel;
import view.FooterPanel;
import view.HeaderPanel;
import view.ImageModule;
import view.LinkModule;
import view.MainPanel;
import view.Module;

public class C_Livre {
	private JPanel livres_panel;
	
	// Données
	Livre livre = new Livre();
	ArrayList<Livre> livres = new ArrayList<>();
	 
	// Composants
	JTable livre_results_table;
	JTextField livre_titre_textfield;
	
	
	public JTable getLivre_results_table() {
		return livre_results_table;
	}
	
	public JTextField getLivre_titre_textfield() {
		return livre_titre_textfield;
	}
	

	public C_Livre(JPanel livres_panel) {
		this.livres_panel = livres_panel;
		
		// Faire une requete qui va  remplir la liste de livre "livres"
		
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();		
	}
	
	public void ajouteHeader() {
		String tabHeader[] = { "Titre", "ISBN", "Annee de sortie" };
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel livre_header = new HeaderPanel(this.livres_panel, "Cet onglet permet de renseigner des livres",
				tabHeader, elmsSize);
		ArrayList<JTextField> liste = livre_header.getJtextArrray();
		this.livre_titre_textfield = liste.get(0);
	}

	public void ajoutMainPanel() {
		MainPanel livre_main = new MainPanel(this.livres_panel);
		// Add element
		livre_main.addModule(new LinkModule("Personne"), 0, 0);
		livre_main.addModule(new Module(), 1, 0);
		livre_main.addModule(new ImageModule(), 2, 0);

		livre_main.addModule(new LinkModule("Genre"), 0, 1);
		livre_main.addModule(new Module(), 1, 1);
		livre_main.addModule(new Module(), 2, 1);
	}
	public void ajouteTab() {
		AsidePanel livres_aside_panel = new AsidePanel(this.livres_panel);
		livres_aside_panel.setEntetes( new String[] { "Titre", "ISBN", "Genre", "Etat", "Annee" });
		livres_aside_panel.setDonnees(new Object[][] { { "La boussole d'or", "toto en vacance", "valide", "2010" }, });// TODO il faut le mettre dans l'attribut "livres"
		livres_aside_panel.ajouterLigne(new Object[] { "toto", "tata et toto", null, null });

		// Ajout d'un evenemment
		this.livre_results_table = livres_aside_panel.getTable_result();
		livre_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel livre_footer_panel = new FooterPanel(this.livres_panel, textBouton, elmsSizeFooter);
	}
	
	/**
	 * Actualise le formulaire du livre
	 */
	public void actualiseLivre() {
		// Actualise le titre
		this.getLivre_titre_textfield().setText(this.livre.getTitre_media());
		// Actualise l'ISBN, etc ...
		
	}
	
	
	/**
	 * Classe interne
	 * @author Romain
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter{
		
		C_Livre controller;
		
		public MouseAdapterTableau(C_Livre controller) {
			this.controller = controller;
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// PERMET DE RECUP LA POSITION DANS LA MATRICE DU TABLEAU
			int row = this.controller.getLivre_results_table().rowAtPoint(e.getPoint());
			// int column = tableau.columnAtPoint(e.getPoint());
			System.out.println("Test click" + row);
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String titre = (String)this.controller.getLivre_results_table().getValueAt(row, 0);
			this.controller.getLivre_titre_textfield().setText(titre);
			// Plus tard faire appelle à la méthode actualise livre qui actualise tous les champs
		}
	}
}
