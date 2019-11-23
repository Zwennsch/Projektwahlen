package com.svenjava.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentListCreator {
	
	static Random random = new Random();
	
	private static List<Schueler> getNStudentsWithGradeWithWahl(int studentsPerGrade, Klassenstufe klassenstufe) {
		List<Kurs> courses = RandomCourseCreator.getNCoursesWithRandomMaxSizes(20);
		return getNStudentsWithGradeWithWahl(studentsPerGrade, klassenstufe, courses);
	}
	
	static List<Schueler> getNStudentsWithGradeWithWahl(int number, Klassenstufe grade, List<Kurs> nCourses){
		List<Schueler> students = new ArrayList<>();
		for (int i = 0; i < number ; i++) {
			students.add(new Schueler("vorname"+Schueler.totalNumber, "nachname"+Schueler.totalNumber, grade, RandomCourseCreator.getThreeRandomCoursesFromCourseListWithNCourses(nCourses)));
		}
		return students;
	}
	
	static List<Schueler> getNStudentsWithEqualNthGraders(int totalNumberOfStudents){
		List<Schueler> students = new ArrayList<>(totalNumberOfStudents);
		int studentsPerGrade = totalNumberOfStudents/Klassenstufe.values().length;
		for(int i = 0; i < Klassenstufe.values().length; i++) {
			List<Schueler> randomNthGraders = getNStudentsWithGrade(studentsPerGrade, Klassenstufe.values()[i]);
			students.addAll(randomNthGraders);
		}
		return students;
		
	}
	

	static List<Schueler> getNStudentsWithNthGradeWithEqalFirstWish(int totalNumberOfStudents, Klassenstufe grade, List<Kurs> nCourses){
		List<Schueler> nthSameFirstWish = getNStudentsWithGrade(totalNumberOfStudents, grade);
		for(Schueler s : nthSameFirstWish) {
			Wahl w = RandomCourseCreator.getThreeKursesWithSameFirstWishFromListOfCourses(nCourses);
			s.makeWahl(w);
		}
		return nthSameFirstWish;
		
	}

	private static List<Schueler> getNStudentsWithGrade(int totalNumberOfStudents, Klassenstufe grade){
		List<Schueler> students = new ArrayList<>(totalNumberOfStudents);
		for (int i = 0; i < totalNumberOfStudents ; i++) {
			students.add(new Schueler("vorname"+Schueler.totalNumber, "nachname"+Schueler.totalNumber, grade));
		}
		return students;
	}

	public static List<Schueler> getNStudentsWithEqualNthGradersWithWahl(int numberOfStudents, List<Kurs> courses) {
		List<Schueler> schueler = getNStudentsWithEqualNthGraders(numberOfStudents);
		for(Schueler s: schueler) {
			Wahl w = RandomCourseCreator.getThreeRandomCoursesFromCourseListWithNCourses(courses);
			s.makeWahl(w);
		}
		
		return schueler;
	}

}
