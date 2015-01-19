package com.mattdsouza.flappyclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class HighScoreController {
	private static final int TOTAL_SCORES = 3;
	private Scanner scanner;
	private PrintWriter printWriter;
	private ArrayList<Entry> entryList;
	private static final File HIGHSCORES_FILE = new File("res/highscores.txt");

	class Entry {
		private String name;
		private int score;

		public Entry(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public String toString() {
			return String.format("%1$8s    %2$d", name, score);
		}
	}

	public HighScoreController() {
		updateList();
	}
	public void updateList() {
		entryList = new ArrayList<Entry>();
		try {
			scanner = new Scanner(HIGHSCORES_FILE);
			for (int i = 0; i < TOTAL_SCORES; i++) {
				entryList.add(new Entry(scanner.next(), scanner.nextInt()));
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println("highscores.txt file missing!");
			fnfe.printStackTrace();
		} catch (Exception e) {
			System.err.println("highscores.txt file corrupted!");
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	public boolean checkNewScore(int score) {
		if (score > entryList.get(TOTAL_SCORES - 1).getScore()) {
			return true;
		} else {
			return false;
		}
	}
	public void addNewScore(String name, int score) {
		Entry newEntry = new Entry(name,score);
		for (int i = 0; i<TOTAL_SCORES; i++) {
			if (entryList.get(i).getScore() < score) {
				entryList.add(i, newEntry);
				entryList.remove(TOTAL_SCORES);
				break;
			}
		}
		try {
			printWriter = new PrintWriter(HIGHSCORES_FILE);
			for (Entry entry : entryList) {
				printWriter.write(entry.getName() + " " + entry.getScore() + "\n");
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println("highscores.txt file missing!");
		} finally {
			printWriter.close();
		}
	}

	public String getHighScores() {
		StringBuilder allScores = new StringBuilder("High Scores:");
		for (int i = 0; i < TOTAL_SCORES; i++) {
			allScores.append("\n" + entryList.get(i));			
		}
		return allScores.toString();
	}
}
