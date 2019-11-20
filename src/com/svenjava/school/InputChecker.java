package com.svenjava.school;

import javafx.scene.control.ToggleGroup;

public class InputChecker {
	
	static String errorMessage = "";


	public static boolean  isValid(String name, String nachN, ToggleGroup group, Kurs k1, Kurs k2, Kurs k3) {
		if(!isNameValid(name, nachN) || !isStufeValid(group) || !isWahlValid(k1, k2, k3)) {
			return false;
		}
		
		return true;
		
	}

	private static boolean isWahlValid(Kurs k1, Kurs k2, Kurs k3) {
		if((k1.equals(k2)) || (k2.equals(k3)) || (k1.equals(k3))) {
			errorMessage = "2 gleiche Kurse!";
			return false;
			
		}
		
		return true;
	}

	private static boolean isStufeValid(ToggleGroup group) {
		if(group.getSelectedToggle() == null) {
			errorMessage = "Bitte Klassenstufe wählen";
			return false;
		}
		else return true;
//		switch(stufeString) {
//		case "8":
//			return true;
//		case "9":
//			return true;
//		case "10":
//			return true;
//		default:
//			System.out.println("keine Stufe gewählt!");
//			return false;
//		}
	}

	private static boolean isNameValid(String name, String nachN) {
		if(name.equals("") || nachN.equals("")) {
			errorMessage = "Bitte Namen korrekt eingeben!";
			return false;
		}
		
		return true;
	}
	

}
