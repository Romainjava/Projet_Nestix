package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controller.C_Livre;
import controller.C_artiste;
import controller.C_film;
import controller.C_musique;
import modele.ConnexionBDD;
import modele.Musique;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

public class MainApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 0, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		// AJOUT DU JTabbedPane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		// ====== DEBUT LIVRE PANEL ====== //

		JPanel livres_panel = new JPanel();		
		GridBagLayout gbl_livres_panel = new GridBagLayout();
		gbl_livres_panel.columnWeights = new double[] { 3.0, 1.0 };
		gbl_livres_panel.rowWeights = new double[] { 1.0, 3.5, 0.5 };
		livres_panel.setLayout(gbl_livres_panel);
		tabbedPane.addTab("Livres", null, livres_panel, null);
		// === Construction du livre panel === //
		C_Livre livres_controler_panel = new C_Livre(livres_panel);
		
		// ===== FIN LIVRE ===== //

		// ====== DEBUT FILMS PANEL ====== //

		JPanel films_panel = new JPanel();	
		GridBagLayout gbl_films_panel = new GridBagLayout();
		gbl_films_panel.columnWeights = new double[] { 3.0, 1.0 };
		gbl_films_panel.rowWeights = new double[] { 1.0, 3.5, 0.5 };
		films_panel.setLayout(gbl_films_panel);
		tabbedPane.addTab("Films", null, films_panel, null);
		
		C_film film_controler_panel = new C_film(films_panel); 

		// ===== FIN FILMS ===== //

		// ====== DEBUT MUSIQUES PANEL ====== //

		JPanel musiques_panel = new JPanel();
		GridBagLayout gbl_musique_panel = new GridBagLayout();
		gbl_musique_panel.columnWeights = new double[] { 3.0 };
		gbl_musique_panel.rowWeights = new double[] { 1.0, 3.5 };
		musiques_panel.setLayout(gbl_musique_panel);
		tabbedPane.addTab("Musique", null, musiques_panel, null);
		// === Construction du livre panel === //
		C_musique musique_controler_panel = new C_musique(musiques_panel);

		// ===== FIN MUSIQUES ===== //

		// ====== DEBUT ARTISTES PANEL ====== //

		JPanel artistes_panel = new JPanel();
		GridBagLayout gbl_artistes_panel = new GridBagLayout();
		gbl_artistes_panel.columnWeights = new double[] { 3.0, 1.0 };
		gbl_artistes_panel.rowWeights = new double[] { 1.0, 3.5, 0.5 };
		artistes_panel.setLayout(gbl_artistes_panel);
		tabbedPane.addTab("Artistes", null, artistes_panel, null);		
		C_artiste artiste_controler_panel = new C_artiste(artistes_panel);

		// ===== FIN ARTISTES ===== //

		// ====== DEBUT A VALIDER PANEL ====== //

		JPanel a_valider_panel = new JPanel();
		tabbedPane.addTab("A valider", null, a_valider_panel, null);
		a_valider_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// ====== FIN A VALIDER ===== //

		// ====== DEBUT NETTOYAGE BDD PANEL ====== //

		JPanel netoyagebdd_panel = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, netoyagebdd_panel, null);
		netoyagebdd_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// ===== FIN NETTOYAGE BDD ===== //

	}
}
