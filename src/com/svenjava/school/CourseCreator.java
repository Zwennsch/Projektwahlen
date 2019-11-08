package com.svenjava.school;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * @author Sven Schröder
 * This is the main class that calculates what student participates in what courses
 * The calculation is based on certain assumptions:
 * 	1. The maximum amount of students is to be respected for every course
 * 	2. Each 10th grader should get his primary choice.
 * 	3. If the primary choice isn't possible, then each 10th grader should get his secondary choice and if this isn't possible the third choice.
 * 	4. The other students should get there primary wishes, after the 10th graders.
 */
public class CourseCreator {
	static List<Schueler> tenThgraders;
	List<Map<Kurs,List<Schueler>>> finalCourses;
	List<Map<Kurs,List<Schueler>>> firstExampleCourses;
	Random random = new Random();
	
	public CourseCreator(List<Schueler> alle, List<Kurs>  courses) {
		tenThgraders = create10thGraders(alle);
	}
	
	private List<Schueler> create10thGraders(List<Schueler> alle) {
		Predicate<Schueler> byGrade = schueler -> schueler.getKlassenstufe() == Klassenstufe.ZEHN;
		return alle.stream().filter(byGrade).collect(Collectors.toList());
	}
	
	public static void calculateCourses() {
		distribute10th();
	}

	private static void distribute10th() {
		fillInStudentsDependingOnWish();
	}

	private static void fillInStudentsDependingOnWish() {
		
	}

}
