package modele;


public interface I_requeteSQL {

	boolean creation();
	boolean modification();
	boolean lireUn(int id);
	boolean suppression(int id);
}
