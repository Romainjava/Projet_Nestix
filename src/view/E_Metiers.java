package view;

public enum E_Metiers {

	ECRIVAIN("Ecrivain"), ILLUSTRATEUR("Illustrateur"), AUTEUR("Auteur"), ACTEUR("Acteur");

	public final String label;

	private E_Metiers(String label) {
		this.label = label;
	}
}
