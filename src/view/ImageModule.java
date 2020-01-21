package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ImageModule extends Module{
	
	JTextField textField = new JTextField();
	
	public ImageModule() {
		super();
			
		
		//Element
		LabelCustom textField_label = new LabelCustom("Titre de l'image");
		textField = new JTextField();
		
		LabelCustom btn_label = new LabelCustom("Ajouter une image");
		JButton btn = new JButton("Parcourir");

		//Layout		
		MigLayout gbl = new MigLayout("", "[grow]", "[][][n:500px][]");

	    this.setLayout(gbl);
	    
		this.add(textField_label, "cell 0 0,alignx center");

		this.add(textField, "cell 0 1,growx");

		JPanel panel_preview = new JPanel();
		panel_preview.setBackground(Color.GRAY);
		this.add(panel_preview, "cell 0 2,grow");
		
		this.add(btn_label, "cell 0 3,alignx center");

		this.add(btn, "cell 0 4,grow");
		
	}

}
