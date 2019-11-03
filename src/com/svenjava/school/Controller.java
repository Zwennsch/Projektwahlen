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
		System.out.println("Button gedrueckt");
		String name = vorname.getText();
		String nachN = nachname.getText();
		Schueler s = new Schueler(name, nachN, Klassenstufe.ACHT );
		Kurs k1 = cbErstwahl.getValue();
		Kurs k2 = cbZweitwahl.getValue();
		Kurs k3 = cbDrittwahl.getValue();
		Wahl w = new Wahl(k1, k2, k3, s);
		s.makeWahl(w);
		System.out.println(w);
		System.out.println(rbEight.isSelected()+ " " + rbNine.isSelected()+ " "+ rbTen.isSelected());
		
	}
	@FXML
	private void showListCB1(ActionEvent e) {
		System.out.println("showing");
		cbErstwahl.getItems().addAll(courses);
		cbZweitwahl.getItems().addAll(courses);
		cbDrittwahl.getItems().addAll(courses);
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
