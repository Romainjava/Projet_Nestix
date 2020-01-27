package controller;

//-- imports swing
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

//-- imports awt
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//-- imports util
import java.util.ArrayList;

//-- imports internes modele
import modele.Artiste;
import modele.Film;
import modele.Genre;
import modele.I_recherche;
import modele.Metier;

//-- imports internes view
import view.*;

/**
 * - Classe Controleur de la vue aux données de Films.
 *
 * @author Thibault
 */
public class C_film {


	//-- Atributs de la classe C_films --\\
	//--
	private Film film = new Film();
	private JPanel films_panel;
	private ArrayList<I_recherche> films = new ArrayList<>();
	private JTable film_results_table;
	private ArrayList<PlaceholderTextField> film_titre_textfield;
	private String[] header = {"Titre", "Durée", "Année de sortie", "Saga", "univers"};
	private HeaderPanel films_header;
	private AsidePanel films_aside_panel;
	private ComboListField film_module_etat;
	private DualLinkModule film_module_personne;
	private LinkModule film_module_genre;
	private TextAreaScrollField film_module_resume;

	//-- surement à delete
	private ComboListField comboListField = new ComboListField(new String[] { "valide", "attente", "bloquer" });
	private DualLinkModule dualLinkModule = new DualLinkModule("Personne", new String[] { "acteur", "realisateur", "scenariste" });
	private LinkModule linkModule = new LinkModule("Genre");


	/**
	 * - Partage le tableau des films à la classe interne.
	 *
	 * @return le tableau des films
	 */
	private JTable getFilm_results_table() {
		return film_results_table;
	}

	//-- surement à delete
	private ArrayList<PlaceholderTextField> getFilm_titre_textfield() {
		return film_titre_textfield;
	}


	/**
	 * - Constructeur du controller des films.
	 * Construit le header, le main, le footer et l'aside de la page.
	 *
	 * @param films_panel la page film.
	 */
	public C_film(JPanel films_panel) {
		this.films_panel = films_panel;
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
	}


	/**
	 * - Création de la partie header de la page film.
	 * définit la place que vont prendre les éléments ainsi que leurs labels.
	 * définit le contenu des placeholders.
	 */
	private void ajouteHeader() {
		double[] elmsSize = {1.0, 1.0, 1.0, 1.0, 1.0};
		films_header = new HeaderPanel(this.films_panel, "Cet onglet permet de renseigner des films",
				header, elmsSize);
		ArrayList<PlaceholderTextField> liste = films_header.getJtextArrray();
		this.film_titre_textfield = liste;
		PlaceholderTextField film_duree_textfield = liste.get(1);
		film_duree_textfield.setPlaceholder("Durée en minutes");
	}


	/**
	 * - Création de la partie main de la page film.
	 * Ajout des modules personnes, genre, etat et image.
	 * Gère la liaison des artistes et des genres avec les films.
	 */
	private void ajoutMainPanel() {
		MainPanel film_main = new MainPanel(this.films_panel);
		this.film_module_personne = film_main.addPanelPersonne(new String[]{"acteur", "realisateur", "scenariste"});
		film_main.addPanelImage();
		film_module_genre=film_main.addPanelGenre();
		film_module_etat=film_main.addPanelEtat();
		film_module_resume = film_main.addPanelResume();


		//-- Lie un artiste et un film lors de l'appui sur bouton '+' --\\
		//--
		film_module_personne.getMore_btn().addActionListener(e -> {
			if (!film_module_personne.empty()) {
				if (film.getId() != 0) {
					film_module_personne.addTextListField();
					Artiste artiste = new Artiste();
					Metier metier = new Metier();
					artiste.creationRapide(film_module_personne.getText_list()
							.get(film_module_personne.getText_list().size() - 1));
					metier.setInfo(film_module_personne.getCombo_list()
							.get(film_module_personne.getCombo_list().size() - 1));
					artiste.setMetiers_artiste(metier);
					film.addArtiste(artiste);
					film.ajoutLiaisonArtisteMetierMedia();
					actualiseTab();
				} else {
					JOptionPane.showMessageDialog(film_main,
							"Film pas encore cree, veuillez cree le film avant d'ajouter ou supprimer\n un artiste");
				}
			}
		});


		//-- Délie un artiste et un film lors de l'appui sur bouton '-' --\\
		//--
		film_module_personne.getLess_btn().addActionListener(e -> {
			if (film_module_personne.getContent_list().getSelectedIndices().length > 0) {
				if (film.getId() != 0) {
					film.supprimeLiaisonArtisteMetierMedia();
					actualiseTab();
				} else {
					JOptionPane.showMessageDialog(film_main,
							"Film pas encore cree, veuillez cree le film avant d'ajouter ou supprimer\n un artiste");
				}
			} else {
				JOptionPane.showMessageDialog(film_main, "Veuillez selectionner un element dans la liste ");
			}
		});


		//-- Lie un genre et un film lors de l'appuie sur le bouton '+' --\\
		//--
		film_module_genre.getMore_btn().addActionListener(e -> {
			if (!film_module_genre.empty()) {
				if (film.getId() != 0) {
					film_module_genre.addTextListField();
					Genre genre = new Genre();
					genre.setInfo(film_module_genre.getText_list()
							.get(film_module_genre.getText_list().size() - 1));
					film.addGenre(genre);
					film.ajoutLiasonMediaGenre();
					actualiseTab();
				} else {
					JOptionPane.showMessageDialog(film_main,
							"Film pas encore cree, veuillez cree le film avant d'ajouter ou supprimer\n un genre");
				}
			}
		});


		//-- Delie un genre et une film lors de l'appuie sur le bouton '-' --\\
		//--
		film_module_genre.getLess_btn().addActionListener(e -> {
			if (film_module_genre.getContent_list().getSelectedIndices().length > 0) {
				if (film.getId() != 0) {
					film.supprimeLiasonMediaGenre();
					actualiseTab();
				} else {
					JOptionPane.showMessageDialog(film_main,
							"Film pas encore cree, veuillez cree le film avant d'ajouter ou supprimer\n un genre");
				}
			} else {
				JOptionPane.showMessageDialog(film_main, "Veuillez selectionner un element dans la liste ");
			}
		});
	}


	/**
	 * - Création de la partie aside de la page film.
	 * Ajoute au tableau les bon noms de colonnes.
	 * Récupère les données des films dans la BDD.
	 * Ajoute de l'évènement du click sur le tableau pour récupérer les données d'un film.
	 */
	private void ajouteTab() {
		films_aside_panel = new AsidePanel(this.films_panel);
		films_aside_panel.setEntetes(film.toHeaderData());
		films = film.lectureTout(50);
		films_aside_panel.setDonnees(films);
		this.film_results_table = films_aside_panel.getTable_result();
		film_results_table.addMouseListener(new MouseAdapterTableau(this));

	}


	/**
	 * - Création de la partie footer de la page musique.
	 * Définit le nom et la taille des boutons dans le footer.
	 * Définit le comportement des boutons.
	 */
	private void footerPanel() {
		String[] textBouton = {"Creer", "Modifier", "Supprimer","Reset"};
		double[] elmsSizeFooter = {1.0, 1.0, 1.0};
		FooterPanel film_footer_panel = new FooterPanel(this.films_panel, textBouton, elmsSizeFooter);


		//-- Bouton créer --\\
		//--
		film_footer_panel.getBoutonTab().get(0).addActionListener(e -> {
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
				film=new Film();
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