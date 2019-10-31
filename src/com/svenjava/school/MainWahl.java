package com.svenjava.school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class MainWahl {
	static final int MINIMUM_NUMBER_OF_COURSES = 10;
	static boolean alleGewählt = false;
	public static Map<Integer, String> kurse = new HashMap<>();  

	public static void main(String[] args) {
		fillTheList();
//		while(!alleGewählt) {
//			startNewSchueler();
//			
//		}
		
	}

	private static void fillTheList() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int id = 1;
		int total = -1;
		while(total < MINIMUM_NUMBER_OF_COURSES) {
			try {
				System.out.println("Bitte geben Sie zunächst an, wie viele Kurse Sie anbieten: ");
				total = Integer.parseInt(br.readLine());
				
			} catch (NumberFormatException | IOException e) {
				System.out.println("Der angegebene Wert ist zu klein, oder keine Zahl.\nBite wiederholen Sie die Eingabe");
			}
		}
		System.out.println("Bitte richten Sie nun die " +total+ " Kurse ein:");
		
		System.out.println("Bitte geben Sie nacheinander alle Kurse an.\nJedem Kurs wird automatisch eine Nummer zugeordent");
		
		for(int i = 0; i < total; i++) {
			System.out.println("Kurs Nr. "+ id++ + ": ");
			try {
				String c = br.readLine();
//				System.out.println();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	

	private static void startNewSchueler() {
		greetings();
	}

	private static void greetings() {
		System.out.println("Hier wählst du deinen WP-Kurs für dieses Halbjahr");
		
		
	}
	

}
