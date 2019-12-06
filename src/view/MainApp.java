package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

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
		frame.setBounds(100, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Livres", null, panel, null);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel livresLabel = new JLabel("Livres");
		panel.add(livresLabel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Films", null, panel_1, null);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel filmsLabel = new JLabel("Films");
		panel_1.add(filmsLabel);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Musiques", null, panel_2, null);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel musiqueLabel = new JLabel("Musiques");
		panel_2.add(musiqueLabel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Artistes", null, panel_3, null);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel artistesLabel = new JLabel("Artistes");
		panel_3.add(artistesLabel);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("A valider", null, panel_4, null);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel avaliderLabel = new JLabel("A valider");
		panel_4.add(avaliderLabel);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Nettoyage BDD", null, panel_5, null);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel nettoyageLabel = new JLabel("Nettoyage BDD");
		panel_5.add(nettoyageLabel);
	}
}
