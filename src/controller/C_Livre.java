package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modele.Livre;
import modele.Oeuvre;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import view.TextAreaScrollField;
import view.TextListField;

public class C_Livre {
	private JPanel livres_panel;
	
	// Données
	Livre livre = new Livre();
	ArrayList<Livre> livres = new ArrayList<>();
	 
	// Composants
	JTable livre_results_table;
	JTextField livre_titre_textfield;
	JTextField livre_isbn_textfield;
	
	DualLinkModule livre_module_personne = new DualLinkModule("Personne");
	LinkModule livre_module_genre = new LinkModule("Genre");
	
	
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
		
		//!!!
		livre.lireUn(3);
		this.actualiseLivre();
		//
	}
	
	public void ajouteHeader() {
		String tabHeader[] = { "Titre", "ISBN", "Annee de sortie", "Saga", "Univers" };
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel livre_header = new HeaderPanel(this.livres_panel, "Cet onglet permet de renseigner des livres",
				tabHeader, elmsSize);
		ArrayList<JTextField> liste = livre_header.getJtextArrray();
		this.livre_titre_textfield = liste.get(0);
		this.livre_isbn_textfield = liste.get(1);
	}

	public void ajoutMainPanel() {
		MainPanel livre_main = new MainPanel(this.livres_panel);
		// Add element
		//ligne 1
		livre_main.addModule(livre_module_personne, 0, 0, 2, 1);
		
		livre_main.addModule(new ImageModule(), 2, 0);
		//ligne 2
		livre_main.addModule(livre_module_genre, 0, 1);
		
		GridPanel relation_panel = new GridPanel(new double[] {1.0, 1.0}, new double[] {1.0, 1.0, 1.0, 1.0});
		livre_main.add(relation_panel, livre_main.addElement(1, 1));
		relation_panel.add(new JLabel("Etat"), relation_panel.addElement(0, 0));
		relation_panel.add(new ComboListField(new String[] {"etat1", "etat2", "etat3"}), relation_panel.addElement(0, 1));
		relation_panel.add(new JLabel("Editeur"), relation_panel.addElement(1, 0));
		relation_panel.add(new ComboListField(new String[] {"editeur1", "editeur2", "editeur3"}), relation_panel.addElement(1, 1));
		
		GridPanel resume_panel = new GridPanel(new double[] {1.0}, new double[] {1.0, 5.0});
		livre_main.add(resume_panel, livre_main.addElement(2, 1));
		resume_panel.add(new JLabel("Resum�"), resume_panel.addElement(0, 0));
		resume_panel.add(new TextAreaScrollField(5,10), resume_panel.addElement(0, 1));
		
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
		String textBouton[] = {"Creer", "Modifier", "Supprimer"};
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel livre_footer_panel = new FooterPanel(this.livres_panel, textBouton, elmsSizeFooter);
	}
	
	/**
	 * Actualise le formulaire du livre
	 */
	public void actualiseLivre() {
		// Actualise le header panel
		this.getLivre_titre_textfield().setText(this.livre.getOeuvre().getNom());
		this.livre_isbn_textfield.setText(""+this.livre.getISBN());
		
		// Actualise le main panel
		//personne
		String[] tPersonneData = new String[livre.getArtistes().size()];
		String[] tPersonneDataMetier = new String[livre.getArtistes().size()];
		for(int i = 0; i < tPersonneData.length; i++) {
			tPersonneData[i] = livre.getArtistes().get(i).getSurnom_artiste();
			tPersonneDataMetier[i] = livre.getArtistes().get(i).getSurnom_artiste();
		}
		this.livre_module_personne.setData(tPersonneData);
		//genre
		String[] tGenreData = new String[livre.getGenres().size()];
		for(int i = 0; i < tGenreData.length; i++) {
			tGenreData[i] = livre.getGenres().get(i).getNom();
		}
		this.livre_module_genre.setData(tGenreData);
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
			//this.controller.getLivre_titre_textfield().setText(titre);
			this.controller.actualiseLivre();
			// Plus tard faire appelle à la méthode actualise livre qui actualise tous les champs
		}
	}
}
