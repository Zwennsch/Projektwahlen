package com.svenjava.school;

import java.util.ArrayList;
import java.util.List;

public class Kurs {
	private static int totalNumberOfCourses;
	private String name;
	private List<Schueler> attendents;
	private int maxSize;
	private int size;
	
	public Kurs(String name) {
		this.name = name;
		totalNumberOfCourses++;
		this.attendents = new ArrayList<>();
	}
	
	public int getTotalNumberOfCourses() {
		return totalNumberOfCourses;
	}
	public List<Schueler> getSchueler(){
		return attendents;
	}
	
	public void addSchuelerToKurs(Schueler schueler) {
		attendents.add(schueler);
	}
	
	public int getMaxSize() {
		return this.maxSize;
	}
	public int getActualSize() {
		return attendents.size();
	}

}
