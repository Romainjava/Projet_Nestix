package modele;


public interface I_requeteSQL {

	boolean creation();
	boolean modification(int id);
	boolean lireUn(int id);
	boolean suppression(int id);
}
