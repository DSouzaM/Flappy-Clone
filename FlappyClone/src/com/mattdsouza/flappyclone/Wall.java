package com.mattdsouza.flappyclone;

import org.newdawn.slick.geom.Rectangle;

public class Wall extends Entity {
	private static final int SPRITE_WIDTH = 40;
	private static final int SPRITE_HEIGHT = 600;
	public Wall() {
		this("wall");
	}
	public Wall(String imgName){
		super(imgName);
		hitbox = new Rectangle(location.getX(),location.getY(),SPRITE_WIDTH,SPRITE_HEIGHT);
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
