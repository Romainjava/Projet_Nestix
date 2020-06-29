package JUnitTest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import modele.I_recherche;
import modele.Image;
import modele.Media;
import requete.M_artiste;
import modele.Artiste;

public class ArtisteTest {
	Artiste artiste;
	Artiste artiste_faux;
	Media media;

	@Before
	public void setUp() throws Exception {
		try {
			artiste = new Artiste();
			artiste_faux = new Artiste();
			artiste_faux.lireUn(9999999);
			artiste.lireUn(57);
		} catch (Exception e) {
			System.out.println("Voici l'erreur attrapé " + e.getMessage());
		}
	}

	@Test
	public void testLireArtiste() {
		assertEquals(true, M_artiste.lireUn(artiste));
		assertEquals(false, M_artiste.lireUn(artiste_faux));
	}
	

	@Test
	public void testLireTout() {
		ArrayList<I_recherche> artistes = artiste.lectureTout(10);
		assertEquals(true, artistes.size() == 10);
		assertTrue(artistes instanceof ArrayList<?>);
	}

	@Test
	public void testToRowData() {
		assertTrue(artiste.toRowData() instanceof Object[]);
	}

	@Test
	public void testToHeaderData() {
		assertTrue(artiste.toHeaderData() instanceof String[]);
	}

	@Test
	public void testVerifDateForm() {
		artiste.setDob_artiste("11/07/1989");
		assertTrue(artiste.verifDateForm());

		artiste.setDob_artiste("1119,11,25");
		assertFalse(artiste.verifDateForm());

		artiste.setDob_artiste("1119-11-25");
		assertFalse(artiste.verifDateForm());

		artiste.setDob_artiste("1119-11dd-25");
		assertFalse(artiste.verifDateForm());

		artiste.setDob_artiste("1989/07/11");
		assertFalse(artiste.verifDateForm());
	}

	@Test
	/**
	 * Test Création d'un nom et vérification de son type
	 */
	public void testCreatePrenomArtiste() {
		assertNull(artiste_faux.getPrenom_artiste());
		assertNotNull(artiste.getPrenom_artiste());
		artiste.setPrenom_artiste("John");
		assertEquals(artiste.getPrenom_artiste(), "John");
		assertTrue(artiste.getPrenom_artiste() instanceof String);
	}

	@Test
	/**
	 * Vérifie la fonction retourne l'id par rapport à un string
	 */
	public void testGetIdEtat() {
		artiste.setEtat("valide");
		assertEquals(1, artiste.getId_etat());

		artiste.setEtat("attente");
		assertEquals(2, artiste.getId_etat());

		artiste.setEtat("bloquer");
		assertEquals(3, artiste.getId_etat());

		artiste.setEtat("suggere");
		assertEquals(4, artiste.getId_etat());
	}


}
