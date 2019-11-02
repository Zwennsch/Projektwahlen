package com.svenjava.school;

public class Schueler {
	
	private static int totalNumber = 0;
	private int id;
	private String vorname;
	private String nachname;
	private Klassenstufe stufe;
	private Wahl wahl;
	
	static { 
		totalNumber = 0;
	}
	public Schueler(String vorname,String nachname,  Klassenstufe klasse, Wahl wahl) {
		this.id = totalNumber++;
		this.vorname = vorname;
		this.nachname = nachname;
		this.wahl = wahl;
	}
	public Schueler(String vorname,String nachname,  Klassenstufe klasse) {
		this.id = totalNumber++;
		this.vorname = vorname;
		this.nachname = nachname;
		this.stufe = klasse;
	}
	
	public void makeWahl(Wahl w) {
		this.wahl = w;
	}
	
	
	public Wahl getWahl() {
		return this.wahl;
	}
	
	public String getName() {
		return this.vorname + " " + this.nachname;
	}
	public Klassenstufe getKlassenstufe() {
		return this.stufe;
	}
	
	

}
