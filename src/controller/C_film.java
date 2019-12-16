package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Etat;
import modele.Film;
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

public class C_film {
	
	private JPanel films_panel;
	
	Film film = new Film();
	ArrayList<Film> films = new ArrayList<Film>();
	int row;
	
	JTable film_results_table;
	ArrayList<JTextField> film_titre_textfield;
	String header[] = { "Titre", "Durée", "Année de sortie", "Saga" };
	ComboListField comboListField = new ComboListField(new String[] { "valide", "attente", "bloquer" });
	DualLinkModule dualLinkModule = new DualLinkModule("Personne", new String[] { "acteur", "realisateur", "scenariste" });
	LinkModule linkModule = new LinkModule("Genre");
	
	DualLinkModule film_module_personne = new DualLinkModule("Personne", new String[]{"acteur", "realisateur", "scenariste"});
	LinkModule film_module_genre = new LinkModule("Genre");
	ComboListField film_module_etat;
	
	public JTable getFilm_results_table() {
		return film_results_table;
	}
	
	public ArrayList<JTextField> getFilm_titre_textfield() {
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
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel films_header = new HeaderPanel(this.films_panel, "Cet onglet permet de renseigner des films",
				header, elmsSize);
		ArrayList<JTextField> liste = films_header.getJtextArrray();
		this.film_titre_textfield = liste;
	}

	public void ajoutMainPanel() {
		MainPanel film_main = new MainPanel(this.films_panel);
		//ligne 1
		film_main.addModule(film_module_personne, 0, 0, 2, 1);
		film_main.addModule(new ImageModule(), 2, 0);
		// ligne 2
		film_main.addModule(film_module_genre, 0, 1);

		GridPanel relationComple = new GridPanel(new double[] { 1.0, 1.0 }, new double[] { 1.0, 1.0, 1.0 });
		film_main.add(relationComple, film_main.addElement(1, 1));
		film_module_etat = new ComboListField(Etat.lectureTout());
		relationComple.add(film_module_etat, relationComple.addElement(0, 0));
		relationComple.add(new TextListField(), relationComple.addElement(0, 1));
		relationComple.add(new TextListField(), relationComple.addElement(1, 1));
		relationComple.add(new TextListField(), relationComple.addElement(0, 2));

		film_main.addModule(new Module(), 2, 1);
	}
	public void ajouteTab() {
		AsidePanel films_aside_panel = new AsidePanel(this.films_panel);
		films_aside_panel.setEntetes(header);
		

		// Ajout d'un evenemment
		this.film_results_table = films_aside_panel.getTable_result();
		film_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel film_footer_panel = new FooterPanel(this.films_panel, textBouton, elmsSizeFooter);
	}
	
	/**
	 * Actualise le formulaire de musique
	 */
	public void actualiseFilm(String titre) {
		// Actualise le titre
		this.getFilm_titre_textfield().get(0).setText(titre);
	}
	
	
	/**
	 * Classe interne
	 * @author Romain
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter{
		
		C_film controller;
		
		public MouseAdapterTableau(C_film controller) {
			this.controller = controller;
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// PERMET DE RECUP LA POSITION DANS LA MATRICE DU TABLEAU
			int row = this.controller.getFilm_results_table().rowAtPoint(e.getPoint());
			// int column = tableau.columnAtPoint(e.getPoint());
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String titre = (String)this.controller.getFilm_results_table().getValueAt(row, 0);
			this.controller.actualiseFilm(titre);
			// Plus tard faire appelle Ã  la mÃ©thode actualise livre qui actualise tous les champs
		}
	}
}
