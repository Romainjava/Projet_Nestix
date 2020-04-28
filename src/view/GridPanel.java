package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GridPanel extends JPanel {
	
	//private ArrayList<Module> moduleList = new ArrayList<>();

	public GridPanel(double[] pwidth, double[] pheight) {
		super();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		//Layout
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = pwidth;
	    gbl.rowWeights = pheight;
	    this.setLayout(gbl);
	}
	
	public GridBagConstraints addElement(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		return gbc;
	}
	public GridBagConstraints addElement(int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
		return gbc;
	}
	
	/**
	 * add a Module with predefine width and height
	 * @param pModule
	 * @param x
	 * @param y
	 */
	public void addModule(Module pModule, int x, int y) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(pModule, gbc);	
	}
	
	/**
	 * add a Module 
	 * @param pModule
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void addModule(Module pModule, int x, int y, int width, int height) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(pModule, gbc);	
	}
	
}
