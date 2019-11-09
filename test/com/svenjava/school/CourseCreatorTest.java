package com.svenjava.school;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseCreatorTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Random random = new Random();
		List<Kurs> courseList = new ArrayList<>();
		fillCourseList(courseList, random);
		List<Schueler> schuelerList = new ArrayList<>();
		fillSchuelerList(schuelerList, random, courseList);
	}

	private static void fillSchuelerList(List<Schueler> schuelerList, Random random, List<Kurs> courseList) {
		// TODO Auto-generated method stub
		/*
		 * change, so that three different courses get picked,
		 * pick a random Klasse from enum for eaćh person
		 */
		for (int i = 0; i < 200; i++) {
			Kurs k1 = courseList.get(random.nextInt(courseList.size()));
			Kurs k2 = courseList.get(random.nextInt(courseList.size()));
			Kurs k3 = courseList.get(random.nextInt(courseList.size()));
//			schuelerList.add(new Schueler("s"+i, "S"+i, klasse)
		}
		
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
	void testCourseCreator() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateCourses() {
		fail("Not yet implemented");
	}

}
