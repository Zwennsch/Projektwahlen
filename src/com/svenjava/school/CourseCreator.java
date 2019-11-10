package com.svenjava.school;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This is the main class that calculates what student participates in what courses
 * The calculation is based on certain assumptions:
 * 	1. The maximum amount of students is to be respected for every course
 * 	2. Each 10th grader should get his primary choice.
 * 	3. If the primary choice isn't possible, then each 10th grader should get his secondary choice and if this isn't possible the third choice.
 * 	4. The other students should get there primary wishes, after the 10th graders.
 * 
 * @author Sven Schröder
 * {@summary Calculates the composition of the courses depending on the election by students }
 */
public class CourseCreator {
	
	static List<Schueler> tenThgraders;
	List<Kurs> finalCourses;
	private static List<Kurs> firstExampleCourses;
	private static List<Schueler> wishNotFullfilled;
	Random random = new Random();
	
	public CourseCreator(List<Schueler> alleSchueler, List<Kurs>  courses) {
		tenThgraders = createNthGraders(alleSchueler, Klassenstufe.ZEHN);
		firstExampleCourses = List.copyOf(courses);
	}
	
	public List<Schueler> createNthGraders(List<Schueler> alle, Klassenstufe stufe) {
		Predicate<Schueler> byGrade = schueler -> schueler.getKlassenstufe() == stufe;
		return alle.stream().filter(byGrade).collect(Collectors.toList());
	}
	
	public static void calculateCourses() {
		List<Kurs> tenThDistibuted = distributeNthGraders(tenThgraders);
	}

	private static List<Kurs> distributeNthGraders(List<Schueler> schuelerList) {
		List<Kurs> justWishes = fillInStudentsDependingOnWish(schuelerList);
		return justWishes;
	}

	private static List<Kurs> fillInStudentsDependingOnWish(List<Schueler> schuelerList) {
		for(Schueler s : schuelerList) {
			Kurs course = s.getWahl().erstWahl;
			Iterator<Kurs> iterator = firstExampleCourses.iterator();
			while(iterator.hasNext()) {
				Kurs it = iterator.next();
				if(it.equals(course)) {
					it.addSchuelerToKurs(s);
					break;
				}
			}
		}
		return firstExampleCourses;
	}

}
