package view;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ComboListField extends JComboBox{
	
	public ComboListField(String[] data) {
		super(data);
		((JLabel)this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}
}
