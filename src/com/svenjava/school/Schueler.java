package com.svenjava.school;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Schueler {
	
	private static int totalNumber = 0;
	private int id;
	private String vorname;
	private String nachname;
	private Klassenstufe stufe;
	private Wahl wahl;
	static Map<Schueler, Wahl> alleSchueler;
	
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
	public String getVorname() {
		return this.vorname;
	}
	public String getNachname() {
		return this.nachname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Schueler other = (Schueler) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public static Schueler getSchuelerByName(String vorname, String nachname) {
		Schueler s = null;
		for(Map.Entry<Schueler, Wahl> entry : alleSchueler.entrySet()) {
			if(entry.getKey().getVorname().equals(vorname)) {
				if(entry.getKey().getNachname().equals(nachname)) {
					return entry.getKey();
				}
			}
		}
		return s;
	}
	
	

}
