package com.mattdsouza.flappyclone;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Player extends Entity {
	public Shape hitbox;
	private static final int SPRITE_WIDTH = 72;
	private static final int SPRITE_HEIGHT = 40;
	
	public Player() {
		this("Fish");
	}
	public Player(String imgName){
		super(imgName, SPRITE_WIDTH, SPRITE_HEIGHT);
		hitbox = new Ellipse(location.getX(),location.getY(),36,20);
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
	public boolean collidesWith(Wall w){
		return w.getHitbox().intersects(hitbox) || w.getHitbox().contains(hitbox);
	}
}
