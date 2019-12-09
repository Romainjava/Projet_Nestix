package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Color;

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

		// === ICI AJOUT DU JTabbedPane === //
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		// === FIN AJOUT DU JTabbedPane === //

		// === ICI AJOUT LIVRE PANEL === //
		JPanel livres_panel = new JPanel();
		tabbedPane.addTab("Livres", null, livres_panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 3.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 3.5, 0.5 };
		livres_panel.setLayout(gbl_panel);

		// === ICI HEADER PANEL LIVRE === //
		String tabHeader[] = { "Titre", "ISBN", "Annee de sortie" };
		double elmsSize[] = { 1.0, 1.0, 1.0, 1.0 };
		HeaderPanel livre_header = new HeaderPanel(livres_panel, "Cet onglet permet de renseigner des livres", tabHeader,
				elmsSize);
		// === FIN HEADER PANEL LIVRE === //
		
		// === ICI MAIN PANEL LIVRE === //
		MainPanel livre_main = new MainPanel(livres_panel);
		//Add element
		livre_main.addModule(new LinkModule("Personne"), 0, 0);
		livre_main.addModule(new Module(), 1, 0);
		livre_main.addModule(new ImageModule(), 2, 0);

		livre_main.addModule(new LinkModule("Genre"), 0, 1);
		livre_main.addModule(new Module(), 1, 1);
		livre_main.addModule(new Module(), 2, 1);
		// === FIN MAIN PANEL LIVRE === //
		
		// === ICI FOOTER PANEL LIVRE === //
		String textBouton[] = {"Créer", "Modifier", "Supprimer"};
		double elmsSizeFooter[] = {1.0, 1.0, 1.0};
		FooterPanel livre_footer_panel = new FooterPanel(livres_panel, textBouton, elmsSizeFooter);
		// === FIN FOOTER PANEL LIVRE === //

		// === ICI ASIDE LIVRE PANEL === //
		AsidePanel livres_aside_panel = new AsidePanel(livres_panel);
		livres_aside_panel.setDonnees(new Object[][] { { "La boussole d'or", null, null, null }, });
		livres_aside_panel.ajouterLigne(new Object[] { "toto", null, null, null });
		// === FIN LIVRE ASIDE === //
		// === FIN LIVRE PANEL === //

		// === ICI AJOUT FILMS PANEL === //
		JPanel films_panel = new JPanel();
		tabbedPane.addTab("Films", null, films_panel, null);
		films_panel.setLayout(new GridLayout(1, 0, 0, 0));


		// === DEBUT ASIDE FILMS === //
		AsidePanel film_aside_panel = new AsidePanel(films_panel);
		film_aside_panel.setDonnees(new Object[][] { { "seigneur des anneaux", null, null, null }, });
		film_aside_panel.ajouterLigne(new Object[] { "toto", null, null, null });
		// === FIN ASIDE FILMS === //
		// === ICI FIN FILMS PANEL === //

		// === ICI MUSIQUES PANEL === //
		JPanel musiques_panel = new JPanel();
		tabbedPane.addTab("Musiques", null, musiques_panel, null);
		musiques_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// === ICI FIN MUSIQUES PANEL === //

		// === ICI ARTISTES PANEL === //
		JPanel artistes_panel = new JPanel();
		tabbedPane.addTab("Artistes", null, artistes_panel, null);
		artistes_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// === ICI FIN ARTISTES PANEL === //

		// === ICI A VALIDER PANEL === //
		JPanel a_valider_panel = new JPanel();
		tabbedPane.addTab("A valider", null, a_valider_panel, null);
		a_valider_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// === ICI FIN A VALIDER PANEL === //

		// === ICI AJOUT NETTOYAGE BDD === //
		JPanel netoyagebdd_panel = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, netoyagebdd_panel, null);
		netoyagebdd_panel.setLayout(new GridLayout(1, 0, 0, 0));

		// === ICI FIN AJOUT NETTOYAGE BDD === //

	}
}
