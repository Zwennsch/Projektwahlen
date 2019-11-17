package com.svenjava.school;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KursTest {
	
	static Kurs c;
	static Random r;
	static List<Schueler> studList;
	static final Klassenstufe[] VALUES = {Klassenstufe.ACHT, Klassenstufe.NEUN, Klassenstufe.ZEHN};
	static final int SIZE = VALUES.length;
//	static List<Kurs> 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		r = new Random();
		c = new Kurs("C1", r.nextInt(10)+10);
		studList = createHundredStuds();
	}

	private static List<Schueler> createHundredStuds() {
		List<Schueler> studs = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
//			studs.add(new Schueler("stud"+i, "nachN"+i, VALUES[r.nextInt(SIZE)], wahl))
		}
		return null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetTotalNumberOfCourses() {
//		check that course is empty at start
		assertEquals(0, c.getActualSize());
//		add ten new students to list and check size
		List<Schueler> studs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			
		}
	}

}
