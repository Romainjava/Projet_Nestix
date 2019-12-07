package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import modele.*;

public class MainPanel extends JPanel{
	
	private ArrayList<Module> moduleList = new ArrayList<>();

	public MainPanel(JPanel fPanel) {
		super();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		//Add this to Father Panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		fPanel.add(this, gbc);

		//Layout
		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0, 1.0, 1.0};
	    gbl.rowWeights = new double[] {1.0,
	    								1.0};
	    this.setLayout(gbl);

	    //Element
//		this.addModule(new LinkModule("Personne"), 0, 0, 1, 1);
//		this.addModule(new Module(), 0, 1, 1, 1);
//		
//		this.addModule(new ImageModule(), 2, 0, 1, 1);

	}
	
	public void addModule(Module pModule, int x, int y) {
		this.moduleList.add(pModule);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(pModule, gbc);	
	}
	public void addModule(Module pModule, int x, int y, int width, int height) {
		this.moduleList.add(pModule);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(pModule, gbc);	
	}
	
}
