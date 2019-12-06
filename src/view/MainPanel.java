package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel{

	public MainPanel(JPanel fPanel) {
		super();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		//Add this to Father Panel
		gbc.gridx = 0;
		gbc.gridy = 2;
		fPanel.add(this, gbc);

		//Element
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.YELLOW);
		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.CYAN);

		//Layout
		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0, 1.0};
	    gbl.rowWeights = new double[] {1.0};
	    this.setLayout(gbl);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(panelLeft, gbc);
		gbc.gridx = 1;
		this.add(panelRight, gbc);

	}

}
