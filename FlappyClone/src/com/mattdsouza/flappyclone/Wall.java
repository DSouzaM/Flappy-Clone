package com.mattdsouza.flappyclone;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Wall extends Entity {
	public Shape hitbox;
	public static final int SPRITE_WIDTH = 40;
	public static final int SPRITE_HEIGHT = 600;
	public Wall() {
		this("wall");
	}
	public Wall(String imgName){
		super(imgName, SPRITE_WIDTH, SPRITE_HEIGHT);
		hitbox = new Rectangle(location.getX(),location.getY(),SPRITE_WIDTH,SPRITE_HEIGHT);
	}
	@Override
	public int getSpriteWidth() {
		return SPRITE_WIDTH;
	}
	@Override
	public int getSpriteHeight() {
		return SPRITE_HEIGHT;
	}
	@Override
	public void update(){
		super.update();
		hitbox.setX(location.getX());
		hitbox.setY(location.getY());
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
}
