package view;

public enum E_Metiers {

	ACTEUR("Acteur", 1), REALISTATEUR("Realisateur", 2), INTERPRETE("Interprete", 3), ECRIVAIN("Ecrivain", 4),
	COMPOSITEUR("Compositeur", 5), SCENARISTE("Scenariste", 6);

	public final String label;
	public final int id;

	private E_Metiers(String label, int id) {
		this.label = label;
		this.id = id;
	}
}
