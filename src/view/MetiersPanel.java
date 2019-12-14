package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MetiersPanel extends JPanel {
	private JTextField media_titre_textField;
	private DefaultListModel<String> modelList;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public MetiersPanel() {
		setLayout(new MigLayout("", "[498px]", "[39px][44px][]"));

		JPanel metier_ajouter_panel = new JPanel();
		add(metier_ajouter_panel, "cell 0 0,alignx left,aligny top");

		JLabel lblTitre = new JLabel("Titre");
		metier_ajouter_panel.add(lblTitre);

		media_titre_textField = new JTextField();
		metier_ajouter_panel.add(media_titre_textField);
		media_titre_textField.setColumns(10);

		JComboBox type_comboBox = new JComboBox();
		type_comboBox.setModel(new DefaultComboBoxModel(E_TypesMedia.values()));
		metier_ajouter_panel.add(type_comboBox);
		
		JComboBox metier_comboBox = new JComboBox();
		metier_comboBox.setModel(new DefaultComboBoxModel(E_Metiers.values()));
		metier_ajouter_panel.add(metier_comboBox);

		JButton metier_add_button = new JButton("+");
		metier_ajouter_panel.add(metier_add_button);
		metier_add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String data_textfield = media_titre_textField.getText();
			data_textfield += " - " + type_comboBox.getModel().getSelectedItem();
			data_textfield += " - " + metier_comboBox.getModel().getSelectedItem();
			addRow(data_textfield);
			}
		});

		JPanel metiers_liste_panel = new JPanel();
		add(metiers_liste_panel, "cell 0 1,alignx center,aligny top");
		metiers_liste_panel.setLayout(new MigLayout("", "[300px]", "[][200px:n]"));

		JLabel lblListeDesMdias = new JLabel("Liste des médias :");
		metiers_liste_panel.add(lblListeDesMdias, "cell 0 0,alignx left,aligny center");

		JScrollPane scrollPane = new JScrollPane();
		metiers_liste_panel.add(scrollPane, "cell 0 1,grow");

		JList artiste_metiers_list = new JList();
		artiste_metiers_list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		modelList = new DefaultListModel();
		artiste_metiers_list.setModel(modelList);
		
		
		scrollPane.setViewportView(artiste_metiers_list);

		JButton btnSupprimer = new JButton("Supprimer");
		add(btnSupprimer, "cell 0 2,alignx center");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

	}

	
	// === MUTATEUR ET ACCESSEUR === //

	/**
	 * Update le model puis insert des données
	 * @param liste_metier_media:ArrayList<String>
	 */
	public void setArtiste_metiers_list(ArrayList<String> liste_metier_media) {
		modelList.clear();
		for (String string : liste_metier_media) {
			modelList.addElement(string);
		}
	}
	
	/**
	 * Ajoute une ligne à la Jlist
	 * @param row:String
	 */
	public void addRow(String row) {
		modelList.addElement(row);
	}

}
