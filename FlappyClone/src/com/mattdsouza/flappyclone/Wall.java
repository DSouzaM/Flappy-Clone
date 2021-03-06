package com.mattdsouza.flappyclone;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import java.util.Random;

public class Wall extends Entity {
	public Shape hitbox;
	public static final int SPRITE_WIDTH = 40;
	public static final int SPRITE_HEIGHT = 600; 

	public static Random random = new Random();
	public Wall(String imgName){
		super(imgName, SPRITE_WIDTH, SPRITE_HEIGHT,17);
		hitbox = new Rectangle(location.getX()+10,location.getY(),10,SPRITE_HEIGHT);
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
		hitbox.setX(location.getX()+10);
		hitbox.setY(location.getY());
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
}
