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
	Media media;

	@Before
	public void setUp() throws Exception {
		try {
			artiste = new Artiste();
			artiste.setId_artiste(57);
			artiste.lireUn(57);
		} catch (Exception e) {
			System.out.println("Voici l'erreur attrapé " + e.getMessage());
		}

	}

	@Test
	public void test() {
		artiste.setId_artiste(57);
		assertEquals(true, M_artiste.lireUn(artiste));
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
	public void testCreatePrenomArtiste() {
		assertNull(artiste.getPrenom_artiste());
		artiste.setPrenom_artiste("John");
		assertNotNull(artiste.getPrenom_artiste());
		assertTrue(artiste.getPrenom_artiste() instanceof String);
	}

	@Test
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
