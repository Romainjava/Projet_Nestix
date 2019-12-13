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
import modele.Genre;
import modele.I_recherche;
import modele.Metier;
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
	int row;

	// Composants
	JTable musique_results_table;
	ArrayList<JTextField> musique_titre_textfield;
	String[] header = { "Titre", "Duree(en secondes)", "Album", "Univers", "Annee de sortie" };
	HeaderPanel musique_header;
	AsidePanel musiques_aside;
	ComboListField comboListField = new ComboListField(new String[] { "valide", "attente", "bloquer" });
	DualLinkModule dualLinkModule = new DualLinkModule("Personne", new String[] { "interprete", "compositeur" });
	LinkModule linkModule = new LinkModule("Genre");

	public JTable getMusique_results_table() {
		return musique_results_table;
	}

	public ArrayList<JTextField> getMusique_titre_textfield() {
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
		ArrayList<JTextField> liste = musique_header.getJtextArrray();
		this.musique_titre_textfield = liste;
	}

	public void ajoutMainPanel() {
		MainPanel musique_main = new MainPanel(this.musiques_panel);
		// Add element
		// ligne 1
		musique_main.addModule(dualLinkModule, 0, 0, 2, 1);

		musique_main.addModule(new ImageModule(), 2, 0);
		// ligne 2
		musique_main.addModule(linkModule, 0, 1);

		GridPanel relationComple = new GridPanel(new double[] { 1.0, 1.0 }, new double[] { 1.0, 1.0, 1.0 });
		musique_main.add(relationComple, musique_main.addElement(1, 1));
		comboListField.setSelectedIndex(1);
		relationComple.add(comboListField);
		relationComple.addElement(0, 0);
		relationComple.add(new TextListField(), relationComple.addElement(0, 1));
		relationComple.add(new TextListField(), relationComple.addElement(1, 1));
		relationComple.add(new TextListField(), relationComple.addElement(0, 2));

		musique_main.addModule(new Module(), 2, 1);
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
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel livre_footer_panel = new FooterPanel(this.musiques_panel, textBouton, elmsSizeFooter);
		livre_footer_panel.getBoutonTab().get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = verifChamp();
				if (success) {
					if (musique.creation()) {
						JOptionPane.showMessageDialog(musiques_panel, "Insertion faites", "Validation",
								JOptionPane.INFORMATION_MESSAGE);
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(musiques_panel, "Erreur lors de l'insertion", "Echec insertion",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		livre_footer_panel.getBoutonTab().get(1).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = verifChamp();
				if (success) {
					if (musique.modification()) {
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
	}

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
			musique.setAlbum(musique_titre_textfield.get(2).getText().toLowerCase());
			musique.setUnivers(musique_titre_textfield.get(3).getText().toLowerCase());
			musique.setEtat(comboListField.getSelectedIndex() + 1);
			for (int i = 0; i < dualLinkModule.getText_list().size(); i++) {
				Artiste artiste = new Artiste();
				Metier metier = new Metier();
				artiste.setSurnom_artiste(dualLinkModule.getText_list().get(i));
				metier.setNom(dualLinkModule.getCombo_list().get(i));
				artiste.setMetiers_artiste(metier);
				musique.addArtiste(artiste);
			}
			for (int i = 0; i < linkModule.getText_list().size(); i++) {
				Genre genre = new Genre();
				genre.setInfo(linkModule.getText_list().get(i));
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
		// Actualise le titre
		musique_header.autoCompleteFormHeader(musique.toRowDataForm());
		comboListField.setSelectedIndex(musique.getEtatId() - 1);
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
