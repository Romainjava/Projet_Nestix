package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Artiste;
import modele.Etat;
import modele.Film;
import modele.Genre;
import modele.I_recherche;
import modele.Metier;
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
import view.PlaceholderTextField;
import view.TextAreaScrollField;
import view.TextListField;

public class C_film {
	
	private JPanel films_panel;
	
	private Film film = new Film();
	private ArrayList<I_recherche> films = new ArrayList<>();
	int row;
	
	JTable film_results_table;

	ArrayList<PlaceholderTextField> film_titre_textfield;
	String header[] = { "Titre", "Durée", "Année de sortie", "Saga","Univers" };

	ComboListField comboListField = new ComboListField(new String[] { "valide", "attente", "bloquer" });
	DualLinkModule dualLinkModule = new DualLinkModule("Personne", new String[] { "acteur", "realisateur", "scenariste" });
	LinkModule linkModule = new LinkModule("Genre");
	HeaderPanel films_header;
	MainPanel film_main;
	AsidePanel films_aside_panel;
	FooterPanel film_footer_panel;
	
	DualLinkModule film_module_personne;
	LinkModule film_module_genre;
	ComboListField film_module_etat;
	TextAreaScrollField film_module_resume;
	
	private JTable getFilm_results_table() {
		return film_results_table;
	}
	
	public ArrayList<PlaceholderTextField> getFilm_titre_textfield() {

		return film_titre_textfield;
	}
	
	public C_film(JPanel films_panel) {
		this.films_panel = films_panel;
		
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
	}

	private void ajouteHeader() {
		double[] elmsSize = {1.0, 1.0, 1.0, 1.0};
		films_header = new HeaderPanel(this.films_panel, "Cet onglet permet de renseigner des films",
				header, elmsSize);
		ArrayList<PlaceholderTextField> liste = films_header.getJtextArrray();
		this.film_titre_textfield = liste;

	}

	private void ajoutMainPanel() {
		MainPanel film_main = new MainPanel(this.films_panel);
		//ligne 1
		film_module_personne=film_main.addPanelPersonne(new String[]{"acteur", "realisateur", "scenariste"});
		film_main.addPanelImage();
		// ligne 2
		film_module_genre=film_main.addPanelGenre();

		film_module_etat=film_main.addPanelEtat();
		
		film_module_resume = film_main.addPanelResume();

		film_main.addModule(new Module(), 2, 1);
	}
	private void ajouteTab() {
		films_aside_panel = new AsidePanel(this.films_panel);
		films_aside_panel.setEntetes(film.toHeaderData());
		films = film.lectureTout(50);
		films_aside_panel.setDonnees(films);
		// Ajout d'un evenemment
		this.film_results_table = films_aside_panel.getTable_result();
		film_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	private void footerPanel() {
		String[] textBouton = {"Creer", "Modifier", "Supprimer","Reset"};
		double[] elmsSizeFooter = {1.0, 1.0, 1.0};
		FooterPanel film_footer_panel = new FooterPanel(this.films_panel, textBouton, elmsSizeFooter);
		film_footer_panel.getBoutonTab().get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifChamp()) {
					System.out.println(film.getOeuvre().getId());
					if (film.creation()) {
						JOptionPane.showMessageDialog(films_panel, "Insertion faites", "Validation",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(films_panel, "Erreur lors de l'insertion", "Echec insertion",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		film_footer_panel.getBoutonTab().get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifChamp()) {
					if (film.modification()) {
						JOptionPane.showMessageDialog(films_panel, "Modification faites", "Modifie",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(films_panel, "Erreur lors de la modification",
								"Echec insertion", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		film_footer_panel.getBoutonTab().get(2).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				film.suppression(film.getId());
				System.out.println(film_module_personne.getText_list().size());
			}
		});
		film_footer_panel.getBoutonTab().get(3).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (PlaceholderTextField text : film_titre_textfield) {
					text.setText("");
				}
				film_module_resume.getText_area().setText(null);
				film_module_etat.setSelectedIndex(1);
				film_module_genre.resetTextListField();
				film_module_personne.resetTextListField();
				
			}
		});
	}

	private boolean verifChamp() {
		boolean success = true;
		if (film_titre_textfield.get(0).getText().equals("")) {
			success = false;
			JOptionPane.showMessageDialog(films_panel, "Veuillez saisir un titre");
		} else {
			film.setOeuvre(film_titre_textfield.get(0).getText().toLowerCase());
			try {
				if (film_titre_textfield.get(1).getText().length() > 0) {
					film.setDuree_film(Integer.parseInt(film_titre_textfield.get(1).getText()));
				}
			} catch (NumberFormatException e2) {
				success = false;
				JOptionPane.showMessageDialog(films_panel,
						"la dure du film ne doit comporter que des chiffres", "Echec", JOptionPane.ERROR_MESSAGE);
			}
			try {
				if (film_titre_textfield.get(2).getText().toLowerCase().length() == 4
						&& Integer.parseInt(film_titre_textfield.get(2).getText().toLowerCase()) > 1900) {
					film.setAnnee_sortie_media(film_titre_textfield.get(2).getText().toLowerCase());
				} else {
					success = false;
					JOptionPane.showMessageDialog(films_panel, "Annee non valide", "Echec",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
				success = false;
				JOptionPane.showMessageDialog(films_panel, "l'annee de sortie ne doit comporter que des chiffres",
						"Echec", JOptionPane.ERROR_MESSAGE);
			}
			// Resumé
			film.setResume_film(film_module_resume.getText_area().getText());
			film.setSaga(film_titre_textfield.get(3).getText().toLowerCase());
			film.setUnivers(film_titre_textfield.get(4).getText().toLowerCase());
			film.setEtat(comboListField.getSelectedIndex() + 1);
			for (int i = 0; i < film_module_personne.getText_list().size(); i++) {
				Artiste artiste = new Artiste();
				Metier metier = new Metier();
				artiste.creationRapide(film_module_personne.getText_list().get(i));
				metier.setInfo(film_module_personne.getCombo_list().get(i));
				artiste.setMetiers_artiste(metier);
				film.addArtiste(artiste);
			}
			for (int i = 0; i < film_module_genre.getText_list().size(); i++) {
				Genre genre = new Genre();
				genre.setInfo(film_module_genre.getText_list().get(i));
				film.addGenre(genre);
			}
		}
		return success;
	}

	public void actualiseTab() {
		films = film.lectureTout(50);
		films_aside_panel.setDonnees(films);
	}
	
	public void actualiseFilm() {
		// Actualise le header panel
		films_header.autoCompleteFormHeader(film.toRowDataForm());
		comboListField.setSelectedIndex(film.getEtatId() - 1);

		// personne
		ArrayList<String> tPersonneData = new ArrayList<>();
		ArrayList<String> tPersonneDataMetier = new ArrayList<>();
		for (int i = 0; i < film.getArtistes().size(); i++) {
			for (int j = 0; j < film.getArtistes().get(i).getMetiers_artiste().size(); j++) {
				tPersonneData.add(film.getArtistes().get(i).getSurnom_artiste());
				tPersonneDataMetier.add(film.getArtistes().get(i).getMetiers_artiste().get(j).getNom());
			}
		}
		this.film_module_personne.setData(tPersonneData.toArray(new String[0]),
				tPersonneDataMetier.toArray(new String[0]));
		// genre
		String[] tGenreData = new String[film.getGenres().size()];
		for (int i = 0; i < tGenreData.length; i++) {
			tGenreData[i] = film.getGenres().get(i).getNom();
		}
		this.film_module_genre.setData(tGenreData);
		// etat
		this.film_module_etat.setSelectedIndex(film.getEtat().getId() - 1);
		this.film_module_resume.getText_area().setText(film.getResume_film());
	}
	
	/**
	 * Classe interne
	 * @author Romain
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter{
		
		C_film controller;

		private MouseAdapterTableau(C_film controller) {
			this.controller = controller;
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// PERMET DE RECUP LA POSITION DANS LA MATRICE DU TABLEAU
			int row = this.controller.getFilm_results_table().rowAtPoint(e.getPoint());
			// int column = tableau.columnAtPoint(e.getPoint());
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			film.lireUn(films.get(row).getId());
			this.controller.actualiseFilm();
			// Plus tard faire appelle Ã  la mÃ©thode actualise livre qui actualise tous les champs
		}
	}
}