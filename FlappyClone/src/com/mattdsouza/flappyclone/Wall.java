package com.mattdsouza.flappyclone;

public class Wall extends Entity {
	private static final int SPRITE_WIDTH = 40;
	private static final int SPRITE_HEIGHT = 400;
	public Wall() {
		super("wall");
	}
	public Wall(String imgName){
		super(imgName);
	}
	@Override
	protected int getSpriteWidth() {
		return SPRITE_WIDTH;
	}
	@Override
	protected int getSpriteHeight() {
		return SPRITE_HEIGHT;
	}
}
