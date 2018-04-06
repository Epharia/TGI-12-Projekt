package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.util.math.Pos2D;

public abstract class Entity {

	//DEFAULTS
	protected static final int DEFAULT_HEALTH = 2; 	//TEMP test value
	
	//Attributes
	protected boolean isDead;
	protected boolean isAirborn;
	protected int health = DEFAULT_HEALTH;
	protected BufferedImage texture;
	protected Pos2D pos;
	
	protected Random rand = new Random();
	
	public Entity() {}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	//SETTER
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public void setPos(double x, double y) {
		this.pos.setPos(x, y);
	}
	
	public void setPos(Pos2D pos) {
		setPos(pos.getX(), pos.getY());
	}
	
	
	//GETTER
	public boolean isDead() {
		return this.isDead;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Pos2D getPos() {
		return this.pos;
	}
	
	public double getPosX() {
		return this.pos.getX();
	}
	
	public double getPosY() {
		return this.pos.getY();
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public boolean isAirborn() {
		return this.isAirborn;
	}
}
