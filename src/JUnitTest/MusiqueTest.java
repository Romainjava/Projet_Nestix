package JUnitTest;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import junit.framework.TestCase;
import modele.Genre;
import modele.I_recherche;
import modele.Musique;

class MusiqueTest extends TestCase {
	Musique musique;
	@BeforeEach
	protected void setUp() throws Exception {
		musique = new Musique();
		musique.lireUn(76);
	}	

	@Test
	void test() {
		assertEquals(true, musique.lireUn(75));
	}
	@Test
	void testLireTout() {
		ArrayList<I_recherche> musiques1= musique.lectureTout(10);
		assertEquals(true, musiques1.size()==10);
		assertTrue(musiques1 instanceof ArrayList<?>);

	}
	@Test
	void testSetGenre() {
		Genre genre = new Genre();
		genre.setId(5);
		genre.setNom("rock");
		assertEquals(5, genre.getId());
		assertEquals("rock", genre.getNom());
		ArrayList<Genre> genres= new ArrayList<>();
		genres.add(genre);
		musique.setGenres(genres);
		assertTrue(musique.getGenres() instanceof ArrayList<?>);
	}
	
	@Test
	void testToRowData() {
		assertTrue(musique.toRowData() instanceof String[]);
	}
	
	@Test
	void testDateCrea() {
		assertNull(musique.getDate_crea_media());
		musique.setDate_crea_media("2019-01-01");
		assertNotNull(musique.getDate_crea_media());
	}
	
	
	@Test
	void testDureeMusique() {
		musique.setDuree_musique(100);
		assertEquals(100, musique.getDuree_musique());
		// l'id 120 est une musique a durée null, mais les int en java sont des 0.
		musique.lireUn(120);
		assertEquals(0,musique.getDuree_musique());
	}
}
