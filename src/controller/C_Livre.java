package controller;

import javax.swing.JPanel;
import view.AsidePanel;

public class C_Livre {
	private JPanel livres_panel;

	public C_Livre(JPanel livres_panel) {
		this.livres_panel = livres_panel;
	}

	public void ajouteTab() {
		AsidePanel livres_aside_panel = new AsidePanel(this.livres_panel);
		livres_aside_panel.setEntetes(new String[] { "Titre", "ISBN", "Genre", "Etat", "Annee" });
		livres_aside_panel.setDonnees(new Object[][] { { "La boussole d'or", "toto en vacance", "valide", "2010" }, });
		livres_aside_panel.ajouterLigne(new Object[] { "toto", "tata et toto", null, null });
	}
}
