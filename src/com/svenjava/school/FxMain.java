package com.svenjava.school;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		loadCourses();
		Schueler.alleSchueler = new ArrayList<Schueler>();
	}

	private void loadCourses() throws IOException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("src/com/svenjava/school/files/courses.txt")));
			String line;
			while((line=br.readLine())!= null) {
				String[] data = line.split(",");
				coursesList.add(new Kurs(data[0], Integer.parseInt(data[1])));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Datei nicht gefunden!");
		}
		System.out.println("Anzahl geladener Kurse: " +Kurs.getTotalNumberOfCourses());
		
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
