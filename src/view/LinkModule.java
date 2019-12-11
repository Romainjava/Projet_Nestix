package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

//import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LinkModule extends Module implements ActionListener{
	
	private ArrayList<String> text_list;
//	JPanel content_panel;
	JButton more_btn = new JButton("+");
	JButton less_btn = new JButton("-");
	TextListField text_list_field;
	JScrollPane content_scroll;
	JList content_list;
	
	public LinkModule(String ptitre) {
		super();
		
		this.setBackground(Color.YELLOW);
		
		//Element
		JLabel title_label = new JLabel(ptitre);
		this.more_btn = new JButton("+");
//		content_panel = new JPanel();
		text_list_field = new TextListField();
		content_scroll = new JScrollPane();
		String[] data = {"item1", "item2", "item3"};
		this.text_list = new ArrayList<String>(Arrays.asList(data));
		this.content_list = new JList(data);
		System.out.println(text_list);
		
		//Event
		this.more_btn.addActionListener(this);
		this.less_btn.addActionListener(this);
				
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0};
	    gbl.rowWeights = new double[] {1.0,
	    								1.0,
	    								1.0,
	    								4.0};
	    this.setLayout(gbl);
//	    content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.PAGE_AXIS));
	    
	    
	    gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		this.add(title_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(this.more_btn, gbc);
		gbc.gridx = 3;
		this.add(this.less_btn, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(this.text_list_field, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(content_scroll, gbc);
		content_scroll.setViewportView(content_list);
	}
	
	public void addTextListField() {
//		JPanel box = new JPanel();
//		box.setLayout(new BoxLayout(box, BoxLayout.LINE_AXIS));
//		
//		TextListField textListField = new TextListField();
//		JButton remove_btn = new JButton("-");
//		
//		this.textListFieldList.add(textListField);
//		
//		box.add(textListField);
//		box.add(remove_btn);
//		content_panel.add(box);

//		content_panel.validate();
//		content_panel.repaint();
		if(!text_list_field.getText().equals("")) {
			this.text_list.add(text_list_field.getText());
			text_list_field.setText("");
//			System.out.println(text_list);
			
			this.content_list = new JList(text_list.toArray());
//			System.out.println(this.content_list.getModel().getSize());
			
			content_scroll.setViewportView(content_list);
		}

	}
	
	public void removeTextListField() {
		
		int[] index_list = content_list.getSelectedIndices();

		for(int i = index_list.length-1; i >= 0 ; i--) {
			this.text_list.remove(index_list[i]);
		}
		
		System.out.println(text_list);
		
		this.content_list = new JList(text_list.toArray());
		System.out.println(this.content_list.getModel().getSize());
		
		content_scroll.setViewportView(content_list);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == this.more_btn) {
			addTextListField();
		}else if(arg0.getSource() == this.less_btn) {
			removeTextListField();
		}
	}

}
