package com.mattdsouza.flappyclone;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

enum State {
	START, PLAYING, END;
}

public class Game extends BasicGame {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final float GRAVITY = .6f;
	private static final float MAX_GRAVITY = 10;
	private static final float WALL_VELOCITY = -5;
	private static final float JUMP_VELOCITY = -12;
	private static final float BACKGROUND_VELOCITY = -1;

	private State gameState;
	private Player player;
	private Entity background;
	private Image titleScreen;
	private int timeSinceLastUpdate;
	private int defaultX, defaultY;
	private int score;
	private ArrayList<Wall> wallList;
	private Random rand;
	private HighScoreController highscores;

	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getGraphics().setBackground(new Color(0x0f84d2));
		titleScreen = new Image("res/sprites/titleScreen.png");

		background = new Entity("background", 2400, 600, 0);
		background.setXVelocity(BACKGROUND_VELOCITY);
		player = new Player();
		defaultX = (gc.getWidth() - player.getImageWidth()) / 2;
		defaultY = (gc.getHeight() - player.getImageHeight()) / 2;
		player.setLocation(defaultX, defaultY);
		player.setVelocity(0, 0);
		gameState = State.START;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		timeSinceLastUpdate += delta;

		while (timeSinceLastUpdate / 17 > 0) { // for every 17ms tick (60fps)
			background.update();
			if (background.getX() <= -1600) {
				background.setX(0);
			}

			// code dependent on State of program
			if (gameState == State.START) {
				if (input.isKeyPressed(Input.KEY_SPACE)) {
					startGame();
					gameState = State.PLAYING;
				}
			} else if (gameState == State.PLAYING) {
				// player
				applyGravity(player);
				player.update();
				if (player.getRotation() < 30) {
					player.rotate(1);
				}
				if (input.isKeyPressed(Input.KEY_SPACE)) {
					player.setYVelocity(JUMP_VELOCITY);
					player.getImage().setRotation(-30);
				}
				// walls
				updateWalls();

				if (wallList.get(wallList.size() - 1).getX() < WIDTH - 400) {
					createWalls();
					score++;
				}
				if (checkCollision()) {
					endGame();
				}
			} else if (gameState == State.END) {
				if (player.getX() > 0-player.getImageWidth()){
					player.update();
					input.clearKeyPressedRecord();
				}
				else if (input.isKeyPressed(Input.KEY_SPACE)) {
					startGame();
				}
				updateWalls();
			}
			timeSinceLastUpdate -= 17;
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		g.drawImage(background.getImage(), background.getX(), background.getY());
		g.drawImage(player.getImage(), player.getX(), player.getY());
		if (gameState == State.START) {
			g.drawImage(titleScreen, (WIDTH - titleScreen.getWidth()) / 2, 50);
		} else {
			for (Wall wall : wallList) {
				g.drawImage(wall.getImage(), wall.getX(), wall.getY());
			}
			if (gameState == State.PLAYING) {
				g.drawString("Score: " + score, 10, HEIGHT - 20);
			} else if (gameState == State.END) {
				// TODO display menu with high scores, option to add high score
				// if applicable, prompt to restart and prompt to change mode
			}
		}
	}

	public void startGame() {
		System.out.println("startGame() called");
		player.setLocation(defaultX, defaultY);
		player.setVelocity(0, 0);
		player.setRotation(0);
		timeSinceLastUpdate = 0;
		score = 0;
		wallList = new ArrayList<Wall>();
		rand = new Random();
		createWalls();
		gameState = State.PLAYING;
	}

	public void endGame() {
		System.out.println("endGame() called");
		player.setYVelocity(0);
		player.setXVelocity(WALL_VELOCITY);
		highscores = new HighScoreController();
		System.out.println(highscores.entryList);
		gameState = State.END;
	}

	public void applyGravity(Entity entity) {
		if (entity.getVY() < MAX_GRAVITY) {
			entity.setYVelocity(entity.getVY() + GRAVITY);
		}
	}
	public void updateWalls(){
		for (int i = 0; i < wallList.size(); i++) {
			Wall wall = wallList.get(i);
			wall.update();
			if (wall.getX() < -1 * wall.getImageWidth() - 5) {
				wallList.remove(i);
				i--;
			}
		}
	}

	private void createWalls() {
		Wall w1 = new Wall();
		Wall w2 = new Wall();
		int yPos = rand.nextInt(400) + 50 - w1.getImageHeight();
		w1.setLocation(WIDTH, yPos);
		w1.setXVelocity(WALL_VELOCITY);
		w1.setVisible(true);
		w2.setLocation(WIDTH,
				yPos + 11 * player.getImageHeight() / 3 + w2.getImageHeight());
		w2.setXVelocity(WALL_VELOCITY);
		w2.setVisible(true);
		wallList.add(w1);
		wallList.add(w2);
	}

	public boolean checkCollision() {
		boolean collision = false;
		for (Wall wall : wallList) {
			if (player.collidesWith(wall)) {
				collision = true;
				break;
			}
		}
		return collision;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public static void main(String[] args) {
		try {
			AppGameContainer flappyGame = new AppGameContainer(new Game(
					"Flappy Clone"));
			flappyGame.setDisplayMode(WIDTH, HEIGHT, false);
			flappyGame.setVSync(true);
			flappyGame.setShowFPS(false);

			flappyGame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
