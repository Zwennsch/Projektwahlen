package com.svenjava.school;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class Controller {
	private String name, nachN;
	
	@FXML
	private TextField vorname, nachname;
	@FXML
	private RadioButton rbEight, rbNine, rbTen;
//	This works even without setting the ToggleGroup here. It looks for the binding in the fxml file
	@FXML
	private ToggleGroup tgStufen;
	@FXML
	private ChoiceBox<Kurs> cbErstwahl, cbZweitwahl, cbDrittwahl; 
	@FXML
	private MenuItem saveMenu, finishMenu, loadMenu;
	
	Toggle standard = rbEight;
	Alert confirmed  = new Alert(AlertType.INFORMATION);
	Alert loaded  = new Alert(AlertType.INFORMATION);
	Alert wrongEntry = new Alert(AlertType.ERROR);
	Alert wrongSave = new Alert(AlertType.ERROR);
	Alert saveData = new Alert(AlertType.INFORMATION);
	
	/**
	 * This method will automatically bee invoked from the FXMLLoader 
	 */
	public void initialize() {
		cbErstwahl.getItems().addAll(FxMain.coursesList);
		cbZweitwahl.getItems().addAll(FxMain.coursesList);
		cbDrittwahl.getItems().addAll(FxMain.coursesList);
		cbErstwahl.setValue(FxMain.coursesList.get(0));
		cbZweitwahl.setValue(FxMain.coursesList.get(0));
		cbDrittwahl.setValue(FxMain.coursesList.get(0));
		confirmed.setContentText("Nächste(r) Schüler(in) bitte!");
		wrongEntry.setContentText("Bitte korrigieren Sie die Eingabe");
		wrongEntry.setHeaderText("Eingabe fehlerhaft");
		saveData.setHeaderText("Kurse wurden berechnet");
		saveData.setContentText("Die Kurse wurden in Dateien gespeichert. Eingabe beendet.");
	}
	
	@FXML
	private void fillInPupil(ActionEvent e) {
		name = vorname.getText();
		nachN = nachname.getText();
		Klassenstufe stufe ;
		Kurs k1 = cbErstwahl.getValue();
		Kurs k2 = cbZweitwahl.getValue();
		Kurs k3 = cbDrittwahl.getValue();
		if(InputChecker.isValid(name, nachN, tgStufen, k1,k2,k3)){
			String stufeString = ((RadioButton) tgStufen.getSelectedToggle()).getText();
			stufe = getStufe(stufeString);
			Wahl w = new Wahl(k1, k2, k3);
			Schueler s = new Schueler(name, nachN, stufe, w);
			Schueler.alleSchueler.add(s);
			DataHandler.appendLine(s);
			confirmed.setHeaderText("O.K. "+name+", Eintrag gespeichert");
			confirmed.show();
			System.out.println(s.getId());
			clearScreenForNewEntry();
		}else {
			wrongEntry.setContentText(InputChecker.errorMessage);
			wrongEntry.show();
		}
	}
	
	private void clearScreenForNewEntry() {
		vorname.setText("");
		nachname.setText("");
		tgStufen.selectToggle(rbEight);
		rbEight.setSelected(false);
		cbErstwahl.setValue(FxMain.coursesList.get(0));
		cbZweitwahl.setValue(FxMain.coursesList.get(0));
		cbDrittwahl.setValue(FxMain.coursesList.get(0));
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
	private void loadState(ActionEvent e) {
		System.out.println("Loading");
		try {
			DataHandler.readStudentDataFromFile("src/com/svenjava/school/files/studentList.txt");
			System.out.println(Schueler.alleSchueler.get(0).getWahl().drittWahl);
			loaded.setContentText("Die Daten der Schüler wurden geladen");
			loaded.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@FXML
	private void saveState(ActionEvent e) {
		/*
		 * Still to implement: Append to an already existing file! So far the file gets overridden every time user saves the current state
		 * But this actually isn't much of a problem as long as the user loads the already existing file in advance..
		 * Still not sure how to implement this
		 */
		System.out.println("Saving");
		try {
			DataHandler.saveStudentsToFile(Schueler.alleSchueler, "src/com/svenjava/school/files/studentList.txt");
			saveData.setContentText("Schülerdaten wurden gespeichert");
			saveData.show();
		} catch (IOException e1) {
			System.out.println("Error while saving the students!");
			wrongSave.setContentText("Fehler beim Speichern! Programm nicht schließen und Lehrer informieren!");
			wrongSave.show();
			e1.printStackTrace();
		}
	}
	@FXML
	private void calculateCourses(ActionEvent e) throws IOException {
		System.out.println("Calculating");
		new CourseCreator(Schueler.alleSchueler, FxMain.coursesList);
		CourseCreator.calculateCourses();
		DataHandler.saveCourses(CourseCreator.finalCoursestoFill);
		DataHandler.saveFinalStudentList(CourseCreator.allStudents);
		saveData.show();
	}

}
