package com.mattdsouza.flappyclone;

public class Player extends Entity {
	private static final int SPRITE_WIDTH = 72;
	private static final int SPRITE_HEIGHT = 40;
	
	public Player() {
		super("Fish");
	}
	public Player(String imgName){
		super(imgName);
	}
	@Override
	public int getSpriteWidth() {
		return SPRITE_WIDTH;
	}
	@Override
	protected int getSpriteHeight() {
		return SPRITE_HEIGHT;
	}
	
}
