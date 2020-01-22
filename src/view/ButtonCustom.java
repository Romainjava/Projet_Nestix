package view;

import java.awt.Font;

import javax.swing.JButton;

public class ButtonCustom extends JButton{

	public ButtonCustom(String text) {
		super();
		this.setText(text);
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	}
	
	public ButtonCustom() {
		super();
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	}
	public ButtonCustom(String text,int fontSize) {
		super();
		this.setText(text);
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, fontSize));
	}
}
