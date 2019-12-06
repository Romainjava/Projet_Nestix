package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HeaderPanel extends JPanel {
	
	private JTextField livre_header_entete_textField;
	private ArrayList<JTextField> JtextArrray;
	
	public ArrayList<JTextField> getJtextArrray() {
		return JtextArrray;
	}

	public void setJtextArrray(ArrayList<JTextField> jtextArrray) {
		JtextArrray = jtextArrray;
	}

	public HeaderPanel(JPanel panel,String infoOnglet,String tab[],double elmsSize[]) {
		super();
		JtextArrray=new ArrayList<>();
		JPanel header_panel = new JPanel();
		header_panel.setBackground(Color.GREEN);
		GridBagConstraints gbc_header_panel = new GridBagConstraints();
		gbc_header_panel.fill = GridBagConstraints.BOTH;
		gbc_header_panel.gridx = 0;
		gbc_header_panel.gridy = 0;
		panel.add(header_panel, gbc_header_panel);
		GridBagLayout gbl_livre_header_panel = new GridBagLayout();
		gbl_livre_header_panel.columnWeights = elmsSize;
		gbl_livre_header_panel.rowWeights = new double[]{1.0, 1.0, 1.0};
		header_panel.setLayout(gbl_livre_header_panel);
		
		JLabel livre_header_info_label = new JLabel(infoOnglet);
		GridBagConstraints gbc_livre_header_info_label = new GridBagConstraints();
		gbc_livre_header_info_label.gridwidth = 3;
		gbc_livre_header_info_label.anchor = GridBagConstraints.WEST;
		gbc_livre_header_info_label.gridx = 0;
		gbc_livre_header_info_label.gridy = 0;
		header_panel.add(livre_header_info_label, gbc_livre_header_info_label);
		int i = 0;
		
		for (String entete : tab) {
			JLabel header_entete_label = new JLabel(entete);
			GridBagConstraints gbc_header_entete_label = new GridBagConstraints();
			gbc_header_entete_label.insets = new Insets(0, 0, 5, 5);
			gbc_header_entete_label.gridx = i;
			gbc_header_entete_label.gridy = 1;
			header_panel.add(header_entete_label, gbc_header_entete_label);
			
			livre_header_entete_textField = new JTextField();
			GridBagConstraints gbc_header_entete_textField = new GridBagConstraints();
			gbc_header_entete_textField.insets = new Insets(0, 0, 0, 5);
			gbc_header_entete_textField.fill = GridBagConstraints.BOTH;
			gbc_header_entete_textField.gridx = i;
			gbc_header_entete_textField.gridy = 2;
			header_panel.add(livre_header_entete_textField, gbc_header_entete_textField);
			livre_header_entete_textField.setColumns(10);
			JtextArrray.add(livre_header_entete_textField);
			i++;
		}
	}

}
