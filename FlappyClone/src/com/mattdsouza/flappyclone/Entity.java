package com.mattdsouza.flappyclone;

import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Entity {
	protected Point location;
	protected Vector2f vector;
	protected boolean visible;
	protected Image image;
	protected float vx, vy;

	public Entity(String str) {
		try {
			setImage(str);
		} catch (SlickException se) {
			se.printStackTrace();
		}
		location = new Point();
		vector = new Vector2f();
		vx = 0;
		vy = 0;
	}

	public void setLocation(int x, int y) {
		location.setX(x);
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

	public void update() {
		location.setX(Math.round(location.getX() + vector.getX()));
		location.setY(Math.round(location.getY() + vector.getY()));
	}

	public void setImage(String path) throws SlickException {
		image = new Image("res/" + path + ".png");
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

	public Image getImage() {
		return image;
	}

	public int getImageWidth() {
		return image.getWidth();
	}

	public int getImageHeight() {
		return image.getHeight();
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
