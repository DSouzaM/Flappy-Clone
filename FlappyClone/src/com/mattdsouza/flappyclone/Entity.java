package com.mattdsouza.flappyclone;

import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

public class Entity {
	protected Point location;
	protected Vector2f vector;
	protected boolean visible;
	protected SpriteSheet sheet;
	protected Image image;
	protected float vx, vy;
	protected int spriteWidth, spriteHeight;
	protected int spriteState;


	public Entity(String imgName, int spriteWidth, int spriteHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		try {
			setSpriteSheet(imgName);
			setImage(0,0);
			image = sheet.getSprite(0, 0);
		} catch (SlickException se) {
			se.printStackTrace();
		}
		location = new Point();
		vector = new Vector2f();
		vx = 0;
		vy = 0;
		spriteState = 0;
	}
	
	public void update() {
		location.setX(Math.round(location.getX() + vector.getX()));
		location.setY(Math.round(location.getY() + vector.getY()));
	}

	public void setLocation(int x, int y) {
		location.setX(x);
		location.setY(y);
	}
	public void setX(int x) {
		location.setX(x);
	}
	public void setY(int y){
		location.setY(y);
	}

	public void setVelocity(float vx, float vy) {
		this.vx = vy;
		this.vy = vy;
		vector.setX(this.vx);
		vector.setY(this.vy);
	}

	public void setXVelocity(float vx) {
		this.vx = vx;
		vector.setX(this.vx);
	}

	public void setYVelocity(float vy) {
		this.vy = vy;
		vector.setY(this.vy);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setSpriteSheet(String path) throws SlickException {
		sheet = new SpriteSheet("res/" + path + ".png", getSpriteWidth(), getSpriteHeight());
	}
	
	public void setImage(int x, int y) {
		image = sheet.getSprite(x,y);
	}
	public void setRotation(int angle) {
		image.setRotation(angle);
	}
	public void nextSpriteState(){
		float rotation = this.getRotation();
		spriteState++;
		if (spriteState >= sheet.getHorizontalCount()){
			spriteState = 0;
		}
		image = sheet.getSprite(spriteState, 0);
		image.setRotation(rotation);
	}	
	public void rotate(int angle){
		image.rotate(angle);
	}	
	public int getX() {
		return location.getX();
	}
	public int getY() {
		return location.getY();
	}
	public Point getLocation() {
		return location;
	}
	public float getVX() {
		return vx;
	}
	public float getVY() {
		return vy;
	}	
	public float getRotation(){
		return image.getRotation();
	}
	public Image getImage() {
		return image;
	}
	public int getImageWidth() {
		return image.getWidth();
	}
	public int getImageHeight() {
		return image.getHeight();
	}
	public int getSpriteWidth() {
		return spriteWidth;
	}
	public int getSpriteHeight() {
		return spriteHeight;
	}
	public Point getUpperLeftPoint(){
		return location;
	}
	public Point getUpperRightPoint(){
		return new Point(location.getX()+getImageWidth(),location.getY());
	}
	public Point getLowerLeftPoint(){
		return new Point(location.getX(),location.getY()+getImageHeight());
	}
	public Point getLowerRightPoint(){
		return new Point(location.getX()+getImageWidth(),location.getY()+getImageHeight());
	}


}
