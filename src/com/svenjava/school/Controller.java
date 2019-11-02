package com.svenjava.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
	Map<Schueler, Integer> schuelerList = new HashMap<>();
	List<Kurs> courses = FxMain.coursesList;
	static {
		
	}
	@FXML
	TextField vorname;
	@FXML
	TextField nachname;
	
	@FXML
	private void fillInPupil(ActionEvent e) {
		System.out.println("Button gedrueckt");
		String name = vorname.getText();
		String nachN = nachname.getText();
		Schueler s = new Schueler(name, nachN, Klassenstufe.ACHT );
		Wahl w = new Wahl(courses.get(0), courses.get(1), courses.get(3), s);
		s.makeWahl(w);
		System.out.println(w);
		
	}
	

}
