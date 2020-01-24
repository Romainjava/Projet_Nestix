package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class LinkModule extends Module implements ActionListener{
	
	protected JLabel title_label;
	protected JButton more_btn = new JButton("+");
	
	protected JButton less_btn = new JButton("-");
	protected TextListField text_list_field;

	protected JScrollPane content_scroll;
	protected ArrayList<String> text_list;
	protected JList content_list;
	
	public JList getContent_list() {
		return content_list;
	}
	public TextListField getText_list_field() {
		return text_list_field;
	}
	public boolean empty() {
		return text_list_field.getText().length()==0;
	}
	public JButton getMore_btn() {
		return more_btn;
	}

	public void setMore_btn(JButton more_btn) {
		this.more_btn = more_btn;
	}

	public JButton getLess_btn() {
		return less_btn;
	}

	public void setLess_btn(JButton less_btn) {
		this.less_btn = less_btn;
	}


	
	protected LinkModule() {
		super();
	}
	
	public LinkModule(String ptitre) {
		super();
		
		//Element
		createElement(ptitre);
		
		//Event
		createEvent();
				
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
	    
	    addElementLayout();
	}
	
	public ArrayList<String> getText_list() {
		return text_list;
	}

	public void setText_list(ArrayList<String> text_list) {
		this.text_list = text_list;
	}

	public void createElement(String ptitre) {
		title_label = new LabelCustom(ptitre);
		this.more_btn = new JButton("+");
		text_list_field = new TextListField();
		content_scroll = new JScrollPane();
		this.setData(new String[0]);
		//System.out.println(text_list);
	}
	public void createEvent() {
		this.more_btn.addActionListener(this);
		this.less_btn.addActionListener(this);
	}
	public void addElementLayout() {
		MigLayout gbl = new MigLayout("", "[grow]", "[][][][grow]");
		
	    this.setLayout(gbl);
	    
		this.add(this.title_label, "cell 0 0 2 1,alignx center");
		
		JPanel panel_btn = new JPanel();
		this.add(panel_btn, "cell 0 1 2 1,grow");
		panel_btn.add(this.more_btn);
		panel_btn.add(this.less_btn);

		this.add(this.text_list_field, "cell 0 2,growx");
		this.text_list_field.setColumns(10);
		//this.add(this.combo_list_field, "cell 1 2,growx");

		this.add(content_scroll, "cell 0 3 2 1,grow");
		content_scroll.setViewportView(content_list);
	}
	
	
	public void setData(String [] data) {
		this.text_list = new ArrayList<String>(Arrays.asList(data));
		this.content_list = new JList(data);
		this.content_scroll.setViewportView(content_list);
	}
	
	public void addTextListField() {

		if(!text_list_field.getText().equals("")) {
			this.text_list.add(text_list_field.getText());
			text_list_field.setText("");
//			System.out.println(text_list);
			
			this.content_list = new JList(text_list.toArray());
			
			content_scroll.setViewportView(content_list);
		}

	}
	
	public void removeTextListField() {
		
		int[] index_list = content_list.getSelectedIndices();

		for(int i = index_list.length-1; i >= 0 ; i--) {
			this.text_list.remove(index_list[i]);
		}
		
		//System.out.println(text_list);
		
		this.content_list = new JList(text_list.toArray());
		//System.out.println(this.content_list.getModel().getSize());
		
		content_scroll.setViewportView(content_list);

	}

	public void resetTextListField(){
		this.text_list.clear();
		this.content_list = new JList(text_list.toArray());
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
