package view;

public enum E_TypesMedia {
	LIVRE("Livre"), FILM("Film"), MUSIQUE("Musique");

	public final String label;

	private E_TypesMedia(String label) {
		this.label = label;
	}
}
