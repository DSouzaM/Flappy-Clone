package com.mattdsouza.flappyclone;

import org.newdawn.slick.Sound;


public class AudioController {
	private Sound jump, death, goal, newhighscore;
	private String dir;
	public AudioController() {
		dir = "audio";
		initSounds();
	}
	public void initSounds(){
		try {
		jump = new Sound("res/" + dir + "/jump.wav");
		goal = new Sound("res/" + dir + "/goal.wav");
		death = new Sound("res/" + dir + "/death.wav");
		newhighscore = new Sound("res/" + dir + "/newhighscore.wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void swapModes() {
		if (dir.equals("audio")) {
			dir = "mlgaudio";
		} else {
			dir = "audio";
		}
		stopAll(); 
	}
	
	public void playJump(){
		jump.play();
	}
	public void stopJump() {
		jump.stop();
	}
	public void playGoal() {
		goal.play();
	}
	public void stopGoal() {
		goal.stop();
	}
	public void playDeath() {
		death.play();
	}
	public void stopDeath(){
		death.stop();
	}
	public void playHighScore() {
		newhighscore.play();
	}
	public void stopHighScore() {
		newhighscore.stop();
	}
	public void stopAll() {
		jump.stop();
		goal.stop();
		death.stop();
		newhighscore.stop();
	}
	

}
