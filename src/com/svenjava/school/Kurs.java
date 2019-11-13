package com.svenjava.school;

import java.util.ArrayList;
import java.util.List;

public class Kurs {
	
	private static int totalNumberOfCourses;
	private String name;
	private List<Schueler> attendents;
	private int maxSize;
	
	
	
	public Kurs(String name, int maxSize) {
		this.name = name;
		totalNumberOfCourses++;
		this.attendents = new ArrayList<>();
		this.maxSize = maxSize;
	}
	
	public static int getTotalNumberOfCourses() {
		return totalNumberOfCourses;
	}
	public List<Schueler> getSchueler(){
		return attendents;
	}
	
	public void addSchuelerToKurs(Schueler schueler) {
		attendents.add(schueler);
		
	}
//	public Kurs getKursByName(String name) {
//		return.
//	}
	public boolean removeSchueler(Schueler s) {
		if (attendents.contains(s)) {
			attendents.remove(s);
			return true;
		}
		return false;
	}
	public boolean isFull() {
		return (this.getActualSize()>= this.maxSize);
	}
	
	public int getMaxSize() {
		return this.maxSize;
	}
	public int getActualSize() {
		return attendents.size();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kurs other = (Kurs) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
