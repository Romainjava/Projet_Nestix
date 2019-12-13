package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Artiste;
import modele.I_recherche;
import modele.M_artiste;
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

public class C_artiste {
	private JPanel artiste_panel;

	// DONNEES
	Artiste artiste = new Artiste();
	ArrayList<I_recherche> artistes = new ArrayList<>();
	// COMPOSANT
	JTable artiste_result_table;
	JTextField artiste_nom_textfield;
	JTextField artiste_prenom_textfield;
	JTextField artiste_surnom_textfield;
	JTextField artiste_dob_textfield;
	AsidePanel artiste_aside;

	public C_artiste(JPanel artiste_panel) {
		this.artiste_panel = artiste_panel;
		ajouteHeader();
		ajouteTab();
		ajoutMainPanel();
		footerPanel();
	}

	public void ajouteHeader() {
		String[] tabHeader = { "Nom", "Prenom", "Date de naissance", "Surnom" };
		double[] elmSize = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel artiste_header = new HeaderPanel(this.artiste_panel, "Cet onglet permet de renseigner des livres",
				tabHeader, elmSize);
		ArrayList<JTextField> liste = artiste_header.getJtextArrray();
		this.artiste_nom_textfield = liste.get(0);
		this.artiste_prenom_textfield = liste.get(1);
		this.artiste_surnom_textfield = liste.get(3);
		this.artiste_dob_textfield = liste.get(2);
	}

	public void ajoutMainPanel() {
		MainPanel artiste_main = new MainPanel(this.artiste_panel);
		// ADD ELEMENT
		artiste_main.addModule(new DualLinkModule("Media"), 0, 0);
		artiste_main.addModule(new Module(), 1, 0);
		artiste_main.addModule(new ImageModule(), 2, 0);

		GridPanel relationComple = new GridPanel(new double[] { 1.0, 1.0 }, new double[] { 1.0, 1.0, 1.0 });
		artiste_main.add(relationComple, artiste_main.addElement(1, 1));
		relationComple.add(new ComboListField(new String[] { "etat1", "etat2", "etat3" }),
				relationComple.addElement(0, 0));
		relationComple.add(new TextListField(), relationComple.addElement(0, 1));
		relationComple.add(new TextListField(), relationComple.addElement(1, 1));
		relationComple.add(new TextListField(), relationComple.addElement(0, 2));
	}

	public void ajouteTab() {
		artiste_aside = new AsidePanel(this.artiste_panel);
		artiste_aside.setEntetes(new String[] { "Nom", "Prenom", "Surnom", "Etat", "Date de naissance" });
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
				if(artiste.getSurnom_artiste().equals("")) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention Surnom est obligatoire");
				}else if(artiste.verifDateForm() == false) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention format date doit être : jj/mm/aaaa");
				}
				else {
					if(artiste.creation() == false) {
						JOptionPane.showMessageDialog(artiste_panel, "Creation de l'artiste échoué car il existe déjà ce surnom");
					}
					actualiseTab();
				}
				actualiseTab();
			}
		});

		/**
		 * Event btn Modifier un artiste avec requête modifier dans M_artiste
		 */
		btn.get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recupDonneeArtiste();
				if(artiste.getSurnom_artiste().equals("")) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention Surnom est obligatoire");
				}else if(artiste.verifDateForm() == false) {
					JOptionPane.showMessageDialog(artiste_panel, "Attention format date doit être : jj/mm/aaaa");
				}
				else {
					if(artiste.modification() == false) {
						JOptionPane.showMessageDialog(artiste_panel, "Modification de l'artiste échoué car il existe déjà ce surnom");
					}
					actualiseTab();
				}	
			}
		});
		
		btn.get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_artiste.supprime(artiste);
				actualiseTab();
			}
		});

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
			controller.artiste = (Artiste) controller.artistes.get(row);
			
			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String nom = (String) this.controller.getArtiste_result_table().getValueAt(row, 0);
			String prenom = (String) this.controller.getArtiste_result_table().getValueAt(row, 1);
			String surnom = (String) this.controller.getArtiste_result_table().getValueAt(row, 2);
			String dob = (String) this.controller.getArtiste_result_table().getValueAt(row, 4);

			this.controller.getArtiste_nom_textfield().setText(nom);
			this.controller.getArtiste_prenom_textfield().setText(prenom);
			this.controller.getArtiste_surnom_textfield().setText(surnom);
			this.controller.getArtiste_dob_textfield().setText(dob);
		}
	}
	
	/**
	 * actualise mon tableau et limite les entrée en param de lireTout()
	 */
	public void actualiseTab() {
		artistes = M_artiste.lireTout(50);
		artiste_aside.setDonnees(artistes);
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

	public void setArtiste_nom_textfield(JTextField artiste_nom_textfield) {
		this.artiste_nom_textfield = artiste_nom_textfield;
	}

	public JTextField getArtiste_prenom_textfield() {
		return artiste_prenom_textfield;
	}

	public void setArtiste_prenom_textfield(JTextField artiste_prenom_textfield) {
		this.artiste_prenom_textfield = artiste_prenom_textfield;
	}

	public JTextField getArtiste_surnom_textfield() {
		return artiste_surnom_textfield;
	}

	public void setArtiste_surnom_textfield(JTextField artiste_surnom_textfield) {
		this.artiste_surnom_textfield = artiste_surnom_textfield;
	}

	public JTextField getArtiste_dob_textfield() {
		return artiste_dob_textfield;
	}

	public void setArtiste_dob_textfield(JTextField artiste_dob_textfield) {
		this.artiste_dob_textfield = artiste_dob_textfield;
	}

}
