package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Editeur;
import modele.Etat;
import modele.I_recherche;
import modele.Livre;
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

public class C_Livre {
	private JPanel livres_panel;
	
	// Données
	Livre livre = new Livre();
	ArrayList<I_recherche> livres = new ArrayList<>();
	 
	// Composants
	JTable livre_results_table;
	ArrayList<JTextField> livre_titre_textfield;
	
	HeaderPanel livre_header;
	
	DualLinkModule livre_module_personne = new DualLinkModule("Personne", new String[]{"ecrivain"});
	LinkModule livre_module_genre = new LinkModule("Genre");
	ComboListField livre_module_etat;
	ComboListField livre_module_editeur;
	TextAreaScrollField livre_module_resume;
	

	public C_Livre(JPanel livres_panel) {
		this.livres_panel = livres_panel;
		
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
		
	}
	
	public JTable getLivre_results_table() {
		return livre_results_table;
	}
	
	public ArrayList<JTextField> getLivre_titre_textfield() {
		return livre_titre_textfield;
	}
	
	public void ajouteHeader() {
		String tabHeader[] = { "Titre", "ISBN", "Annee de sortie", "Saga", "Univers" };
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		livre_header = new HeaderPanel(this.livres_panel, "Cet onglet permet de renseigner des livres",
				tabHeader, elmsSize);
		ArrayList<JTextField> liste = livre_header.getJtextArrray();
		this.livre_titre_textfield = liste;
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
		livre_module_etat = new ComboListField(Etat.lectureTout());
		relation_panel.add(livre_module_etat, relation_panel.addElement(0, 1));
		relation_panel.add(new JLabel("Editeur"), relation_panel.addElement(1, 0));
		livre_module_editeur = new ComboListField(Editeur.lectureTout());
		relation_panel.add(livre_module_editeur, relation_panel.addElement(1, 1));
		relation_panel.add(new Module(), relation_panel.addElement(0, 2, 2, 2));
		
		GridPanel resume_panel = new GridPanel(new double[] {1.0}, new double[] {1.0, 5.0});
		livre_main.add(resume_panel, livre_main.addElement(2, 1));
		resume_panel.add(new JLabel("Resumé"), resume_panel.addElement(0, 0));
		livre_module_resume = new TextAreaScrollField(5,10);
		resume_panel.add(livre_module_resume, resume_panel.addElement(0, 1));
		
	}
	public void ajouteTab() {
		AsidePanel livres_aside_panel = new AsidePanel(this.livres_panel);
		livres_aside_panel.setEntetes( new String[] { "Titre", "ISBN", "Editeur", "Etat", "Année de sortie" });
		
		livres = livre.lectureTout(50);
		livres_aside_panel.setDonnees(livres);

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
		livre_header.autoCompleteFormHeader(livre.toRowDataForm());
		
		// Actualise le main panel
		//personne
		ArrayList<String> tPersonneData = new ArrayList<>();
		ArrayList<String> tPersonneDataMetier = new ArrayList<>();
		for(int i = 0; i < livre.getArtistes().size(); i++) {
			for(int j = 0; j < livre.getArtistes().get(i).getMetiers_artiste().size(); j++) {
				tPersonneData.add(livre.getArtistes().get(i).getSurnom_artiste());
				tPersonneDataMetier.add(livre.getArtistes().get(i).getMetiers_artiste().get(j).getNom());
			}
		}
		this.livre_module_personne.setData(tPersonneData.toArray(new String[0]), tPersonneDataMetier.toArray(new String[0]));
		//genre
		String[] tGenreData = new String[livre.getGenres().size()];
		for(int i = 0; i < tGenreData.length; i++) {
			tGenreData[i] = livre.getGenres().get(i).getNom();
		}
		this.livre_module_genre.setData(tGenreData);
		//etat
		this.livre_module_etat.setSelectedIndex(livre.getEtat().getId()-1);
		//editeur
		this.livre_module_editeur.setSelectedIndex(livre.getEditeur().getId());
		//resume
		this.livre_module_resume.getText_area().setText(livre.getResume_livre());
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
			//System.out.println("Test click" + row);
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String titre = (String)this.controller.getLivre_results_table().getValueAt(row, 0);
			//this.controller.getLivre_titre_textfield().setText(titre);
			livre.lireUn(livres.get(row).getId());
			this.controller.actualiseLivre();
			// Plus tard faire appelle Ã  la méthode actualise livre qui actualise tous les champs
		}
	}
}
