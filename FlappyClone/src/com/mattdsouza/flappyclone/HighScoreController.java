package com.mattdsouza.flappyclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreController {
	private static final int totalScores = 3;
	Scanner scanner;
	ArrayList<Entry> entryList;
	
	class Entry {
		private String name;
		private int score;
		public Entry(String name, int score) {
			this.name = name;
			this.score = score;
		}
		public String getName(){
			return name;
		}
		public int getScore() {
			return score;
		}
		public void setName(String name){
			this.name = name;
		}
		public void setScore(int score) {
			this.score = score;
		}
		@Override
		public String toString(){
			return name + " - " + score;
		}
	}

	public HighScoreController() {
		entryList = new ArrayList<Entry>();
		try {
			scanner = new Scanner(new File("res/highscores.txt"));
			for (int i = 0; i < totalScores; i++) {
				entryList.add(new Entry(scanner.next(), scanner.nextInt()));
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println("highscores.txt file missing!");
			fnfe.printStackTrace();
		} catch (Exception e) {
			System.err.println("highscores.txt file corrupted!");
			e.printStackTrace();
		}
	}
}
