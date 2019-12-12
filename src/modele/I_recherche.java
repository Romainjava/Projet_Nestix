package modele;

import java.util.ArrayList;

public interface I_recherche {

	ArrayList<I_recherche> lectureTout(int limit);
	int getId();
	boolean recherchePar(int limit);
	Object[] toRowData();
	String[] toHeaderData();
}
