package com.mattdsouza.flappyclone;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	private Player player;
	private int timeSinceLastUpdate;
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 900;
	private static final float GRAVITY = 0.5f;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(player.getImage(), player.getX(), player.getY());
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getHeight();
		player = new Player("smile");
		player.setLocation((gc.getWidth()-player.getImageWidth())/2, (gc.getHeight()-player.getImageHeight())/2);
		player.setVelocity(0, -1);
		timeSinceLastUpdate = 0;
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
		}	
	}
	
	public void applyGravity(Entity entity){
		entity.setYVelocity(entity.getVY()+GRAVITY);
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer flappyGame = new AppGameContainer(new Game("Flappy MacKinnon"));
			flappyGame.setDisplayMode(WIDTH, HEIGHT, false);
			flappyGame.setVSync(true);
			
			flappyGame.start();
		} catch (SlickException e){
			e.printStackTrace();
		}
	}
	
	
}
