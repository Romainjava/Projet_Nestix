package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class MainApp {

	private JFrame frame;
	private JTextField livre_aside_recherche_textfield;
	private JTable livre_aside_tableau_jtable;

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
		frame.setBounds(100, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		JPanel films_panel = new JPanel();
		tabbedPane.addTab("Films", null, films_panel, null);
		films_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel filmsLabel = new JLabel("Films");
		films_panel.add(filmsLabel);
		// === DEBUT ASIDE FILMS === //
		AsidePanel film_aside_panel = new AsidePanel(films_panel);
		film_aside_panel.setDonnees(new Object[][] {
				{"seigneur des anneaux", null, null, null},
			});
		film_aside_panel.ajouterLigne(new Object[]{"toto",null,null,null});
		// === FIN ASIDE FILMS === //
		
		JPanel musiques_panel = new JPanel();
		tabbedPane.addTab("Musiques", null, musiques_panel, null);
		musiques_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel musiqueLabel = new JLabel("Musiques");
		musiques_panel.add(musiqueLabel);

		JPanel artistes_panel = new JPanel();
		tabbedPane.addTab("Artistes", null, artistes_panel, null);
		artistes_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel artistesLabel = new JLabel("Artistes");
		artistes_panel.add(artistesLabel);

		JPanel a_valider_panel = new JPanel();
		tabbedPane.addTab("A valider", null, a_valider_panel, null);
		a_valider_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel avaliderLabel = new JLabel("A valider");
		a_valider_panel.add(avaliderLabel);

		// ==== ONGLET LIVRE ==== //
		JPanel livres_panel = new JPanel();
		tabbedPane.addTab("Livres", null, livres_panel, null);
		GridBagLayout gbl_livres_panel = new GridBagLayout();
		gbl_livres_panel.columnWeights = new double[] {3.0 };
		gbl_livres_panel.rowWeights = new double[] { 1.0, 3.0, 2.0 };
		livres_panel.setLayout(gbl_livres_panel);

		JPanel livres_header_panel = new JPanel();
		livres_header_panel.setBackground(Color.GREEN);
		GridBagConstraints gbc_livres_header_panel = new GridBagConstraints();
		gbc_livres_header_panel.insets = new Insets(0, 0, 5, 5);
		gbc_livres_header_panel.fill = GridBagConstraints.BOTH;
		gbc_livres_header_panel.gridx = 0;
		gbc_livres_header_panel.gridy = 0;
		livres_panel.add(livres_header_panel, gbc_livres_header_panel);
		
		JPanel livres_main_panel = new JPanel();
		livres_main_panel.setBackground(Color.RED);
		GridBagConstraints gbc_livres_main_panel = new GridBagConstraints();
		gbc_livres_main_panel.insets = new Insets(0, 0, 0, 5);
		gbc_livres_main_panel.fill = GridBagConstraints.BOTH;
		gbc_livres_main_panel.gridx = 0;
		gbc_livres_main_panel.gridy = 1;
		livres_panel.add(livres_main_panel, gbc_livres_main_panel);
		
		
		// === DEBUT ASIDE === //
		AsidePanel livres_aside_panel = new AsidePanel(livres_panel);
		livres_aside_panel.setDonnees(new Object[][] {
			{"La boussole d'or", null, null, null},
		});
		livres_aside_panel.ajouterLigne(new Object[]{"toto",null,null,null});
		// === FIN ASIDE

		JPanel livres_footer_panel = new JPanel();
		livres_footer_panel.setBackground(Color.CYAN);
		GridBagConstraints gbc_livres_footer_panel = new GridBagConstraints();
		gbc_livres_footer_panel.insets = new Insets(0, 0, 0, 5);
		gbc_livres_footer_panel.fill = GridBagConstraints.BOTH;
		gbc_livres_footer_panel.gridx = 0;
		gbc_livres_footer_panel.gridy = 2;
		livres_panel.add(livres_footer_panel, gbc_livres_footer_panel);

		JPanel netoyagebdd_panel = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, netoyagebdd_panel, null);
		netoyagebdd_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel nettoyageLabel = new JLabel("Nettoyage BDD");
		netoyagebdd_panel.add(nettoyageLabel);
	}
}
