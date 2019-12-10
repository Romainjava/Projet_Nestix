package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controller.C_Livre;
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
		frame.setBounds(100, 0, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		// AJOUT DU JTabbedPane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		// ====== DEBUT LIVRE PANEL ====== //

		JPanel livres_panel = new JPanel();		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 3.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 3.5 };
		livres_panel.setLayout(gbl_panel);
		tabbedPane.addTab("Livres", null, livres_panel, null);
		// === Construction du livre panel === //
		C_Livre livres_controler_panel = new C_Livre(livres_panel);

		// ===== FIN LIVRE ===== //

		// ====== DEBUT FILMS PANEL ====== //

		JPanel films_panel = new JPanel();
		tabbedPane.addTab("Films", null, films_panel, null);
		films_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// === DEBUT ASIDE FILMS === //
		AsidePanel film_aside_panel = new AsidePanel(films_panel);
		film_aside_panel.setDonnees(new Object[][] { { "seigneur des anneaux", null, null, null }, });
		film_aside_panel.ajouterLigne(new Object[] { "toto", null, null, null });
		// == FIN ASIDE == //

		// ===== FIN FILMS ===== //

		// ====== DEBUT MUSIQUES PANEL ====== //

		JPanel musiques_panel = new JPanel();
		tabbedPane.addTab("Musiques", null, musiques_panel, null);
		musiques_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// ===== FIN MUSIQUES ===== //

		// ====== DEBUT ARTISTES PANEL ====== //

		JPanel artistes_panel = new JPanel();
		tabbedPane.addTab("Artistes", null, artistes_panel, null);
		artistes_panel.setLayout(new GridLayout(1, 0, 0, 0));

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
