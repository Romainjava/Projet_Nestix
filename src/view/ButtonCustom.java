package view;

import java.awt.Font;

import javax.swing.JButton;

public class ButtonCustom extends JButton{
	/**
	 * 
	 * @param text
	 */
	public ButtonCustom(String text) {
		super();
		this.setText(text);
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	}
	/**
	 * 
	 */
	public ButtonCustom() {
		super();
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	}
	/**
	 * 
	 * @param text
	 * @param fontSize
	 */
	public ButtonCustom(String text,int fontSize) {
		super();
		this.setText(text);
		setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, fontSize));
	}
}
