package requete;

import javax.swing.JOptionPane;

public class MessageSqlState {

	public static void message(int sqlState) {
		switch (sqlState) {
		//1062 erreur de duplicatat
		case 1062:
			JOptionPane.showMessageDialog(null, "Erreur duplicat d'entrée","Duplicatat",JOptionPane.ERROR_MESSAGE);
			break;

		default:
			break;
		}
	}
	
}
