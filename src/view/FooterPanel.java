package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FooterPanel extends JPanel{
	
	private JButton bouton;
	private ArrayList<JButton> boutonTab;
	
	public ArrayList<JButton> getBoutonTab() {
		return boutonTab;
	}

	public void setBoutonTab(ArrayList<JButton> boutonTab) {
		this.boutonTab = boutonTab;
	}
	
	public FooterPanel(JPanel parent, String tab[], double elmsSize[]) {
		super();
		boutonTab = new ArrayList<JButton>();
		JPanel footer_panel = new JPanel();
		footer_panel.setBackground(Color.BLUE);
		GridBagConstraints gbc_footer_panel = new GridBagConstraints();
		gbc_footer_panel.fill = GridBagConstraints.BOTH;
		gbc_footer_panel.gridx = 0;
		gbc_footer_panel.gridy = 2;
		parent.add(footer_panel, gbc_footer_panel);
		GridBagLayout gbl_footer_panel = new GridBagLayout();
		gbl_footer_panel.columnWeights = elmsSize;
		gbl_footer_panel.rowWeights = new double[]{1.0};
		footer_panel.setLayout(gbl_footer_panel);
		
		int i = 0;
		for (String textBouton : tab) {
			bouton = new JButton(textBouton);
			GridBagConstraints gbc_footer_btn = new GridBagConstraints();
			gbc_footer_btn.gridx = i;
			gbc_footer_btn.gridy = 0;
			footer_panel.add(bouton, gbc_footer_btn);
			boutonTab.add(bouton);
			i++;
		}
	}
	
}
