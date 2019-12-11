package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Artiste;
import view.AsidePanel;
import view.FooterPanel;
import view.HeaderPanel;
import view.ImageModule;
import view.LinkModule;
import view.MainPanel;
import view.Module;

public class C_artiste {
	private JPanel artiste_panel;

	// DONNEES
	Artiste artiste = new Artiste();
	ArrayList<Artiste> artistes = new ArrayList<>();
	// COMPOSANT
	JTable artiste_result_table;
	JTextField artiste_nom_textfield;

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
	}

	public void ajoutMainPanel() {
		MainPanel artiste_main = new MainPanel(this.artiste_panel);
		// ADD ELEMENT
		artiste_main.addModule(new LinkModule("Personne"), 0, 0);
		artiste_main.addModule(new Module(), 1, 0);
		artiste_main.addModule(new ImageModule(), 2, 0);

		artiste_main.addModule(new LinkModule("Genre"), 0, 1);
		artiste_main.addModule(new Module(), 1, 1);
		artiste_main.addModule(new Module(), 2, 1);
	}

	public void ajouteTab() {
		AsidePanel artiste_aside = new AsidePanel(this.artiste_panel);
		artiste_aside.setEntetes(new String[] { "Nom", "Prenom", "Etat", "Date de naissance" });
		artiste_aside.setDonnees(new Object[][] { { "Collins", "Phil", "Valide", "1951" }, });
		artiste_aside.ajouterLigne(new Object[] { "Doe", "John", "en attente", "2010" });

		// AJOUT EVENT
		this.artiste_result_table = artiste_aside.getTable_result();
		artiste_result_table.addMouseListener(new MouseAdapterTableau(this));
	}

	public void footerPanel() {
		String textBouton[] = { "Creer", "Modifier", "Supprimer" };
		double elmsSizeFooter[] = { 1.0, 1.0, 1.0 };
		FooterPanel artiste_footer = new FooterPanel(this.artiste_panel, textBouton, elmsSizeFooter);
	}

	/**
	 * Actualise les infos grâce à la class Artiste
	 */
	public void actualiseArtiste() {
		// ACTUALISE NOM
		this.artiste_nom_textfield.setText(artiste.getNom_artiste());
		// TODO RESTE A FAIRE PRENOM ETAT ETC...
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
			// int column =
			// this.controller.getArtiste_result_table().columnAtPoint(e.getPoint());

			// "getAtValue" : Permet de prendre la valeur de la case ( row , column )
			String nom = (String) this.controller.getArtiste_result_table().getValueAt(row, 0);
			this.controller.getArtiste_nom_textfield().setText(nom);
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

	public void setArtiste_nom_textfield(JTextField artiste_nom_textfield) {
		this.artiste_nom_textfield = artiste_nom_textfield;
	}

}
