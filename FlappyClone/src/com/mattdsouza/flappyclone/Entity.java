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

	public Entity(String str) {
		try {
			setImage(str);
		} catch (SlickException se) {
			se.printStackTrace();
		}
		location = new Point();
		vector = new Vector2f();
	}

	public void setLocation(int x, int y) {
		location.setX(x);
		location.setY(y);
	}

	public void setVelocity(float vx, float vy) {
		vector.setX(vx);
		vector.setY(vy);
	}

	public void setXVelocity(float vx) {
		vector.x = vx;
	}

	public void setYVelocity(float vy) {
		vector.y = vy;
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

	public int getVX() {
		return Math.round(vector.getX());
	}

	public int getVY() {
		return Math.round(vector.getY());
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
