package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Artiste;
import modele.Editeur;
import modele.Etat;
import modele.Genre;
import modele.I_recherche;
import modele.Livre;
import modele.Metier;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

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

public class C_Livre {
	private JPanel livres_panel;

	// Données
	Livre livre = new Livre();
	ArrayList<I_recherche> livres = new ArrayList<>();

	// Composants
	JTable livre_results_table;
	ArrayList<PlaceholderTextField> livre_titre_textfield;

	HeaderPanel livre_header;
	String[] header_title = { "Titre", "ISBN", "Annee de sortie", "Saga", "Univers", "Tome" };
	AsidePanel livres_aside_panel;

	DualLinkModule livre_module_personne;
	LinkModule livre_module_genre;
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

	public ArrayList<PlaceholderTextField> getLivre_titre_textfield() {
		return livre_titre_textfield;
	}

	public void ajouteHeader() {
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		livre_header = new HeaderPanel(this.livres_panel, "Cet onglet permet de renseigner des livres",
				this.header_title, elmsSize);
		ArrayList<PlaceholderTextField> liste = livre_header.getJtextArrray();
		this.livre_titre_textfield = liste;
	}

	public void ajoutMainPanel() {
		MainPanel livre_main = new MainPanel(this.livres_panel);
		this.livre_module_personne=livre_main.addPanelPersonne(new String[] { "ecrivain" });
		
		// Add element
		// ligne 1

		livre_main.addPanelImage();
		// ligne 2
		livre_module_genre=livre_main.addPanelGenre();


		livre_module_etat = livre_main.addPanelEtat(2,2);

		livre_module_editeur = livre_main.addPanelEditeur();

		livre_module_resume = livre_main.addPanelResume();

		/**
		 * lie un artiste et un livre lors de l'appui sur +
		 */
		livre_module_personne.getMore_btn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!livre_module_personne.empty()) {
					if (livre.getId() != 0) {
						livre_module_personne.addTextListField();
						Artiste artiste = new Artiste();
						Metier metier = new Metier();
//					System.out.println(livre_module_personne.getText_list().get(livre_module_personne.getText_list().size()-1));
						artiste.creationRapide(livre_module_personne.getText_list()
								.get(livre_module_personne.getText_list().size() - 1));
						metier.setInfo(livre_module_personne.getCombo_list()
								.get(livre_module_personne.getCombo_list().size() - 1));
						artiste.setMetiers_artiste(metier);
						livre.addArtiste(artiste);
						livre.ajoutLiaisonArtisteMetierMedia();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livre_main,
								"Livre pas encore cree, veuillez cree le livre avant d'ajouter ou supprimer\n un artiste");
					}
				}
			}
		});
		/**
		 * delie un artiste et un livre lors de l'appui sur -
		 */
		livre_module_personne.getLess_btn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(livre_module_personne.getContent_list().getSelectedIndices().length>0) {
					if (livre.getId() != 0) {
						livre.supprimeLiaisonArtisteMetierMedia();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livre_main,
								"Livre pas encore cree, veuillez cree le livre avant d'ajouter ou supprimer\n un artiste");
					}
				}else {
					JOptionPane.showMessageDialog(livre_main,"Veuillez selectionner un element dans la liste ");
				}
			}
		});
		/**
		 * lie un genre et un livre lors de l'appuie sur +
		 */
		livre_module_genre.getMore_btn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!livre_module_genre.empty()) {
					if (livre.getId() != 0) {
						livre_module_genre.addTextListField();
						Genre genre = new Genre();
//					System.out.println(livre_module_genre.getText_list().get(livre_module_genre.getText_list().size()-1));
						genre.setInfo(
								livre_module_genre.getText_list().get(livre_module_genre.getText_list().size() - 1));
						livre.addGenre(genre);
						livre.ajoutLiasonMediaGenre();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livre_main,
								"Livre pas encore cree, veuillez cree le livre avant d'ajouter ou supprimer\n un genre");
					}
				}
			}
		});
		/**
		 * delie un genre et un livre lors de l'appuie sur -
		 */
		livre_module_genre.getLess_btn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(livre_module_genre.getContent_list().getSelectedIndices().length>0) {
					if (livre.getId() != 0) {
						livre.supprimeLiasonMediaGenre();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livre_main,
								"Livre pas encore cree, veuillez cree le livre avant d'ajouter ou supprimer\n un genre");
					}
				}else {
					JOptionPane.showMessageDialog(livre_main,"Veuillez selectionner un element dans la liste ");
				}
			}
		});
	}

	public void ajouteTab() {
		livres_aside_panel = new AsidePanel(this.livres_panel);
		livres_aside_panel.setEntetes(new String[] { "Titre", "ISBN", "Editeur", "Etat", "Année de sortie" });

		actualiseTab();

		// Ajout d'un evenemment
		this.livre_results_table = livres_aside_panel.getTable_result();
		livre_results_table.addMouseListener(new MouseAdapterTableau(this));

	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer","Reset" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel livre_footer_panel = new FooterPanel(this.livres_panel, textBouton, elmsSizeFooter);
		// Event
		// Créer
		livre_footer_panel.getBoutonTab().get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifChamp()) {
					if (livre.creation()) {
						JOptionPane.showMessageDialog(livres_panel, "Insertion faites", "Validation",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livres_panel, "Erreur lors de l'insertion", "Echec insertion",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		// Modifier
		livre_footer_panel.getBoutonTab().get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifChamp()) {
					if (livre.modification()) {
						JOptionPane.showMessageDialog(livres_panel, "Modification faites", "Modifie",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livres_panel, "Erreur lors de la modification", "Echec update",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		// Supprimer
		livre_footer_panel.getBoutonTab().get(2).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (livre.getId() != 0) {
					if (livre.suppression(livre.getId())) {
						JOptionPane.showMessageDialog(livres_panel, "Suppression faites", "Supprime",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(livres_panel, "Erreur lors de la suppression", "Echec delete",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(livres_panel, "Erreur id media inexistant", "Echec delete",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		livre_footer_panel.getBoutonTab().get(3).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (PlaceholderTextField text : livre_titre_textfield) {
					text.setText("");
				}
				
			}
		});
	}

	public boolean verifChamp() {
		boolean success = true;
		if (livre_titre_textfield.get(0).getText().equals("")) {
			success = false;
			JOptionPane.showMessageDialog(livres_panel, "Veuillez saisir un titre");
		} else {
			// Oeuvre
			livre.setOeuvre(livre_titre_textfield.get(0).getText().toLowerCase());
			// Annee sortie
			try {
				if (!livre_titre_textfield.get(1).getText().equals("")) {
					if (livre_titre_textfield.get(2).getText().toLowerCase().length() == 4
							&& Integer.parseInt(livre_titre_textfield.get(2).getText().toLowerCase()) > 1900) {
						livre.setAnnee_sortie_media(livre_titre_textfield.get(2).getText().toLowerCase());
					} else {
						success = false;
						JOptionPane.showMessageDialog(livres_panel, "Annee non valide", "Echec",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					livre.setAnnee_sortie_media("0000");
				}
			} catch (Exception e2) {
				success = false;
				JOptionPane.showMessageDialog(livres_panel, "l'annee de sortie ne doit comporter que des chiffres",
						"Echec", JOptionPane.ERROR_MESSAGE);
			}
			// Univers
			livre.setUnivers(livre_titre_textfield.get(4).getText().toLowerCase());
			// Saga
			livre.setSaga(livre_titre_textfield.get(3).getText().toLowerCase());
			// Etat
			livre.setEtat(livre_module_etat.getSelectedIndex() + 1);
			// Image

			// ISBN
			try {
				if (!livre_titre_textfield.get(1).getText().equals("")) {
					if (Integer.parseInt(livre_titre_textfield.get(1).getText()) < 1000000000) {
						livre.setISBN(Integer.parseInt(livre_titre_textfield.get(1).getText()));
					} else {
						success = false;
						JOptionPane.showMessageDialog(livres_panel, "ISBN non valide", "Echec",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					livre.setISBN(0);
				}
			} catch (Exception e) {
				success = false;
				JOptionPane.showMessageDialog(livres_panel, "l'ISBN ne doit comporter que des chiffres", "Echec",
						JOptionPane.ERROR_MESSAGE);
			}
			// Resumé
			livre.setResume_livre(livre_module_resume.getText_area().getText());
			// Tome
			try {
				if (!livre_titre_textfield.get(1).getText().equals("")) {
					livre.setTome_livre(Integer.parseInt(livre_titre_textfield.get(5).getText()));
				} else {
					livre.setTome_livre(0);
				}
			} catch (Exception e) {
				success = false;
				JOptionPane.showMessageDialog(livres_panel, "le numéro du tome ne doit comporter que des chiffres",
						"Echec", JOptionPane.ERROR_MESSAGE);
			}
			// Editeur
			System.out.println(livre_module_editeur.getSelectedItem().toString());
			livre.getEditeur().setId(Editeur.getIdInList(livre_module_editeur.getSelectedItem().toString()));
			livre.getEditeur().setNom(livre_module_editeur.getSelectedItem().toString());

			// Personne
			livre.getArtistes().clear();
			for (int i = 0; i < livre_module_personne.getText_list().size(); i++) {
				Artiste artiste = new Artiste();
				Metier metier = new Metier();
				artiste.setSurnom_artiste(livre_module_personne.getText_list().get(i));
				metier.setNom(livre_module_personne.getCombo_list().get(i));
				artiste.setMetiers_artiste(metier);
				livre.addArtiste(artiste);
			}
			// genre
			livre.getGenres().clear();
			for (int i = 0; i < livre_module_genre.getText_list().size(); i++) {
				Genre genre = new Genre();
				genre.setInfo(livre_module_genre.getText_list().get(i));
				livre.addGenre(genre);
			}
		}

		return success;
	}

	/**
	 * Actualise le tableau
	 */
	public void actualiseTab() {
		livres = livre.lectureTout(50);
		livres_aside_panel.setDonnees(livres);
	}

	/**
	 * Actualise le formulaire du livre
	 */
	public void actualiseLivre() {
		// Actualise le header panel
		livre_header.autoCompleteFormHeader(livre.toRowDataForm());

		// Actualise le main panel
		// personne
		ArrayList<String> tPersonneData = new ArrayList<>();
		ArrayList<String> tPersonneDataMetier = new ArrayList<>();
		for (int i = 0; i < livre.getArtistes().size(); i++) {
			for (int j = 0; j < livre.getArtistes().get(i).getMetiers_artiste().size(); j++) {
				tPersonneData.add(livre.getArtistes().get(i).getSurnom_artiste());
				tPersonneDataMetier.add(livre.getArtistes().get(i).getMetiers_artiste().get(j).getNom());
			}
		}
		this.livre_module_personne.setData(tPersonneData.toArray(new String[0]),
				tPersonneDataMetier.toArray(new String[0]));
		// genre
		String[] tGenreData = new String[livre.getGenres().size()];
		for (int i = 0; i < tGenreData.length; i++) {
			tGenreData[i] = livre.getGenres().get(i).getNom();
		}
		this.livre_module_genre.setData(tGenreData);
		// etat
		this.livre_module_etat.setSelectedIndex(livre.getEtat().getId() - 1);
		// editeur
		this.livre_module_editeur.setSelectedItem(livre.getEditeur().getNom());

		livre.getEditeur().setId(Editeur.getIdInList(livre_module_editeur.getSelectedItem().toString()));
		livre.getEditeur().setNom(livre_module_editeur.getSelectedItem().toString());
		// resume
		this.livre_module_resume.getText_area().setText(livre.getResume_livre());

	}

	/**
	 * Classe interne
	 * 
	 * @author Romain
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter {

		C_Livre controller;

		public MouseAdapterTableau(C_Livre controller) {
			this.controller = controller;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// PERMET DE RECUP LA POSITION DANS LA MATRICE DU TABLEAU
			int row = this.controller.getLivre_results_table().rowAtPoint(e.getPoint());
			// int column = tableau.columnAtPoint(e.getPoint());
			// System.out.println("Test click" + row);
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String titre = (String) this.controller.getLivre_results_table().getValueAt(row, 0);
			// this.controller.getLivre_titre_textfield().setText(titre);
			livre.lireUn(livres.get(row).getId());
			this.controller.actualiseLivre();
			// Plus tard faire appelle à la méthode actualise livre qui actualise tous les
			// champs
		}
	}
}

