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
		frame.setBounds(100, 0, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		// === ICI AJOUT DU JTabbedPane === //
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		// === FIN AJOUT DU JTabbedPane === //

		// === ICI AJOUT LIVRE PANEL === //
		JPanel livre_panel = new JPanel();
		tabbedPane.addTab("Livres", null, livre_panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{3.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,3.5,0.5};
		livre_panel.setLayout(gbl_panel);

		// === ICI HEADER PANEL LIVRE === //
		String tabHeader[]={"Titre","ISBN","Ann�e de sortie"};
		double elmsSize[]= {1.0,1.0,1.0,1.0};
		HeaderPanel livre_header=new HeaderPanel(livre_panel,"Cet onglet permet de renseigner des livres",tabHeader,elmsSize);
		// === FIN HEADER PANEL LIVRE === //
		// === FIN LIVRE PANEL === //


		// === ICI AJOUT FILMS PANEL === //
		JPanel films_panel = new JPanel();
		tabbedPane.addTab("Films", null, films_panel, null);
		films_panel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel films_label = new JLabel("Films");
		films_panel.add(films_label);

		// === DEBUT ASIDE FILMS === //
		AsidePanel film_aside_panel = new AsidePanel(films_panel);
		film_aside_panel.setDonnees(new Object[][] {
				{"seigneur des anneaux", null, null, null},
			});
		film_aside_panel.ajouterLigne(new Object[]{"toto",null,null,null});
		// === FIN ASIDE FILMS === //
		// === ICI FIN FILMS PANEL === //
		
		// === ICI MUSIQUES PANEL === //
		JPanel musiques_panel = new JPanel();
		tabbedPane.addTab("Musiques", null, musiques_panel, null);
		musiques_panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel musiques_label = new JLabel("Musiques");
		musiques_panel.add(musiques_label);
		// === ICI FIN MUSIQUES PANEL === //
		
		// === ICI ARTISTES PANEL === //
		JPanel artistes_panel = new JPanel();
		tabbedPane.addTab("Artistes", null, artistes_panel, null);
		artistes_panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel artistes_label = new JLabel("Artistes");
		artistes_panel.add(artistes_label);
		// === ICI FIN ARTISTES PANEL === //

		// === ICI A VALIDER PANEL === //
		JPanel a_valider_panel = new JPanel();
		tabbedPane.addTab("A valider", null, a_valider_panel, null);
		a_valider_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel avaliderLabel = new JLabel("A valider");
		a_valider_panel.add(avaliderLabel);
		// === ICI FIN A VALIDER PANEL === //

		// ==== ICI LIVRE PANEL ==== //
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
		
		
		// === DEBUT LIVRES ASIDE === //
		AsidePanel livres_aside_panel = new AsidePanel(livres_panel);
		livres_aside_panel.setDonnees(new Object[][] {
			{"La boussole d'or", null, null, null},
		});
		livres_aside_panel.ajouterLigne(new Object[]{"toto",null,null,null});
		// === FIN LIVRE ASIDE === //
		
		// === DEBUT LIVRES FOOTER PANEL === //
		JPanel livres_footer_panel = new JPanel();
		livres_footer_panel.setBackground(Color.CYAN);
		GridBagConstraints gbc_livres_footer_panel = new GridBagConstraints();
		gbc_livres_footer_panel.insets = new Insets(0, 0, 0, 5);
		gbc_livres_footer_panel.fill = GridBagConstraints.BOTH;
		gbc_livres_footer_panel.gridx = 0;
		gbc_livres_footer_panel.gridy = 2;
		livres_panel.add(livres_footer_panel, gbc_livres_footer_panel);
		// === ICI FIN LIVRES PANEL === //

		// === ICI AJOUT NETTOYAGE BDD === //
		JPanel netoyagebdd_panel = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, netoyagebdd_panel, null);
		netoyagebdd_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel nettoyageLabel = new JLabel("Nettoyage BDD");
		netoyagebdd_panel.add(nettoyageLabel);
		// === ICI FIN AJOUT NETTOYAGE BDD === //
	}
}
