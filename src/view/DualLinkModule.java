package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

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
		//GridBagConstraints gbc = new GridBagConstraints();
		//gbc.fill = GridBagConstraints.BOTH;
	    
	    //addElementLayout(gbc);
		this.addElementLayout();
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
	
	public void setData(String[] data, String[] dataCombo) {
		this.text_list = new ArrayList<String>(Arrays.asList(data));
		this.combo_list = new ArrayList<String>(Arrays.asList(dataCombo));
//		System.out.println(text_list.toString());
//		System.out.println(combo_list.toString());
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

	public void addElementLayout() {
		MigLayout gbl = new MigLayout("", "[grow][grow 50]", "[][][][grow]");
		
	    this.setLayout(gbl);
	    
		this.add(this.title_label, "cell 0 0 2 1,alignx center");
		
		JPanel panel_btn = new JPanel();
		this.add(panel_btn, "cell 0 1 2 1,grow");
		panel_btn.add(this.more_btn);
		panel_btn.add(this.less_btn);

		this.add(this.text_list_field, "cell 0 2,growx");
		this.text_list_field.setColumns(10);
		this.add(this.combo_list_field, "cell 1 2,growx");

		this.add(content_scroll, "cell 0 3 2 1,grow");
		content_scroll.setViewportView(content_list);
	}
	
}

