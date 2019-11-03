package com.svenjava.school;

import com.sun.javafx.binding.StringFormatter;

public class Wahl {
	
	Kurs erstWahl;
	Kurs zweitWahl;
	Kurs drittWahl;
	Schueler schueler;

	public Wahl(Kurs k1, Kurs k2, Kurs k3, Schueler schueler) {
		this.erstWahl = k1;
		this.zweitWahl = k2;
		this.drittWahl = k3;
		this.schueler = schueler;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Schueler: "+ schueler.getName() + " Klasse: " + schueler.getKlassenstufe().toString() + " wählte: \n"+
			erstWahl +"\t" + zweitWahl +"\t"+ drittWahl ;
	}
}
