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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Course Creator Test")
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
		schuelerList = null;
		randomCourseListWithTenToTwentyStudentsAndNCourses = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test 
	@DisplayName("Test generate nth graders")
	void testCreateNthGrades () throws Exception{
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
		List<Kurs> firstWish = CourseCreator.fillInStudentsDependingOnWish(tenth, randomCourseListWithTenToTwentyStudentsAndNCourses);
		for(Kurs c : firstWish) {
			System.out.println(c + ": maximale Größe: "+ c.getMaxSize());
			for( Schueler s : c.getSchueler()) {
				System.out.println(s + "wählte erstwahl:" + s.getWahl().erstWahl);
			}
		}
	}
	
	@Test
	void testRefactorIfCourseIsfull() {
		
//		test for 10 times:
		List<Schueler> students = new ArrayList<>();
//		create 100 students where at least 30 of them have the same first wish;
		for (int i = 0; i < 1; i++) {
			students = StudentListCreator.getNStudentsWithNthGradeWithEqalFirstWish(30, Klassenstufe.ZEHN);
			students.addAll(StudentListCreator.getNStudentsWithGrade(70, Klassenstufe.ZEHN));
		}
		int counter = 0;
		for (Schueler s : students) {
			System.out.println(s.getWahl().erstWahl);
			if(s.getWahl().erstWahl.toString().equals("Kurs0")) {
				counter++;
			}
		}
		System.out.println(counter);
		
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
