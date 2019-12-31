package com.svenjava.school;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DataHandler {
	static BufferedReader br;
	static BufferedWriter bw;
	static File coursesFile, studentsFile;
	
	
	static void loadCoursesIntoList(List<Kurs> coursesList) throws NumberFormatException, IOException {
		try {
			coursesFile = new File("src/com/svenjava/school/files/courses.txt");
			br = new BufferedReader(new FileReader(coursesFile));
			String line;
			while((line=br.readLine())!= null) {
				String[] data = line.split(",");
				coursesList.add(new Kurs(data[0], Integer.parseInt(data[1])));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Datei nicht gefunden!");
		}
		System.out.println("Anzahl geladener Kurse: " +Kurs.getTotalNumberOfCourses());
	}
	
	static void saveStudentsToFile(List<Schueler> studentsSoFar, String fileName) throws IOException {
		studentsFile = new File(fileName);
		try {
			if(studentsFile.createNewFile()) {
				System.out.println("File created: "+ studentsFile.getName());
			}else {
				System.out.println("File exists already");
			}
		} catch (IOException e) {
			System.out.println("Wrong path");
			e.printStackTrace();
		}
		bw = new BufferedWriter(new FileWriter(studentsFile));
		System.out.println("Writing to file...");
		for (Schueler s : studentsSoFar) {
			String line = s.getId()+"," +s.getVorname()+ "," + s.getNachname()+ ","+ s.getKlassenstufe()+","
					+ s.getWahl().erstWahl+","+s.getWahl().zweitWahl+","+ s.getWahl().drittWahl;
			bw.write(line);
			bw.newLine();
		}
		bw.close();
		
		
	}
	/**
	 * reads the student date from a csv-file into the List with all students only if the file already exists
	 * @param fileName 
	 * the filename  to read from
	 * @throws IOException
	 */
	static void readStudentDataFromFile(String fileName) throws IOException {
		studentsFile = new File(fileName);
		if(studentsFile.exists()) {
			br = new BufferedReader(new FileReader(studentsFile));
			String line;
			while((line=br.readLine())!= null) {
				String[] data = line.split(",");
				int k1 = 0;
				int k2 = 0;
				int k3 = 0;
				for(int i = 0; i < FxMain.coursesList.size(); i++) {
					if(FxMain.coursesList.get(i).toString().equals(data[4])) {
						k1 = i;
					}
					if(FxMain.coursesList.get(i).toString().equals(data[5])) {
						k2 = i;
					}
					if(FxMain.coursesList.get(i).toString().equals(data[6])) {
						k3 = i;
					}
				}
				Schueler s = new Schueler(data[1], data[2], Klassenstufe.valueOf(data[3]) , new Wahl(FxMain.coursesList.get(k1), FxMain.coursesList.get(k2), FxMain.coursesList.get(k3)));
				Schueler.alleSchueler.add(s);
			}
			br.close();
		}
	}
	
	static void appendLine(Schueler s) {
		String studentData = s.getId()+"," +s.getVorname()+ "," + s.getNachname()+ ","+ s.getKlassenstufe()+","
				+ s.getWahl().erstWahl+","+s.getWahl().zweitWahl+","+ s.getWahl().drittWahl+ "\n";
		try {
			Files.write(Paths.get(studentsFile.getPath()), studentData.getBytes(), StandardOpenOption.APPEND);
			System.out.println("name appended");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Something went wrong!!");
		}
	}

}
