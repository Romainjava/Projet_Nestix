package view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextAreaScrollField extends JScrollPane{
	
	protected JTextArea text_area;

	public TextAreaScrollField(int pRow, int pColumn){
		
		text_area = new JTextArea(pRow, pColumn);
		
		this.setViewportView(text_area);
	}
	
	public JTextArea getText_area() {
		return text_area;
	}

	public void setText_area(JTextArea text_area) {
		this.text_area = text_area;
	}

}
