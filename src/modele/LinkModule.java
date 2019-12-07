package modele;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LinkModule extends Module{
	
	private ArrayList<TextListField> textListFieldList = new ArrayList<>();
	JPanel content_panel;
	
	public LinkModule(String ptitre) {
		super();
		
		this.setBackground(Color.YELLOW);
		
		//Element
		JLabel title_label = new JLabel(ptitre);
		JButton more_btn = new JButton("+");
		content_panel = new JPanel();
				
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0};
	    gbl.rowWeights = new double[] {0.5,
	    								0.5,
	    								2.0};
	    this.setLayout(gbl);
	    content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.PAGE_AXIS));
	    
	    
	    gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		this.add(title_label, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(more_btn, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(content_panel, gbc);
	}
	
	public void addTextListField() {
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.LINE_AXIS));
		
		TextListField textListField = new TextListField();
		JButton remove_btn = new JButton("-");
		
		this.textListFieldList.add(textListField);
		
		box.add(textListField);
		box.add(remove_btn);
		content_panel.add(box);
	}

}
