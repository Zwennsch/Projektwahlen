package com.svenjava.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
 * @author Sven Schroeder
 * {@summary Calculates the composition of the courses depending on the election by students }
 */
public class CourseCreator {
	
	static List<Kurs> finalCoursestoFill;
	static List<Schueler> tenThGraders;
	static List<Schueler> eigthAndNinthThGraders;
	static List<Schueler> wishNotFullfilled;
	Random random = new Random();
	
	static {
		wishNotFullfilled = new ArrayList<>();
	}
	
	public CourseCreator(List<Schueler> alleSchueler, List<Kurs>  courses) {
		tenThGraders = createNthGraders(alleSchueler, Klassenstufe.ZEHN);
		eigthAndNinthThGraders = createNthGraders(alleSchueler, Klassenstufe.ACHT);
		eigthAndNinthThGraders.addAll(createNthGraders(alleSchueler, Klassenstufe.NEUN));
		finalCoursestoFill = courses;
	}
	
	public static void calculateCourses() {
//		first: distruíbute the 10th graders
		distributeNthGraders(tenThGraders, finalCoursestoFill);
		distributeNthGraders(eigthAndNinthThGraders, finalCoursestoFill);
	}
	
	public static List<Schueler> createNthGraders(List<Schueler> alle, Klassenstufe stufe) {
		Predicate<Schueler> byGrade = schueler -> schueler.getKlassenstufe() == stufe;
		return alle.stream().filter(byGrade).collect(Collectors.toList());
	}
	

	private static void distributeNthGraders(List<Schueler> schuelerList, List<Kurs> coursesToBeFilled) {
		fillInStudentsDependingOnWish(schuelerList, coursesToBeFilled);
		refactorIfCourseFull(coursesToBeFilled);
	}
	
	
	/**checks if any of the courses maxSize is being exceeded. If so students get randomly picked from the list and put into the pool
	 * of students List<Schueler> wishNotFullfilled. 
	 * 
	 * @param justWishes the List of courses filled up with students depending on their wishes
	 */
	static void refactorIfCourseFull(List<Kurs> justWishes) {
		for(Kurs c : justWishes) {
			if(c.getMaxSize() < c.getActualSize()) {
				int toMany = c.getActualSize() - c.getMaxSize();
				for(int i = 0; i < toMany ; i++) {
					Collections.shuffle(c.getSchueler());
					Schueler s = c.getSchueler().get(0);
					c.removeSchueler(s);
					wishNotFullfilled.add(s);
				}
			}
//			distribute students depending on their second and third wish if possible
//		}if(!wishNotFullfilled.isEmpty()) {
//			int wish = 2;
//			do {
//				fillCoursesWithSecondOrThirdWish(justWishes, wish);
//				wish++;
//			}while(wishNotFullfilled.isEmpty() || wish > 3);
//			wish++;
		}
	}

	static void fillCoursesWithSecondOrThirdWish(List<Kurs> justWishes, int wishNumber) {
		for(Schueler s : wishNotFullfilled) {
			Kurs second = s.getWahl().zweitWahl;
			for(Kurs c: justWishes) {
				if(c.equals(second)) {
					if(!c.isFull()) {
						c.addSchuelerToKurs(s);
						wishNotFullfilled.remove(s);
					}
				}
			}
		}
	}

	static void fillInStudentsDependingOnWish(List<Schueler> schuelerList, List<Kurs> coursToBeFilledOnWish) {
		Set<Schueler> studentsSet = new HashSet<>(schuelerList);
//		Collections.shuffle(studentsSet);
		for(int i = 0; i < schuelerList.size(); i++)
		for(Schueler s : schuelerList) {
			Kurs course = s.getWahl().erstWahl;
			Iterator<Kurs> iterator = coursToBeFilledOnWish.iterator();
			while(iterator.hasNext()) {
				Kurs it = iterator.next();
				if(it.equals(course)) {
					it.addSchuelerToKurs(s);
					break;
				}
			}
		}
	}
	
	static void distributeRandomlyIfCourseFull(List<Kurs> exampleCourses) {
		
	}

}
