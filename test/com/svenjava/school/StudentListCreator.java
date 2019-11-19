package com.svenjava.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentListCreator {
	
	static Random random = new Random();
	
	static List<Schueler> getNStudentsWithGrade(int number, Klassenstufe grade){
		List<Schueler> students = new ArrayList<>();
		for (int i = 0; i < number ; i++) {
			students.add(new Schueler("vorname"+i, "nachname"+i, grade, RandomCourseCreator.getThreeRandomCoursesFromCourseListWithNCourses()));
		}
		return students;
	}
	
	static List<Schueler> getNRandomStudentsWithEqualNthGraders(int totalNumberOfStudents){
		List<Schueler> students = new ArrayList<>(totalNumberOfStudents);
		int studentsPerGrade = totalNumberOfStudents/Klassenstufe.values().length;
		for(int i = 0; i < Klassenstufe.values().length; i++) {
			List<Schueler> randomNthGraders = getNStudentsWithGrade(studentsPerGrade, Klassenstufe.values()[i]);
			students.addAll(randomNthGraders);
		}
		return students;
		
	}
	
	static List<Schueler> getNStudentsWithNthGradeWithEqalFirstWish(int totalNumberOfStudents, Klassenstufe grade){
		List<Schueler> nthSameFirstWish = getNStudentsWithGrade(totalNumberOfStudents, grade);
		for(Schueler s : nthSameFirstWish) {
			Wahl w = RandomCourseCreator.getThreeKursesWithSameFirstWish();
			s.makeWahl(w);
		}
		return nthSameFirstWish;
		
	}

}
