package com.svenjava.school;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxMain extends Application {
	static List<Kurs> coursesList = new ArrayList<>();
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
		DataHandler.loadCoursesIntoList(coursesList);
		Schueler.alleSchueler = new ArrayList<Schueler>();
	}



	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main2.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("WP-Kurswahl");
		stage.setResizable(false);
		
		stage.show();
		
	}

}
