package com.svenjava.school;

public class Schueler {
	
	private static int totalNumber = 0;
	private int id;
	private String name;
	private Klassenstufe stufe;
	private Wahl wahl;
	
	static { 
		totalNumber = 0;
	}
	public Schueler(String name, Klassenstufe klasse) {
		this.id = totalNumber++;
		this.name = name;
	}
	
	public void makeWahl(int wahl1, int wahl2, int wahl3) {
		
	}
	
	

}
