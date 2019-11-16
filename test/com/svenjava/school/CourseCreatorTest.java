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
	static List<Kurs> randomCourseListWithTenToTwentyStudentsAndNCourses;
	CourseCreator cc = new CourseCreator(schuelerList, randomCourseListWithTenToTwentyStudentsAndNCourses);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		randomCourseListWithTenToTwentyStudentsAndNCourses = RandomCourseCreator.getNCoursesWithRandomMaxSizes(20);
		System.out.println(randomCourseListWithTenToTwentyStudentsAndNCourses.size());
		schuelerList = StudentListCreator.getNRandomStudentsWithEqualNthGraders(300);
		System.out.println(schuelerList.size());
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
	void testFillInStudentsDependingOnWish() {
		List<Schueler> tenth = cc.createNthGraders(schuelerList, Klassenstufe.ZEHN);
//		make sure, that every tenth grader gets its first choice:
		List<Kurs> firstWish = cc.fillInStudentsDependingOnWish(tenth);
		for(Kurs c : firstWish) {
			System.out.println(c + ": maximale Größe: "+ c.getMaxSize());
			for( Schueler s : c.getSchueler()) {
				System.out.println(s + "wählte erstwahl:" + s.getWahl().erstWahl);
			}
		}
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
