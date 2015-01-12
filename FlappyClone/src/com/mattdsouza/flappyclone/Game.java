package com.mattdsouza.flappyclone;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final float GRAVITY = .6f;
	private static final float MAX_GRAVITY = 10;
	private static final int DISTANCE_BETWEEN_WALLS = 400;
	private static final float WALL_VELOCITY = -5;
	private static final float JUMP_VELOCITY = -12;
	private static final float BACKGROUND_VELOCITY = -1;
	
	private State gameState;	
	private Player player;	
	private Entity background; 
	private int timeSinceLastUpdate;
	private ArrayList<Wall> wallList;
	private Random rand;

	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getHeight();
		gc.getGraphics().setBackground(new Color(0x0f84d2));
		background = new Entity("background",2400,600);
		background.setXVelocity(BACKGROUND_VELOCITY);
		
		
		player = new Player();
		player.setLocation((gc.getWidth() - player.getImageWidth()) / 2,
				(gc.getHeight() - player.getImageHeight()) / 2);
		player.setVelocity(0, 0);
		gameState = State.START;
		startGame();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		timeSinceLastUpdate+=delta;
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			player.setYVelocity(JUMP_VELOCITY);
			player.getImage().setRotation(-30);
		}
		
		while (timeSinceLastUpdate / 17 > 0) { // for every 17ms tick (60fps)
			background.update();
			if (background.getX() <= -1600){
				background.setX(0);
			}
			System.out.println(background.getX());
			
			
			//code dependent on State of program
			if (gameState == State.START) {
				
				
				
			} else if (gameState == State.PLAYING) {
					// player
					applyGravity(player);
					player.update();
					if (player.getRotation() < 30) {
						player.rotate(1);
					}

					// walls
					for (int i = 0; i < wallList.size(); i++) {
						Wall wall = wallList.get(i);
						wall.update();
						if (wall.getX() < -1 * wall.getImageWidth()-5) {
							wallList.remove(i);
							i--;
						}					
					}
					timeSinceLastUpdate -= 17;
				
				if (wallList.get(wallList.size() - 1).getX() < WIDTH
						- DISTANCE_BETWEEN_WALLS) {
					createWalls();
				}
				if (checkCollision()) {
					player.setRotation(180);
					// TODO change to endGame() method once created
				}
			}
		}
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		g.drawImage(background.getImage(), background.getX(),background.getY());
		g.drawImage(player.getImage(), player.getX(), player.getY());
		if (gameState == State.START) {
			//TODO display menu with prompt to start and prompt to change mode
		} else if (gameState == State.PLAYING) {
			//TODO display score, lives 
			for (Wall wall : wallList) {
				g.drawImage(wall.getImage(), wall.getX(), wall.getY());
			}
		} else if (gameState == State.END){
			//TODO display menu with high scores, option to add high score if applicable, prompt to restart and prompt to change mode
		}
	}

	public void startGame() {
		timeSinceLastUpdate = 0;
		wallList = new ArrayList<Wall>();
		rand = new Random();
		createWalls();
		gameState = State.PLAYING;
	}

	public void applyGravity(Entity entity) {
		if (entity.getVY() < MAX_GRAVITY) {
			entity.setYVelocity(entity.getVY() + GRAVITY);
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

			flappyGame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
