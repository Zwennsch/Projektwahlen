package com.svenjava.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
		randomCourseListWithNCourses = getNCoursesWithRandomMaxSizes(20);
		Collections.shuffle(randomCourseListWithNCourses);
		return new Wahl(randomCourseListWithNCourses.get(0), randomCourseListWithNCourses.get(1), randomCourseListWithNCourses.get(2));
	}
	
	public static Wahl getThreeRandomCoursesFromCourseListWithNCourses(
			List<Kurs> nCourses) {
		randomCourseListWithNCourses = nCourses;
		List<Kurs> courses = new ArrayList<>();
		while(courses.size()<3) {
			Kurs c = randomCourseListWithNCourses.get(random.nextInt(randomCourseListWithNCourses.size()));
			if(!courses.contains(c)) {
				courses.add(c);
			}
		}
		return new Wahl(courses.get(0), courses.get(1), courses.get(2));
		
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
		return getThreeKursesWithSameFirstWishFromListOfCourses(courses);
	}

	public static Wahl getThreeKursesWithSameFirstWishFromListOfCourses(
			List<Kurs> randomCourseListWithTenToTwentyStudentsAndNCourses) {
		
		List<Kurs> courses = randomCourseListWithTenToTwentyStudentsAndNCourses;
		Kurs firstChoice = courses.get(0);
		return new Wahl(firstChoice, courses.get(random.nextInt(courses.size()-1)+1), courses.get(random.nextInt(courses.size()-1)+1));
		
	}

}
