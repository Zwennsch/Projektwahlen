package com.svenjava.school;

import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
	Map<Schueler, Integer> schuelerList = new HashMap<>();
	List<Kurs> courses = FxMain.coursesList;
	private int added;
	
	@FXML
	TextField vorname;
	@FXML
	TextField nachname;
	@FXML
	RadioButton rbEight, rbNine, rbTen;
//	This works even without setting the ToggleGroup here. It looks for the binding in the fxml file
	@FXML
	ToggleGroup tgStufen;
	@FXML
	ChoiceBox<Kurs> cbErstwahl; 
	@FXML
	ChoiceBox<Kurs> cbZweitwahl; 
	@FXML
	ChoiceBox<Kurs> cbDrittwahl; 
	
	@FXML
	private void fillInPupil(ActionEvent e) {
//		checkForCorrectName();
//		checkForKlasse();
		System.out.println("Button gedrueckt");
		String name = vorname.getText();
		String nachN = nachname.getText();
		Klassenstufe stufe ;
		String stufeString = ((RadioButton) tgStufen.getSelectedToggle()).getText();
		Kurs k1 = cbErstwahl.getValue();
		Kurs k2 = cbZweitwahl.getValue();
		Kurs k3 = cbDrittwahl.getValue();
		if(InputChecker.isValid(name, nachN, stufeString, k1,k2,k3)){
			System.out.println("Wahl korrekt");
			if(stufeString.equals("8")) {
				stufe = Klassenstufe.ACHT;
			}else if(stufeString.equals("9")) {
				stufe = Klassenstufe.NEUN;
			}else {
				stufe = Klassenstufe.ZEHN;
			}
			Schueler s = new Schueler(name, nachN, stufe);
			Wahl w = new Wahl(k1, k2, k3, s);
			s.makeWahl(w);
			System.out.println(w);
		}
		
	}
	@FXML
	private void showListCB1(ActionEvent e) {
		added++;
		
		System.out.println("showing");
		if(added == 1) {
			cbErstwahl.getItems().addAll(courses);
			cbZweitwahl.getItems().addAll(courses);
			cbDrittwahl.getItems().addAll(courses);
		}
	}
	

	@FXML
	private void loadList(ActionEvent e) {
		System.out.println("Loading List");
//		System.out.println(e.getSource());
//		for(Kurs k : courses) {
//			cbErstwahl.getItems().add(k);
//		}
	}
	
	

}
