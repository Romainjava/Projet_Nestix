package modele;

import java.util.ArrayList;

public interface I_recherche {

	ArrayList<I_recherche> lectureTout(int limit);
	boolean rechercheParSurnom(int limit);
//	dans le cas de artiste c'est un recherche par metier
	boolean rechercheParTitre(int limit);
	boolean rechercheParAnnee(int limit);
	Object[] toRowData();
	String[] toHeaderData();
}
