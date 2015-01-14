package com.mattdsouza.flappyclone;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreController {
	Scanner scanner;
	public HighScoreController() {
		try{
		scanner = new Scanner(new File("res/highscores.txt"));		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
