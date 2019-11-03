package com.svenjava.school;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;


public class MainWahl {
	static final int MINIMUM_NUMBER_OF_COURSES = 10;
	static boolean alleGewählt = false;
	public static Map<Integer, String> kurse = new HashMap<>();  

	public static void main(String[] args) {
		startUI(args);
		
	}

	private static void startUI(String[] args) {
		Application.launch(FxMain.class, args);
	}

}
