package com.svenjava.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
 * 	4. The other students should get their primary wishes, after the 10th graders.
 * 
 * @author Sven Schroeder
 * {@summary Calculates the composition of the courses depending on the election by students }
 */
public class CourseCreator {
	
	static List<Kurs> finalCoursestoFill;
	static List<Schueler> allStudents;
	static List<Schueler> tenThGraders;
	static List<Schueler> eigthAndNinthThGraders;
	static List<Schueler> wishNotFullfilled;
	static Random random = new Random();
	
	static {
		wishNotFullfilled = new ArrayList<>();
	}
	
	public CourseCreator(List<Schueler> alleSchueler, List<Kurs>  courses) {
		allStudents = alleSchueler;
		tenThGraders = createNthGraders(alleSchueler, Klassenstufe.ZEHN);
		eigthAndNinthThGraders = createNthGraders(alleSchueler, Klassenstufe.ACHT);
		eigthAndNinthThGraders.addAll(createNthGraders(alleSchueler, Klassenstufe.NEUN));
		finalCoursestoFill = courses;
	}
	
	public static void calculateCourses() {
//		first: distribute the 10th graders
		distributeNthGraders(tenThGraders, finalCoursestoFill);
//		second: distribute the 8th and 9th graders equally
		distributeNthGraders(eigthAndNinthThGraders, finalCoursestoFill);
	}
	/**
	 * 
	 * @param alle list with all students
	 * @param stufe the grade of the students you wish to return
	 * @return the list of students that consists of students only with nth grade
	 */
	public static List<Schueler> createNthGraders(List<Schueler> alle, Klassenstufe stufe) {
		Predicate<Schueler> byGrade = schueler -> schueler.getKlassenstufe() == stufe;
		return alle.stream().filter(byGrade).collect(Collectors.toList());
	}
	

	private static void distributeNthGraders(List<Schueler> schuelerList, List<Kurs> coursesToBeFilled) {
		fillInStudentsDependingOnWish(schuelerList, coursesToBeFilled);
		refactorIfCourseFull(coursesToBeFilled);
	}
	/**
	 * should fill the courses depending on the students wishes. It picks a random student from the list, and checks
	 * its first wish. If the course still has at least one space left, the students gets added. If not the students 
	 * second wish gets checked. If even the course regarding the students third wish should be out of space
	 * the student gets added to the whisNotfullFilled list of student
	 * 
	 * @param schuelerList the list of students to be filled into courses
	 * @param coursToBeFilledOnWish the courses to be filled with students wishes
	 */
	static void fillInStudentsDependingOnWish(List<Schueler> schuelerList, List<Kurs> coursToBeFilledOnWish) {
		//create a HashSet with the students Id's 
		List<Integer> studentsIDSet = new ArrayList<>();
		for(int i = 0; i < schuelerList.size(); i++) {
			studentsIDSet.add(schuelerList.get(i).getId());
		}
		
		Collections.shuffle(studentsIDSet);
		while(!studentsIDSet.isEmpty()) {
			int id = studentsIDSet.get(0);
			Predicate<Schueler> byId = schueler -> schueler.getId() == id;
			Optional<Schueler> s = schuelerList.stream()
					.filter(byId).findFirst();
			Schueler stud = s.get();
			Kurs first = stud.getWahl().erstWahl;
			Kurs second = stud.getWahl().zweitWahl;
			Kurs third = stud.getWahl().drittWahl;
			if(!first.isFull()) {
				stud.setFinalCourse(first);
				first.addSchuelerToKurs(stud);
				studentsIDSet.remove(0);
//				continue;
			}else{
				if(!second.isFull()) {
					stud.setFinalCourse(second);
					second.addSchuelerToKurs(stud);
					studentsIDSet.remove(0);
					continue;
				}else if(!third.isFull()) {
					stud.setFinalCourse(third);
					third.addSchuelerToKurs(stud);
					studentsIDSet.remove(0);
				}else {
					wishNotFullfilled.add(stud);
					studentsIDSet.remove(0);
				}
			}
			
		}
//		old method, I might have to save for later...
		
//		for(int i = 0; i < schuelerList.size(); i++)
//		for(Schueler s : schuelerList) {
//			Kurs course = s.getWahl().erstWahl;
//			Iterator<Kurs> iterator = coursToBeFilledOnWish.iterator();
//			while(iterator.hasNext()) {
//				Kurs it = iterator.next();
//				if(it.equals(course)) {
//					it.addSchuelerToKurs(s);
//					break;
//				}
//			}
//		}
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

	
	static void distributeRandomlyIfCourseFull(List<Kurs> exampleCourses) {
		
	}

}
