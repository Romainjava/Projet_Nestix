package view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextAreaScrollField extends JScrollPane{
	
	public TextAreaScrollField(int prow, int pcolumn){
		
		JTextArea text_area = new JTextArea(prow, pcolumn);
		
		this.setViewportView(text_area);
	}

}
