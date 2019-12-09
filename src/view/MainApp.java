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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel livre_panel = new JPanel();
		tabbedPane.addTab("Livre", null, livre_panel, null);
		GridBagLayout gbl_livre_panel = new GridBagLayout();
		gbl_livre_panel.columnWeights = new double[]{2.0, 1.0};
		gbl_livre_panel.rowWeights = new double[]{1.1, 3.0, 0.9};
		livre_panel.setLayout(gbl_livre_panel);
		
		// KEVIN
		JPanel livre_header_panel = new JPanel();
		livre_header_panel.setBackground(Color.GREEN);
		GridBagConstraints gbc_livre_header_panel = new GridBagConstraints();
		gbc_livre_header_panel.fill = GridBagConstraints.BOTH;
		gbc_livre_header_panel.gridx = 0;
		gbc_livre_header_panel.gridy = 0;
		livre_panel.add(livre_header_panel, gbc_livre_header_panel);
		
		// VINCENT
		JPanel livre_main_panel = new JPanel();
		livre_main_panel.setBackground(Color.RED);
		GridBagConstraints gbc_livre_main_panel = new GridBagConstraints();
		gbc_livre_main_panel.fill = GridBagConstraints.BOTH;
		gbc_livre_main_panel.gridx = 0;
		gbc_livre_main_panel.gridy = 1;
		livre_panel.add(livre_main_panel, gbc_livre_main_panel);
		
		// THIBAULT
		String textBouton[] = {"Créer", "Modifier", "Supprimer"};
		double elmsSize[] = {1.0, 1.0, 1.0};
		FooterPanel livre_footer_panel = new FooterPanel(livre_panel, textBouton, elmsSize);
		
		
		// ROMAIN
		JPanel livre_aside_panel = new JPanel();
		livre_aside_panel.setBackground(Color.GRAY);
		GridBagConstraints gbc_livre_aside_panel = new GridBagConstraints();
		gbc_livre_aside_panel.gridheight = 3;
		gbc_livre_aside_panel.fill = GridBagConstraints.BOTH;
		gbc_livre_aside_panel.gridx = 1;
		gbc_livre_aside_panel.gridy = 0;
		livre_panel.add(livre_aside_panel, gbc_livre_aside_panel);
		
		JPanel film_panel = new JPanel();
		tabbedPane.addTab("Film", null, film_panel, null);
		
		JPanel musique_panel = new JPanel();
		tabbedPane.addTab("Musique", null, musique_panel, null);
		
		JPanel artiste_panel = new JPanel();
		tabbedPane.addTab("Artiste", null, artiste_panel, null);
		
		JPanel valider_panel = new JPanel();
		tabbedPane.addTab("A valider", null, valider_panel, null);
		
		JPanel nettoyage_panel = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, nettoyage_panel, null);
	}
}
