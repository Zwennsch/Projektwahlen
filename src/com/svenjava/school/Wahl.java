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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drittWahl == null) ? 0 : drittWahl.hashCode());
		result = prime * result + ((erstWahl == null) ? 0 : erstWahl.hashCode());
		result = prime * result + ((zweitWahl == null) ? 0 : zweitWahl.hashCode());
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
		Wahl other = (Wahl) obj;
		if (drittWahl == null) {
			if (other.drittWahl != null)
				return false;
		} else if (!drittWahl.equals(other.drittWahl))
			return false;
		if (erstWahl == null) {
			if (other.erstWahl != null)
				return false;
		} else if (!erstWahl.equals(other.erstWahl))
			return false;
		if (zweitWahl == null) {
			if (other.zweitWahl != null)
				return false;
		} else if (!zweitWahl.equals(other.zweitWahl))
			return false;
		return true;
	}
	
	
}
