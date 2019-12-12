package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modele.I_recherche;
import modele.Media;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AsidePanel extends JPanel {
	

	private JPanel panel_recherche;
	private JLabel label_titre;
	private JTextField textfield_recherche;
	private JTable table_result;
	private Object donnees[][] = { { null, null, null, null } };
	private String entetes[] = { "Titre", "Genre", "Etat", "Date de sortie" };
	private DefaultTableModel tab_model = new DefaultTableModel(donnees, entetes) {
		//empêche l'edition du tableau en surchargeant la class pendant la définition
		public boolean isCellEditable(int row, int col) {
			return false;
		};
	};

	public DefaultTableModel getTab_model() {
		return tab_model;
	}

	public AsidePanel(JPanel panel_container) {

		this.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_aside_panel = new GridBagConstraints();
		gbc_aside_panel.gridheight = 3;
		gbc_aside_panel.gridwidth = 1;
		gbc_aside_panel.fill = GridBagConstraints.BOTH;
		gbc_aside_panel.gridx = 1;
		gbc_aside_panel.gridy = 0;
		panel_container.add(this, gbc_aside_panel);

		GridBagLayout gbl_aside_panel = new GridBagLayout();
		gbl_aside_panel.columnWeights = new double[] { 1.0 };
		gbl_aside_panel.rowWeights = new double[] { 0.2, 0.8 };
		this.setLayout(gbl_aside_panel);

		panel_recherche = new JPanel();
		GridBagConstraints gbc_aside_panel_recherche = new GridBagConstraints();
		gbc_aside_panel_recherche.fill = GridBagConstraints.BOTH;
		gbc_aside_panel_recherche.insets = new Insets(0, 0, 5, 0);
		gbc_aside_panel_recherche.gridx = 0;
		gbc_aside_panel_recherche.gridy = 0;
		this.add(panel_recherche, gbc_aside_panel_recherche);
		panel_recherche.setLayout(new MigLayout("", "[grow,center]", "[50px,center][20px,center]"));

		label_titre = new JLabel("Recherche");
		panel_recherche.add(label_titre, "flowx,cell 0 0");

		textfield_recherche = new JTextField();
		panel_recherche.add(textfield_recherche, "cell 0 1,growx,aligny center");
		textfield_recherche.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.add(scrollPane, gbc_scrollPane);

		table_result = new JTable();
		scrollPane.setViewportView(table_result);
		table_result.setModel(tab_model);
		
	}

	public String[] getEntetes() {
		return entetes;
	}

	public void setEntetes(String[] entetes) {
		this.entetes = entetes;
		tab_model.setColumnCount(0);
		for (String string : entetes) {
			tab_model.addColumn(string);
		}
	}

	public Object[][] getDonnees() {
		return donnees;
	}

	public void ajouterLigne(Object[] rowData) {
		tab_model.addRow(rowData);
	}

	public void setDonnees(Object[][] donnees) {
		//this.donnees = donnees;
		this.tab_model.setRowCount(0); // vide le tableau
		for (Object[] objects : donnees) {
			ajouterLigne(objects);
		}
	}
	
	public void setDonnees(ArrayList<I_recherche> donnees) {
		this.tab_model.setRowCount(0);
		for (I_recherche i_recherche : donnees) {
			ajouterLigne(i_recherche.toRowData());
		}
	}

	public JTable getTable_result() {
		return this.table_result;
	}
}
