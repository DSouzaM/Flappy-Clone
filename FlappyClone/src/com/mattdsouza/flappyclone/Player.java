package com.mattdsouza.flappyclone;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Player extends Entity {
	private static final int SPRITE_WIDTH = 72;
	private static final int SPRITE_HEIGHT = 40;
	
	public Player() {
		this("Fish");
	}
	public Player(String imgName){
		super(imgName);
		hitbox = new Ellipse(location.getX(),location.getY(),36,20);
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
