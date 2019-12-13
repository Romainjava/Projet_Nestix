package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JList;

public class DualLinkModule extends LinkModule{
	
	protected ArrayList<String> combo_list;
	protected JComboBox combo_list_field;
	protected String[] dataCombo;

	
	public DualLinkModule(String ptitre, String[] pDataCombo) {
		super();
		
		this.dataCombo = pDataCombo;
		
		//Element
		createElement(ptitre);
		
		//Event
		createEvent();
				
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
	    
	    addElementLayout(gbc);
	}
	
	public ArrayList<String> getCombo_list() {
		return combo_list;
	}
	public void setCombo_list(ArrayList<String> combo_list) {
		this.combo_list = combo_list;
	}
	
	@Override
	public void createElement(String ptitre) {
		super.createElement(ptitre);
		
		this.combo_list = new ArrayList<String>();
		this.combo_list_field = new JComboBox(this.dataCombo);
	}
//	@Override
//	public void createEvent() {
//		super.createEvent();
//		
//		
//	}
	@Override
	public void addElementLayout(GridBagConstraints gbc) {
		GridBagLayout gbl = new GridBagLayout();
		
		gbl.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 5.0};
	    gbl.rowWeights = new double[] {1.0,
										0.5,
										1.0,
										3.5};
	    this.setLayout(gbl);
	    
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		this.add(this.title_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(this.more_btn, gbc);
		gbc.gridx = 3;
		this.add(this.less_btn, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		this.add(this.text_list_field, gbc);
		gbc.gridx = 5;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(this.combo_list_field, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(content_scroll, gbc);
		content_scroll.setViewportView(content_list);
	}
	
	public void setData(String[] data, String[] dataCombo) {
		this.text_list = new ArrayList<String>(Arrays.asList(data));
		this.combo_list = new ArrayList<String>(Arrays.asList(dataCombo));
		System.out.println(text_list.toString());
		System.out.println(combo_list.toString());
		String[] tData = new String[text_list.size()];
		for(int i = 0; i < tData.length; i++){
			tData[i] = this.text_list.get(i) + " | " + this.combo_list.get(i);
		}
		
		this.content_list = new JList(tData);
		this.content_scroll.setViewportView(content_list);
	}
	
	@Override
	public void addTextListField() {

		if(!text_list_field.getText().equals("")) {
			this.text_list.add(text_list_field.getText());
			text_list_field.setText("");
			
			this.combo_list.add(combo_list_field.getSelectedItem().toString());
			combo_list_field.setSelectedIndex(0);
			
//			System.out.println(text_list);
//			System.out.println(combo_list);
			
			String[] text_list_array = new String[text_list.size()];
			for(int i = 0; i < text_list_array.length; i++){
				text_list_array[i] = text_list.get(i) + " | " + combo_list.get(i);
			}
			
			this.content_list = new JList(text_list_array);
			
			content_scroll.setViewportView(content_list);
		}

	}
	@Override
	public void removeTextListField() {
		
		int[] index_list = content_list.getSelectedIndices();

		for(int i = index_list.length-1; i >= 0 ; i--) {
			this.text_list.remove(index_list[i]);
			this.combo_list.remove(index_list[i]);
		}
		
		System.out.println(text_list);
		System.out.println(combo_list);
		
		String[] text_list_array = new String[text_list.size()];
		for(int i = 0; i < text_list_array.length; i++){
			text_list_array[i] = text_list.get(i) + " | " + combo_list.get(i);
		}
		this.content_list = new JList(text_list_array);
		
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
