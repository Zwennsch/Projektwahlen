package com.svenjava.school;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CourseCreatorTest {
	static List<Schueler> schuelerList;
	static List<Kurs> courseList;
	CourseCreator cc = new CourseCreator(schuelerList, courseList);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Random random = new Random();
		courseList = new ArrayList<>();
		fillCourseList(courseList, random);
		schuelerList = new ArrayList<>();
		fillSchuelerList(schuelerList, random, courseList);
	}

	private static void fillSchuelerList(List<Schueler> schuelerList, Random random, List<Kurs> courseList) {
		for (int i = 0; i < 300; i++) {
			Wahl w = getThreeRandomCourses(random, courseList);
			Klassenstufe stufe;
			if(i < 100){
				stufe = Klassenstufe.ACHT;
			}else if(i >= 100 && i < 200) {
				stufe = Klassenstufe.NEUN;
			}else {
				stufe = Klassenstufe.ZEHN;
			}
			Schueler s = new Schueler("vorname"+i, "nachname"+i, stufe, w);
			schuelerList.add(s);
		}
		
	}

	private static Wahl getThreeRandomCourses(Random random, List<Kurs> courseList) {
		List<Kurs> copy = new ArrayList<>(courseList);
		Kurs k1 = copy.get(random.nextInt(copy.size()));
		copy.remove(k1);
		Kurs k2 = copy.get(random.nextInt(copy.size()));
		copy.remove(k2);
		Kurs k3 = copy.get(random.nextInt(copy.size()));
		return new Wahl(k1, k2, k3);
	}

	private static void fillCourseList(List<Kurs> list, Random random) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 20; i++) {
			list.add(new Kurs("Kurs"+i, random.nextInt(10)+10));
		}
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
	void testGenerateNthGrades () throws Exception{
		List<Schueler> tenth = cc.createNthGraders(schuelerList, Klassenstufe.ZEHN);
		Random r = new Random();
		assertEquals(100, tenth.size(), "Should be 100");
		assertEquals(Klassenstufe.ZEHN, tenth.get(r.nextInt(tenth.size())).getKlassenstufe());
		assertEquals(Klassenstufe.ZEHN, tenth.get(r.nextInt(tenth.size())).getKlassenstufe());
		assertEquals(Klassenstufe.ZEHN, tenth.get(r.nextInt(tenth.size())).getKlassenstufe());
		assertEquals(Klassenstufe.ZEHN, tenth.get(r.nextInt(tenth.size())).getKlassenstufe());
		assertEquals(Klassenstufe.ZEHN, tenth.get(r.nextInt(tenth.size())).getKlassenstufe());
		assertEquals(Klassenstufe.ZEHN, tenth.get(r.nextInt(tenth.size())).getKlassenstufe());
	}
	


	@Test
	void testCourseCreator() {
		for(Schueler s : schuelerList) {
			System.out.println(s + " ;" +s.getKlassenstufe()+ " ;"+ s.getWahl());
		}
		System.out.println(schuelerList.get(20).getKlassenstufe());
	}

	@Test
	@Disabled
	void testCalculateCourses() {
		fail("Not yet implemented");
	}

}
