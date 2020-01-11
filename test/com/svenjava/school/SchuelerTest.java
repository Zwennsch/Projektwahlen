package com.svenjava.school;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SchuelerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testShouldSortCorrectly() {
		Schueler s = new Schueler("Hans", "Müller", Klassenstufe.ACHT);
		Schueler s2 = new Schueler("Adam", "Tag", Klassenstufe.ACHT);
		Schueler s3 = new Schueler("Tom", "Abbe", Klassenstufe.ACHT);
		Schueler s4 = new Schueler("Lutz", "Meier", Klassenstufe.ACHT);
		
		List<Schueler> liste = new ArrayList<>();
		liste.add(s);
		liste.add(s2);
		liste.add(s3);
		liste.add(s4);
		
		Collections.sort(liste);
		
		assertEquals(liste.get(0).getVorname(), "Tom");
		System.out.println(liste);
	}

}
