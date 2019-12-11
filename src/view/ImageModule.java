package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageModule extends Module{
	
	JTextField textField = new JTextField();
	
	public ImageModule() {
		super();
			
		
		//Element
		JLabel textField_label = new JLabel("Titre de l'image");
		textField = new JTextField();
		
		JLabel btn_label = new JLabel("Ajouter une image");
		JButton btn = new JButton("Parcourir");

		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0};
	    gbl.rowWeights = new double[] {1.0,
	    								1.0,
	    								1.0,
	    								1.0};
	    this.setLayout(gbl);
	    
	    gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(textField_label, gbc);
		gbc.gridy = 1;
		this.add(textField, gbc);
		gbc.gridy = 2;
		this.add(btn_label, gbc);
		gbc.gridy = 3;
		this.add(btn, gbc);
		
	}

}
