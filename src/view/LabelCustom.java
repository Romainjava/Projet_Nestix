package view;

import java.awt.Font;

import javax.swing.JLabel;

public class LabelCustom extends JLabel {

	public LabelCustom() {
		super();
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	}
	
	public LabelCustom(int sizeFont) {
		super();
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, sizeFont));
	}
	
	public LabelCustom(String text,int sizeFont) {
		super();
		this.setText(text);
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, sizeFont));
	}
	
	public LabelCustom(String text) {
		super();
		this.setText(text);
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	}
}
