package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import modele.ImageModule;
import modele.LinkModule;
import modele.Module;

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
		tabbedPane.addTab("Livres", null, livre_panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{3.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,3.5,0.5};
		livre_panel.setLayout(gbl_panel);
		
		String tabHeader[]={"Titre","ISBN","Année de sortie"};
		double elmsSize[]= {1.0,1.0,1.0,1.0};
		HeaderPanel livre_header=new HeaderPanel(livre_panel,"Cet onglet permet de renseigner des livres",tabHeader,elmsSize);
		
		//livre main panel//
		MainPanel livre_main = new MainPanel(livre_panel);
		//Add element
		livre_main.addModule(new LinkModule("Personne"), 0, 0);
		livre_main.addModule(new Module(), 1, 0);
		livre_main.addModule(new ImageModule(), 2, 0);
		
		livre_main.addModule(new LinkModule("Genre"), 0, 1);
		livre_main.addModule(new Module(), 1, 1);
		livre_main.addModule(new Module(), 2, 1);
		
		
		JPanel film_Panel = new JPanel();
		tabbedPane.addTab("Films", null, film_Panel, null);
		film_Panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel filmsLabel = new JLabel("Films");
		film_Panel.add(filmsLabel);
		
		JPanel musique_Panel = new JPanel();
		tabbedPane.addTab("Musiques", null, musique_Panel, null);
		musique_Panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel musiqueLabel = new JLabel("Musiques");
		musique_Panel.add(musiqueLabel);
		
		JPanel artiste_Panel = new JPanel();
		tabbedPane.addTab("Artistes", null, artiste_Panel, null);
		artiste_Panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel artistesLabel = new JLabel("Artistes");
		artiste_Panel.add(artistesLabel);
		
		JPanel valider_Panel = new JPanel();
		tabbedPane.addTab("A valider", null, valider_Panel, null);
		valider_Panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel avaliderLabel = new JLabel("A valider");
		valider_Panel.add(avaliderLabel);
		
		JPanel nettoyage_Panel = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, nettoyage_Panel, null);
		nettoyage_Panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel nettoyageLabel = new JLabel("Nettoyage BDD");
		nettoyage_Panel.add(nettoyageLabel);
	}
}
