package controller;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Artiste;
import modele.I_recherche;
import modele.Metier;
import requete.M_artiste;
import requete.M_artiste_metier_media;
import view.AsidePanel;
import view.ComboListField;
import view.FooterPanel;
import view.GridPanel;
import view.HeaderPanel;
import view.ImageModule;
import view.MainPanel;
import view.MetiersPanel;
import view.PlaceholderTextField;

public class C_artiste {
	private JPanel artiste_panel;

	// DONNEES
	Artiste artiste = new Artiste();
	ArrayList<I_recherche> artistes = new ArrayList<>();
	// COMPOSANT
	JTable artiste_result_table;
	PlaceholderTextField artiste_nom_textfield;
	PlaceholderTextField artiste_prenom_textfield;
	PlaceholderTextField artiste_surnom_textfield;
	PlaceholderTextField artiste_dob_textfield;
	AsidePanel artiste_aside;
	MetiersPanel metier_panel;

	public C_artiste(JPanel artiste_panel) {
		this.artiste_panel = artiste_panel;
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
	}

	public void ajouteHeader() {
		String[] tabHeader = { "Nom", "Prenom","Date de naissance", "Surnom" };
		double[] elmSize = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel artiste_header = new HeaderPanel(this.artiste_panel, "Cet onglet permet de renseigner des livres",
				tabHeader, elmSize);
		ArrayList<PlaceholderTextField> liste = artiste_header.getJtextArrray();
		this.artiste_nom_textfield = liste.get(0);
		this.artiste_prenom_textfield = liste.get(1);
		this.artiste_dob_textfield = liste.get(2);
		this.artiste_dob_textfield.setPlaceholder("Format : jj/mm/aaaa");
		this.artiste_surnom_textfield = liste.get(3);
		
		// affiche le text en hover
		//this.artiste_dob_textfield.setToolTipText("(Format jj/mm/aaaa");
	}

	public void ajoutMainPanel() {
		MainPanel artiste_main = new MainPanel(this.artiste_panel);

		metier_panel = new MetiersPanel();

		/**
		 * Evenement pour ajouter un Metier + Artiste + media
		 */
		JButton btn = metier_panel.metier_add_button;
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//EMPECHE LA CREATION DU BTN + SI SURNOM EST VIDE
				if (!artiste.getSurnom_artiste().equals("")) {
					// recupere les valeurs des combobox et les ajoutes en bdd
					metier_panel.getInfoMetier();
					metier_panel.getInfoMedia();

					// Artiste
					if (artiste.getId() == 0) {
						System.out.print("Creation rapide d'un artiste");
						artiste.creationRapide(artiste_surnom_textfield.getText()); // recuperer le surnom dans le
																					// jtextfield
						System.out.println(" id = " + artiste.getId());
						if (artiste.getId() > 0) {
							actualiseTab(); // Mise à jour du tableau
							actualiseListe();
						}
					}
					metier_panel.metier.setArtiste(artiste);

					// Creation dans la table jointure entre artiste media metier
					if (M_artiste_metier_media.creation(metier_panel.getMetier())) {
						actualiseListe();
						actualiseTab();
					} else {
						JOptionPane.showMessageDialog(metier_panel, "Erreur lors de la creation d'un metier");
					}

				}
			}
		});

		JButton btn_reset = metier_panel.metier_reset_button;
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAllDataTextfield();
			}
		});

		JButton btn_supprimer = metier_panel.btnSupprimer;
		btn_supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;

				if (metier_panel.artiste_metiers_list.getLastVisibleIndex() != -1) {
					ArrayList<Metier> selectedValuesList = new ArrayList<Metier>();
					List<?> list = metier_panel.artiste_metiers_list.getSelectedValuesList();
					// permet d'éviter de casté les elements si la liste est vide
					for (Object object : list) {
						selectedValuesList.add((Metier) object);
					}
					if (selectedValuesList.size() == 0) {
						JOptionPane.showMessageDialog(artiste_main,
								"Veuillez selectionner un element de la liste media");
					} else {
						for (Metier metier : selectedValuesList) {

							if (!M_artiste_metier_media.suppression(metier)) {
								flag = true;
								System.out.println(metier.getId());
								System.out.println(metier.getArtiste().getId());
								System.out.println(metier.getMedia().getId());
							}
						}
						if (flag) {
							JOptionPane.showMessageDialog(artiste_main, "Erreur lors de la supression d'un Media");
						}
						actualiseListe();
						actualiseTab();
					}
				}
			}

		});
		artiste_main.add(metier_panel);
		artiste_main.addModule(new ImageModule(), 2, 0);
		GridPanel relationComple = new GridPanel(new double[] { 1.0, 1.0 }, new double[] { 1.0, 1.0, 1.0 });
		artiste_main.add(relationComple, artiste_main.addElement(2, 1));
		relationComple.add(new ComboListField(new String[] { "valide", "en attente", "suggerer", "bloqué" }),
				relationComple.addElement(0, 0));

		actualiseListe();
	}

	public void ajouteTab() {
		artiste_aside = new AsidePanel(this.artiste_panel);
		artiste_aside.setEntetes(new String[] { "Surnom", "Metiers", "Etat", "Date de naissance" });
		actualiseTab();

		// AJOUT EVENT
		this.artiste_result_table = artiste_aside.getTable_result();
		artiste_result_table.addMouseListener(new MouseAdapterTableau(this));
	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel artiste_footer = new FooterPanel(this.artiste_panel, textBouton, elmsSizeFooter);

		ArrayList<JButton> btn = artiste_footer.getBoutonTab();

		/**
		 * Event btn creation un artiste avec requête creation dans M_artiste
		 */
		btn.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recupDonneeArtiste();
				if (artiste.getSurnom_artiste().equals("")) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention Surnom est obligatoire");
				} else if (artiste.verifDateForm() == false) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention format date doit être : jj/mm/aaaa");
				} else {
					if (artiste.creation() == false) {
						JOptionPane.showMessageDialog(artiste_panel,
								"Creation de l'artiste échoué car il existe déjà ce surnom");
					}
					actualiseTab();
				}
				actualiseTab();
				actualiseListe();
			}
		});

		/**
		 * Event btn Modifier un artiste avec requête modifier dans M_artiste
		 */
		btn.get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recupDonneeArtiste();
				if (artiste.getSurnom_artiste().equals("")) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention Surnom est obligatoire");
				} else if (artiste.verifDateForm() == false) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention format date doit être : jj/mm/aaaa");
				} else {
					if (artiste.modification() == false) {
						JOptionPane.showMessageDialog(artiste_panel,
								"Modification de l'artiste échoué car il existe déjà ce surnom");
					}
					actualiseTab();
					actualiseListe();
				}
			}
		});

		
		/**
		 * suppression d'un artiste
		 */
		btn.get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_artiste.supprime(artiste);
				actualiseTab();
				actualiseListe();
			}
		});

	}

	/**
	 * Reset tout les champs de textfield
	 */
	public void resetAllDataTextfield() {
		this.artiste_nom_textfield.setText("");
		this.artiste_prenom_textfield.setText("");
		this.artiste_surnom_textfield.setText("");
		this.artiste_dob_textfield.setText("");
		this.metier_panel.media_titre_textField.setText("");
	}

	/**
	 * Permet de récupérer les valeurs des textfield de l'instance d'artiste
	 */
	public void recupDonneeArtiste() {
		artiste.setSurnom_artiste(getArtiste_surnom_textfield().getText());
		artiste.setNom_artiste(getArtiste_nom_textfield().getText());
		artiste.setPrenom_artiste(getArtiste_prenom_textfield().getText());
		artiste.setDob_artiste(getArtiste_dob_textfield().getText());
	}

	/**
	 * Actualise les infos grâce à la class Artiste
	 */
	public void actualiseArtiste() {
		this.artiste_nom_textfield.setText(artiste.getNom_artiste());
		this.artiste_prenom_textfield.setText(artiste.getPrenom_artiste());
		this.artiste_surnom_textfield.setText(artiste.getSurnom_artiste());
		this.artiste_dob_textfield.setText(artiste.getDob_artiste());
		this.metier_panel.metier.setArtiste(artiste);
	}

	/**
	 * actualise mon tableau et limite les entrée en param de lireTout()
	 */
	public void actualiseTab() {
		artistes = M_artiste.lireTout(50);
		artiste_aside.setDonnees(artistes);
	}

	public void actualiseListe() {
		ArrayList<Metier> metiers = M_artiste_metier_media.readAllJobsForOneArtiste(artiste);
		metier_panel.setArtiste_metiers_list(metiers);
	}

	/**
	 * Class Interne Permet de bindé le this dans l'event
	 * 
	 * @author ROM.VAN_DAM
	 *
	 */
	class MouseAdapterTableau extends MouseAdapter {
		C_artiste controller;

		public MouseAdapterTableau(C_artiste controller) {
			this.controller = controller;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = this.controller.getArtiste_result_table().rowAtPoint(e.getPoint());
			// Recupere la valeur de l'index de l'ArrayList.
			controller.artiste = (Artiste) controller.artistes.get(row);
			actualiseArtiste();
			actualiseListe();
		}
	}

	// === ACCESSEUR ET MUTATEUR === //
	public JTable getArtiste_result_table() {
		return artiste_result_table;
	}

	public void setArtiste_result_table(JTable artiste_result_table) {
		this.artiste_result_table = artiste_result_table;
	}

	public JTextField getArtiste_nom_textfield() {
		return artiste_nom_textfield;
	}

	public void setArtiste_nom_textfield(PlaceholderTextField artiste_nom_textfield) {
		this.artiste_nom_textfield = artiste_nom_textfield;
	}

	public JTextField getArtiste_prenom_textfield() {
		return artiste_prenom_textfield;
	}

	public void setArtiste_prenom_textfield(PlaceholderTextField artiste_prenom_textfield) {
		this.artiste_prenom_textfield = artiste_prenom_textfield;
	}

	public JTextField getArtiste_surnom_textfield() {
		return artiste_surnom_textfield;
	}

	public void setArtiste_surnom_textfield(PlaceholderTextField artiste_surnom_textfield) {
		this.artiste_surnom_textfield = artiste_surnom_textfield;
	}

	public JTextField getArtiste_dob_textfield() {
		return artiste_dob_textfield;
	}

	public void setArtiste_dob_textfield(PlaceholderTextField artiste_dob_textfield) {
		this.artiste_dob_textfield = artiste_dob_textfield;
	}

}
