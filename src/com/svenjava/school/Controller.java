package com.svenjava.school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class Controller {
	Map<Schueler, Integer> schuelerList = new HashMap<>();
	List<Kurs> courses = FxMain.coursesList;
	String name, nachN;
	private int added;
	
//	@FXML
	Alert confirmed  = new Alert(AlertType.INFORMATION);
	Alert wrongEntry = new Alert(AlertType.ERROR);
	
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
	Toggle standard = rbEight;
	
	@FXML
	private void fillInPupil(ActionEvent e) {
		name = vorname.getText();
		nachN = nachname.getText();
		Klassenstufe stufe ;
		String stufeString = ((RadioButton) tgStufen.getSelectedToggle()).getText();
		Kurs k1 = cbErstwahl.getValue();
		Kurs k2 = cbZweitwahl.getValue();
		Kurs k3 = cbDrittwahl.getValue();
		if(InputChecker.isValid(name, nachN, stufeString, k1,k2,k3)){
			stufe = getStufe(stufeString);
			Schueler s = new Schueler(name, nachN, stufe);
			Wahl w = new Wahl(k1, k2, k3, s);
			s.makeWahl(w);
			System.out.println(w);
			confirmed.setContentText("Eingabe gespeichert!\nNächster Schüler bitte!");
			confirmed.show();
			clearScreenForNewEntry();
		}else {
			wrongEntry.setContentText("Falsche Eingabe!\nBitte korrigieren Sie die Eingabe");
			wrongEntry.show();
		}
		
	}
	
	
	
	private void clearScreenForNewEntry() {
		vorname.setText("");
		nachname.setText("");
		tgStufen.selectToggle(standard);
	}



	private Klassenstufe getStufe(String stufeString) {
		Klassenstufe stufe;
		if(stufeString.equals("8")) {
			stufe = Klassenstufe.ACHT;
		}else if(stufeString.equals("9")) {
			stufe = Klassenstufe.NEUN;
		}else {
			stufe = Klassenstufe.ZEHN;
		}
		return stufe;
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
