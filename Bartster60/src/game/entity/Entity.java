package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.Game;
import game.util.math.Area2D;
import game.util.math.Pos2D;

public abstract class Entity {

	//DEFAULTS
	protected static final int DEFAULT_HEALTH = 2;
	
	//Attributes
	protected boolean isDead;
	protected boolean isAirborn;
	protected int health = DEFAULT_HEALTH;
	protected BufferedImage texture;
	protected Pos2D pos = new Pos2D();
	protected Area2D AABB = new Area2D();
	
	protected Random rand = new Random();
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public boolean isEntityCollision(double xOffset, double yOffset) {
		for (Entity e : Game.getHandler().getWorld().getEntities().getList()) {
			if (e.equals(this)) {
				continue;
			}
			if (getCollisionBounds(xOffset, yOffset).intersects(e.getCollisionBounds(0, 0))) {
				return true;
			}
		}
		return false;
	}
	
	//SETTER
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public void setPos(double x, double y) {
		this.pos.set(x, y);
	}
	
	public void setPos(Pos2D newPos) {
		setPos(newPos.getX(), newPos.getY());
	}

	public void setAABB(Area2D AABB) {
		this.AABB = AABB;
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
	
	public Area2D getAABB() {
		return AABB;
	}
	
	public Area2D getCollisionBounds(double xOffset, double yOffset) {
		return new Area2D((pos.getX() + getAABB().getX()) + xOffset, (pos.getY() + getAABB().getY()) + yOffset, getAABB().getWidth(), getAABB().getHeight());
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public boolean isAirborn() {
		return this.isAirborn;
	}
}
