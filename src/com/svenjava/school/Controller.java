package com.svenjava.school;



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
	private MenuItem saveMenu, finishMenu;
	
	Toggle standard = rbEight;
	Alert confirmed  = new Alert(AlertType.INFORMATION);
	Alert wrongEntry = new Alert(AlertType.ERROR);
	
	/*
	 * This method will automatically bee invoked from the FXMLLoader 
	 */
	public void initialize() {
		cbErstwahl.getItems().addAll(FxMain.coursesList);
		cbZweitwahl.getItems().addAll(FxMain.coursesList);
		cbDrittwahl.getItems().addAll(FxMain.coursesList);
		cbErstwahl.setValue(FxMain.coursesList.get(0));
		cbZweitwahl.setValue(FxMain.coursesList.get(0));
		cbDrittwahl.setValue(FxMain.coursesList.get(0));
		tgStufen.selectToggle(rbEight);
		confirmed.setContentText("Nächste(r) Schüler(in) bitte!");
		wrongEntry.setContentText("Bitte korrigieren Sie die Eingabe");
		wrongEntry.setHeaderText("Eingabe fehlerhaft");
	}
	
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
			Wahl w = new Wahl(k1, k2, k3);
			s.makeWahl(w);
			Schueler.alleSchueler.add(s);
			confirmed.setHeaderText("O.K. "+name+" ,Eintrag gespeichert");
			confirmed.show();
			System.out.println(s.getKlassenstufe());
			clearScreenForNewEntry();
		}else {
			wrongEntry.show();
		}
	}
	
	private void clearScreenForNewEntry() {
		vorname.setText("");
		nachname.setText("");
		tgStufen.selectToggle(rbEight);
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
	private void saveState(ActionEvent e) {
		System.out.println("Saving");
	}
	@FXML
	private void calculateCourses(ActionEvent e) {
		System.out.println("Calculating");
		System.out.println(Schueler.alleSchueler.get(0));
		new CourseCreator(Schueler.alleSchueler, FxMain.coursesList);
		CourseCreator.calculateCourses();
	}

}
