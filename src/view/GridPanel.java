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
		
		this.setBackground(Color.MAGENTA);
		
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
	public void addModule(Module pModule, int x, int y) {
		//this.moduleList.add(pModule);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(pModule, gbc);	
	}
	public void addModule(Module pModule, int x, int y, int width, int height) {
		//this.moduleList.add(pModule);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(pModule, gbc);	
	}
	
}
