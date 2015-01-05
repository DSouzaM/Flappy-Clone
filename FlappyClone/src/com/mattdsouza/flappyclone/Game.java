package com.mattdsouza.flappyclone;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	private Player player;
	private int timeSinceLastUpdate;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final float GRAVITY = 0.5f;
	private static final int WALL_VELOCITY = -5;
	private static final int DISTANCE_BETWEEN_WALLS = 400;
	private ArrayList<Wall> wallList;
	private Random rand;
	
	public Game(String title) {
		super(title);
	}
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getHeight();
		player = new Player("smile");
		player.setLocation((gc.getWidth()-player.getImageWidth())/2, (gc.getHeight()-player.getImageHeight())/2);
		player.setVelocity(0, -1);
		timeSinceLastUpdate = 0;

		
		wallList = new ArrayList<Wall>();
		rand = new Random();
		createWalls();
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		timeSinceLastUpdate += delta;
		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)){
			player.setYVelocity(-18);
		}
				
		while (timeSinceLastUpdate/17 > 0) {
			applyGravity(player);
			player.update();
			timeSinceLastUpdate-=17;
			for (int i = 0; i < wallList.size(); i++){
				Wall wall = wallList.get(i);
				wall.update();
				if (wall.getX() < -1*wall.getImageWidth()){
					wallList.remove(i);
				}
			}
		}
		if (wallList.get(wallList.size()-1).getX() < WIDTH-DISTANCE_BETWEEN_WALLS){
			createWalls();
		}
	}
		
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(player.getImage(), player.getX(), player.getY());
		for (Wall wall : wallList){
			g.drawImage(wall.getImage(), wall.getX(), wall.getY());
		}
	}
	
	
	public void applyGravity(Entity entity){
		entity.setYVelocity(entity.getVY()+GRAVITY);
	}
	
	private void createWalls(){
		System.out.println("Creating new walls " + wallList.size());
		Wall w1 = new Wall("wall");
		Wall w2 = new Wall("wall");
		int yPos = rand.nextInt(400) + 100;
		w1.setLocation(WIDTH, yPos);
		w1.setXVelocity(WALL_VELOCITY);
		w1.setVisible(true);
		w2.setLocation(WIDTH, yPos+w2.getImageHeight()+100);
		w2.setXVelocity(WALL_VELOCITY);
		w2.setVisible(true);
		wallList.add(w1);
		wallList.add(w2);
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer flappyGame = new AppGameContainer(new Game("Flappy Clone"));
			flappyGame.setDisplayMode(WIDTH, HEIGHT, false);
			flappyGame.setVSync(true);
			
			flappyGame.start();
		} catch (SlickException e){
			e.printStackTrace();
		}
	}	
	
}
