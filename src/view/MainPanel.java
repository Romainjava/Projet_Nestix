package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

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
