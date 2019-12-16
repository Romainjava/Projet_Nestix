package view;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ComboListField extends JComboBox{
	
	public ComboListField(String[] data) {
		super(data);
		((JLabel)this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void setSelectedItem(String pName) {
		this.setSelectedIndex(0);
		
		for(int i = 0; i < this.getItemCount(); i++) {
			if(this.getItemAt(i).toString().equals(pName)) {
				this.setSelectedIndex(i);
			}
		}
	}
}
