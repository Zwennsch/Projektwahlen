package com.svenjava.school;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("Course Creator Test")
class CourseCreatorTest {
	static List<Schueler> schuelerList;
	static List<Kurs> twentyCourses;
	static Random random;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		twentyCourses = RandomCourseCreator.getNCoursesWithRandomMaxSizes(20);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		schuelerList = null;
		twentyCourses = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		CourseCreator.wishNotFullfilled.clear();
		
	}
	
	@Test 
	@DisplayName("Test generate nth graders")
	void testCreateNthGrades () throws Exception{
		schuelerList = StudentListCreator.getNStudentsWithEqualNthGradersWithWahl(300, twentyCourses);
		List<Schueler> tenth = CourseCreator.createNthGraders(schuelerList, Klassenstufe.ZEHN);
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
//		create 300 random students and 20 random courses
		schuelerList = StudentListCreator.getNStudentsWithEqualNthGradersWithWahl(300, twentyCourses);
//		create a list of 10thgraders
		List<Schueler> tenth = CourseCreator.createNthGraders(schuelerList, Klassenstufe.ZEHN);
//		make sure, that every tenth grader gets its first choice:
		CourseCreator.fillInStudentsDependingOnWish(tenth, twentyCourses);
		for(Kurs c : twentyCourses) {
			System.out.println(c + ": maximale Größe: "+ c.getMaxSize());
			for( Schueler s : c.getSchueler()) {
				System.out.println(s + " wählte erstwahl:" + s.getWahl().erstWahl);
				assertEquals(c, s.getWahl().erstWahl);
			}
		}
	}
	
	@RepeatedTest(5)
	void testRefactorIfCourseIsfull() {
		List<Schueler> students = new ArrayList<>();
//		create 100 students where at least 30 of them have the same first wish, wich is Kurs1;
		students = StudentListCreator.getNStudentsWithNthGradeWithEqalFirstWish(30, Klassenstufe.ZEHN, twentyCourses);
		students.addAll(StudentListCreator.getNStudentsWithGradeWithWahl(70, Klassenstufe.ZEHN, twentyCourses));
//		add all the students to their courses
		CourseCreator.fillInStudentsDependingOnWish(students, twentyCourses);
//		make sure that Kurs0 has more students than its capacity
		assertTrue(twentyCourses.get(0).getActualSize() > twentyCourses.get(0).getMaxSize(), "should be more students in the course");
//		assert that the wishNotFullfilled List is still empty;
		assertTrue(CourseCreator.wishNotFullfilled.isEmpty());
//		Put the list through the refactor and make sure that the Kurs0 isn't exceeded any more
		CourseCreator.refactorIfCourseFull(twentyCourses);
		assertTrue(twentyCourses.get(0).getActualSize() == twentyCourses.get(0).getMaxSize());
//		check, that the wishNotFUllfilled isn't empty any more;
		assertTrue(CourseCreator.wishNotFullfilled.size()>0);
		System.out.println(CourseCreator.wishNotFullfilled.size());
		System.out.println(twentyCourses.get(0).getMaxSize());
//		make sure that the size of wishNotFullfilled is 30 - maxSize of course0
		assertTrue(CourseCreator.wishNotFullfilled.size() >= 30 - twentyCourses.get(0).getMaxSize());
		System.out.println();
		for(Schueler s :CourseCreator.wishNotFullfilled) {
			System.out.println(s.getWahl());
		}
		
		System.out.println(Kurs.getTotalNumberOfCourses());
	}
	@Test
	void testFillCoursesWithSecondOrThirdWishFromWishNotFullFilledList() {
//		Create 300 students with random courses, 100 for every grade;
		schuelerList = StudentListCreator.getNStudentsWithEqualNthGradersWithWahl(300, twentyCourses);
		System.out.println(schuelerList.size());
		System.out.println(twentyCourses.size());
//		fillCourseList with students depending on first wish
		CourseCreator.fillInStudentsDependingOnWish(schuelerList, twentyCourses);
		random = new Random();
	
//		create  for those getting in WishNotFullfilled
		CourseCreator.wishNotFullfilled = schuelerList.stream()
				.filter(s -> s.getId()%(schuelerList.size()/30) == 0)
				.collect(Collectors.toList());
		
		schuelerList = schuelerList.stream()
				.filter(s -> s.getId()%(schuelerList.size()/30) != 0)
				.collect(Collectors.toList());
			
//		Iterator<Schueler> it = schuelerList.iterator();
//		for(int i = 0; i< numberOfWishNotFullfilled ; i++) {
//			int r = random.nextInt(schuelerList.size());
//			CourseCreator.wishNotFullfilled.add(schuelerList.get(r));
//			schuelerList.remove(r);
//		}
//		assert that wishNotFullfilled has n students
//		assertTrue(CourseCreator.wishNotFullfilled.size() == numberOfWishNotFullfilled);
		assertTrue(CourseCreator.wishNotFullfilled.size() + schuelerList.size() == 300);
//		let the method do its job:
		CourseCreator.fillCoursesWithSecondOrThirdWish(twentyCourses, 2);
//		assert that every student from  wishNotFullfilled is put into second wish course
		for(Schueler s : CourseCreator.wishNotFullfilled) {
			Kurs k = s.getWahl().zweitWahl;
			assertTrue(k.isPartOfCourse(s));
		}
		
	}
	@Test
	void shouldFillCoursesWithStudents() {
		int numberStudents = 300;
		List<Schueler> studs = StudentListCreator.getNStudentsWithEqualNthGradersWithWahl(numberStudents, twentyCourses);
		new CourseCreator(studs, twentyCourses);
		//make sure courses are empty;
		for(Kurs c : CourseCreator.finalCoursestoFill) {
			assertTrue(c.getActualSize()== 0);
		}
		//make sure wishNotFullfilled is empty
		assertTrue(CourseCreator.wishNotFullfilled.size()== 0);
//		make sure, all students are distributed
//		CourseCreator.fillInStudentsDependingOnWish(CourseCreator.allStudents, CourseCreator.finalCoursestoFill);
		CourseCreator.calculateCourses();
		int counter = 0;
		for(Kurs c : CourseCreator.finalCoursestoFill) {
			counter += c.getActualSize();
		}
		counter += CourseCreator.wishNotFullfilled.size();
		assertEquals(numberStudents, counter);
		
		
		try {
			DataHandler.saveCourses(CourseCreator.finalCoursestoFill);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DataHandler.saveFinalStudentList(CourseCreator.allStudents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	

	@Test
	@Disabled
	void testCalculateCourses() {
		fail("Not yet implemented");
	}

}
