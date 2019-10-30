package com.svenjava.school;

public class MainWahl {
	static boolean alleGewählt = false;

	public static void main(String[] args) {
		while(!alleGewählt) {
			startNewSchueler();
			
		}
		
	}

	private static void startNewSchueler() {
		greetings();
	}

	private static void greetings() {
		System.out.println("Hier wählst du deinen WP-Kurs für dieses Halbjahr");
		
		
	}
	

}
