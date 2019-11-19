package com.svenjava.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * test class that creates a choice (Wahl) of three different courses.urses.
 * You can either choose to get three courses from the courses loaded by the FxMain.class or let this class create a list of n 
 * different courses.
 * The size of maxStudents for each course is a number between 10 and 20.
 * @author Sven Schroeder
 *
 */
public class RandomCourseCreator {
	
	static Random random = new Random();
	int totalCoursesCount = Kurs.getTotalNumberOfCourses();
	static List<Kurs> originalFxMainCourseList = new ArrayList<>(FxMain.coursesList);
	static List<Kurs> randomCourseListWithNCourses;
	
	static Wahl getThreeRandomCoursesFromOriginalCourseList() {
		Collections.shuffle(originalFxMainCourseList);
		return new Wahl(originalFxMainCourseList.get(0), originalFxMainCourseList.get(1), originalFxMainCourseList.get(2));
	}
	
	static Wahl getThreeRandomCoursesFromCourseListWithNCourses() {
		Collections.shuffle(randomCourseListWithNCourses);
		return new Wahl(randomCourseListWithNCourses.get(0), randomCourseListWithNCourses.get(1), randomCourseListWithNCourses.get(2));
	}
	
	static protected List<Kurs> getNCoursesWithRandomMaxSizes(int numberOfCourses){
		randomCourseListWithNCourses = new ArrayList<>(numberOfCourses);
		for(int i = 0; i < numberOfCourses; i++) {
			randomCourseListWithNCourses.add(new Kurs("Kurs"+i, random.nextInt(10)+11));
		}
		return randomCourseListWithNCourses;
	}
	
	static Wahl getThreeKursesWithSameFirstWish()
	{
		List<Kurs> courses = getNCoursesWithRandomMaxSizes(20);
		Kurs firstChoice = courses.get(0);
		return new Wahl(firstChoice, courses.get(random.nextInt(courses.size())), courses.get(random.nextInt(courses.size())));
	}
}
