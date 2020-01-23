package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import modele.Artiste;
import modele.Genre;
import modele.I_recherche;
import modele.Metier;
import modele.Musique;
import view.AsidePanel;
import view.ComboListField;
import view.DualLinkModule;
import view.FooterPanel;
import view.HeaderPanel;
import view.LinkModule;
import view.MainPanel;
import view.PlaceholderTextField;


public class C_musique {

	private JPanel musiques_panel;
	PlaceholderTextField musique_duree_textfield;
	// Donn�es
	private Musique musique = new Musique();
	private ArrayList<I_recherche> musiques = new ArrayList<>();
	private int row;

	// Composants

	private JTable musique_results_table;
	ArrayList<PlaceholderTextField> musique_titre_textfield;
	private String[] header = { "Titre", "Duree(en secondes)", "Album", "Univers", "Annee de sortie" };
	private HeaderPanel musique_header;
	private AsidePanel musiques_aside;
	private ComboListField musique_module_etat;
	private DualLinkModule musique_module_personne;
	private LinkModule musique_module_genre;
	double elmsSize[] = { 1.0, 1.0, 1.0, 1.0, 1.0 };

	public JTable getMusique_results_table() {
		return musique_results_table;
	}

	public ArrayList<PlaceholderTextField> getMusique_titre_textfield() {
		return musique_titre_textfield;
	}

	public C_musique(JPanel musiques_panel) {
		this.musiques_panel = musiques_panel;

		// Faire une requete qui va remplir la liste de livre "livres"

		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
	}

	public void ajouteHeader() {
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0, 1.0 };
		musique_header = new HeaderPanel(this.musiques_panel, "Cet onglet permet de renseigner des musiques", header,
				elmsSize);

		ArrayList<PlaceholderTextField> liste = musique_header.getJtextArrray();
		this.musique_titre_textfield = liste;
		this.musique_duree_textfield = liste.get(1);
		this.musique_duree_textfield.setPlaceholder("Durée en seconde");

	}

	public void ajoutMainPanel() {
		MainPanel musique_main = new MainPanel(this.musiques_panel);
		this.musique_module_personne=musique_main.addPanelPersonne(new String[] { "interprete", "compositeur" });
		
		// Add element
		// ligne 1

		musique_main.addPanelImage();
		// ligne 2
		musique_module_genre=musique_main.addPanelGenre();

		musique_module_etat = musique_main.addPanelEtat();


		/**
		 * lie un artiste et une musique lors de l'appui sur +
		 */
		musique_module_personne.getMore_btn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!musique_module_personne.empty()) {
					if (musique.getId() != 0) {
						musique_module_personne.addTextListField();
						Artiste artiste = new Artiste();
						Metier metier = new Metier();
						System.out.println(musique_module_personne.getText_list()
								.get(musique_module_personne.getText_list().size() - 1));
						artiste.creationRapide(musique_module_personne.getText_list()
								.get(musique_module_personne.getText_list().size() - 1));
						metier.setInfo(musique_module_personne.getCombo_list()
								.get(musique_module_personne.getCombo_list().size() - 1));
						artiste.setMetiers_artiste(metier);
						musique.addArtiste(artiste);
						musique.ajoutLiaisonArtisteMetierMedia();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(musique_main,
								"Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un artiste");
					}
				}
			}
		});
		/**
		 * delie un artiste et une musique lors de l'appui sur -
		 */
		musique_module_personne.getLess_btn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(musique_module_personne.getContent_list().getSelectedIndices().length>0) {
					if (musique.getId() != 0) {
						musique.supprimeLiaisonArtisteMetierMedia();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(musique_main,
								"Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un artiste");
					}
				}else {
					JOptionPane.showMessageDialog(musique_main,"Veuillez selectionner un element dans la liste ");
				}
			}
		});
		/**
		 * lie un genre et une musique lors de l'appuie sur +
		 */
		musique_module_genre.getMore_btn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!musique_module_genre.empty()) {
					if (musique.getId() != 0) {
						musique_module_genre.addTextListField();
						Genre genre = new Genre();
						System.out.println(musique_module_genre.getText_list()
								.get(musique_module_genre.getText_list().size() - 1));
						genre.setInfo(musique_module_genre.getText_list()
								.get(musique_module_genre.getText_list().size() - 1));
						musique.addGenre(genre);
						musique.ajoutLiasonMediaGenre();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(musique_main,
								"Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un genre");
					}
				}
			}
		});
		/**
		 * delie un genre et une musique lors de l'appuie sur -
		 */
		musique_module_genre.getLess_btn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(musique_module_genre.getContent_list().getSelectedIndices().length>0) {
					if (musique.getId() != 0) {
						musique.supprimeLiasonMediaGenre();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(musique_main,
								"Musique pas encore cree, veuillez cree la musique avant d'ajouter ou supprimer\n un genre");
					}
				}else {
					JOptionPane.showMessageDialog(musique_main,"Veuillez selectionner un element dans la liste ");
				}
			}
		});
	}

	public void ajouteTab() {
		musiques_aside = new AsidePanel(this.musiques_panel);
		musiques_aside.setEntetes(musique.toHeaderData());
		musiques = musique.lectureTout(50);
		musiques_aside.setDonnees(musiques);
		// Ajout d'un evenemment
		this.musique_results_table = musiques_aside.getTable_result();
		musique_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer","Reset" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel musique_footer_panel = new FooterPanel(this.musiques_panel, textBouton, elmsSizeFooter);
		/**
		 * bouton cree
		 */
		musique_footer_panel.getBoutonTab().get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifChamp()) {
					if (musique.creation()&& musique.updateDureeAlbum()) {
						JOptionPane.showMessageDialog(musiques_panel, "Insertion faites", "Validation",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					}
				}
			}
		});
		/**
		 * bouton modifier
		 */
		musique_footer_panel.getBoutonTab().get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifChamp()) {
					if (musique.modification() && musique.updateDureeAlbum()) {
						JOptionPane.showMessageDialog(musiques_panel, "Modification faites", "Modifie",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(musiques_panel, "Erreur lors de la modification",
								"Echec insertion", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		/**
		 * bouton supprimer
		 */
		musique_footer_panel.getBoutonTab().get(2).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (musique.getId() != 0 && verifChamp()) {
					musique.suppression(musique.getId());
					actualiseTab();
				}
			}
		});
		musique_footer_panel.getBoutonTab().get(3).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musique = new Musique();
				for (PlaceholderTextField text : musique_titre_textfield) {
					text.setText("");
				}
				musique_module_etat.setSelectedIndex(1);
				musique_module_genre.resetTextListField();
				musique_module_personne.resetTextListField();
				
			}
		});
	}

	/**
	 * permet de verifier les champ si ils sont valide enclenche la creation rapide
	 * et récupération des id ou la récupération des id simple si deja cree
	 * 
	 * @return boolean
	 */
	public boolean verifChamp() {
		boolean success = true;
		if (musique_titre_textfield.get(0).getText().equals("")) {
			success = false;
			JOptionPane.showMessageDialog(musiques_panel, "Veuillez saisir un titre");
		} else {
			musique.setOeuvre(musique_titre_textfield.get(0).getText().toLowerCase());
			try {
				if (musique_titre_textfield.get(1).getText().length() > 0) {
					musique.setDuree_musique(Integer.parseInt(musique_titre_textfield.get(1).getText()));
				}
			} catch (NumberFormatException e2) {
				success = false;
				JOptionPane.showMessageDialog(musiques_panel,
						"la dure de la musique ne doit comporter que des chiffres", "Echec", JOptionPane.ERROR_MESSAGE);
			}
			try {
				if (musique_titre_textfield.get(4).getText().toLowerCase().length() == 4
						&& Integer.parseInt(musique_titre_textfield.get(4).getText().toLowerCase()) > 1900) {
					musique.setAnnee_sortie_media(musique_titre_textfield.get(4).getText().toLowerCase());
				} else {
					success = false;
					JOptionPane.showMessageDialog(musiques_panel, "Annee non valide", "Echec",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
				success = false;
				JOptionPane.showMessageDialog(musiques_panel, "l'annee de sortie ne doit comporter que des chiffres",
						"Echec", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(musique_titre_textfield.get(2).getText().toLowerCase());
			musique.setAlbum(musique_titre_textfield.get(2).getText().toLowerCase());
			musique.setUnivers(musique_titre_textfield.get(3).getText().toLowerCase());
			musique.setEtat(musique_module_etat.getSelectedIndex() + 1);
			for (int i = 0; i < musique_module_genre.getText_list().size(); i++) {
				Genre genre = new Genre();
				genre.setInfo(musique_module_genre.getText_list().get(i));
				musique.addGenre(genre);
			}
		}
		return success;
	}

	/**
	 * Actualise le formulaire de musique
	 */
	public void actualiseTab() {
		musiques = musique.lectureTout(50);
		musiques_aside.setDonnees(musiques);
	}

	public void actualiseMusique() {
		// Actualise le header panel
		System.out.println(musique.getNomunivers());
		musique_header.autoCompleteFormHeader(musique.toRowDataForm());
		musique_module_etat.setSelectedIndex(musique.getEtatId() - 1);

		// personne
		ArrayList<String> tPersonneData = new ArrayList<>();
		ArrayList<String> tPersonneDataMetier = new ArrayList<>();
		for (int i = 0; i < musique.getArtistes().size(); i++) {
			for (int j = 0; j < musique.getArtistes().get(i).getMetiers_artiste().size(); j++) {
				tPersonneData.add(musique.getArtistes().get(i).getSurnom_artiste());
				tPersonneDataMetier.add(musique.getArtistes().get(i).getMetiers_artiste().get(j).getNom());
			}
		}
		this.musique_module_personne.setData(tPersonneData.toArray(new String[0]),
				tPersonneDataMetier.toArray(new String[0]));
		// genre
		String[] tGenreData = new String[musique.getGenres().size()];
		for (int i = 0; i < tGenreData.length; i++) {
			tGenreData[i] = musique.getGenres().get(i).getNom();
		}
		this.musique_module_genre.setData(tGenreData);
		// etat
		this.musique_module_etat.setSelectedIndex(musique.getEtat().getId() - 1);
	}

	/**
	 * Classe interne
	 * 
	 * @author Romain
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter {

		C_musique controller;

		public MouseAdapterTableau(C_musique controller) {
			this.controller = controller;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// PERMET DE RECUP LA POSITION DANS LA MATRICE DU TABLEAU
			row = this.controller.getMusique_results_table().rowAtPoint(e.getPoint());
			// int column = tableau.columnAtPoint(e.getPoint());
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			musique.lireUn(musiques.get(row).getId());
			this.controller.actualiseMusique();
			// Plus tard faire appelle à la méthode actualise livre qui actualise tous les
			// champs
		}
	}

}
