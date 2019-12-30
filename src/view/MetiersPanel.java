package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import view.E_Metiers;
import view.E_TypesMedia;
import modele.Metier;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class MetiersPanel extends Module {
	public JTextField media_titre_textField;
	private DefaultListModel<Metier> modelList;
	public JButton metier_add_button;
	public JButton metier_reset_button;
	public JComboBox<?> metier_comboBox;
	public JComboBox<?> type_comboBox;
	public JButton btnSupprimer;
	public JList<Metier> artiste_metiers_list;
	public Metier metier = new Metier();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public MetiersPanel() {

		this.setLayout(new MigLayout("", "[600px]", "[][][]"));
		/* == Panel d'ajout == */
		JPanel metier_ajouter_panel = new JPanel();
		metier_ajouter_panel.setLayout(new MigLayout("","50[]20[]20[]20[]","[][]")); // Ajout des espaces inter-cellule
		this.add(metier_ajouter_panel, "cell 0 0,alignx left,aligny top,grow");
		
		//1er ligne du panel ajouter
		JLabel lblTitre = new JLabel("Titre");
		metier_ajouter_panel.add(lblTitre,"cell 0 0, alignx right");

		media_titre_textField = new JTextField();
		media_titre_textField.setColumns(20);
		metier_ajouter_panel.add(media_titre_textField,"cell 1 0,span 3");
		
		//2eme ligne du panel ajouter
		type_comboBox = new JComboBox();
		type_comboBox.setModel(new DefaultComboBoxModel(E_TypesMedia.values()));
		metier_ajouter_panel.add(type_comboBox,"cell 0 1,alignx center");

		metier_comboBox = new JComboBox();
		metier_comboBox.setModel(new DefaultComboBoxModel(E_Metiers.values()));
		metier_ajouter_panel.add(metier_comboBox,"cell 1 1,alignx center");

		metier_add_button =  new JButton("+");
		metier_ajouter_panel.add(metier_add_button,"cell 2 1,alignx center");
		
		metier_reset_button = new JButton("RESET");
		metier_ajouter_panel.add(metier_reset_button,"cell 3 1,alignx center");
		
		/* == Fin de Panel d'ajout == */
		
		JPanel metiers_liste_panel = new JPanel();
		this.add(metiers_liste_panel, "cell 0 1,alignx center,aligny top");
		metiers_liste_panel.setLayout(new MigLayout("", "[300px]", "[][200px:n]"));

		JLabel lblListeDesMdias = new JLabel("Liste des médias :");
		metiers_liste_panel.add(lblListeDesMdias, "cell 0 0,alignx left,aligny center");

		JScrollPane scrollPane = new JScrollPane();
		metiers_liste_panel.add(scrollPane, "cell 0 1,grow");

		artiste_metiers_list = new JList();
		artiste_metiers_list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		modelList = new DefaultListModel();
		artiste_metiers_list.setModel(modelList);

		scrollPane.setViewportView(artiste_metiers_list);

		btnSupprimer = new JButton("Supprimer");
		this.add(btnSupprimer, "cell 0 2,alignx center");
		
		
	}

	// === MUTATEUR ET ACCESSEUR === //
	/**
	 * 
	 */
	public void getInfoMetier() {
		E_Metiers M = (E_Metiers) metier_comboBox.getModel().getSelectedItem();
		metier.setNom(M.label);
		metier.setId(M.id);
	}
	/**
	 * ajoute le titre et le type du media par rapport au jtextfield du panel
	 */
	public void getInfoMedia() {
		E_TypesMedia T = (E_TypesMedia) type_comboBox.getModel().getSelectedItem();
		metier.addMedia(media_titre_textField.getText(), T.label);
	}

	/**
	 * Update le model puis insert des données
	 * 
	 * @param liste_metier_media:ArrayList<Metier>
	 */
	public void setArtiste_metiers_list(ArrayList<Metier> liste_metier_media) {
		modelList.clear();
		for (Metier metier : liste_metier_media) {
			modelList.addElement(metier);
		}
	}

	public Metier getMetier() {
		return metier;
	}

	/**
	 * Ajoute une ligne à la Jlist
	 * 
	 * @param row:String
	 */
	public void addRow(Metier row) {
		modelList.addElement(row);
	}
	
	// ==== MUTATEUR && ACCESSEUR ==== //
	
	public JTextField getMedia_titre_textField() {
		return media_titre_textField;
	}

	public void setMedia_titre_textField(JTextField media_titre_textField) {
		this.media_titre_textField = media_titre_textField;
	}

}
