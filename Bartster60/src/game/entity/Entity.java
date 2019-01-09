package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.Game;
import game.util.math.Area2D;
import game.util.math.Pos2D;
import game.world.World;

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
	
	private Random rand = new Random();
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public boolean isEntityCollision() {
		for (Entity e : Game.getHandler().getWorld().getEntities().getList()) {
			if (e.equals(this)) {
				continue;
			}
			if (getCollisionBounds().intersects(e.getCollisionBounds())) {
				onEntityCollision(e);
				return true;
			}
		}
		return false;
	}
	
	//called when Entity Collision is detected
	protected void onEntityCollision(Entity e) {}
	
	protected boolean isTileCollision() {
		World w = Game.getHandler().getWorld();
		
		int x1 = (int) (getPosX()+getAABB().getX()-1); //LEFT
		int y1 = (int) (getPosY()+getAABB().getY()-1); //RIGHT
		
		int x2 = (int) (x1 + getAABB().getWidth()+2); //TOP
		int y2 = (int) (y1 + getAABB().getHeight()+2); //BOTTOM
		
		if(x1<0)
			x1=0;
		if(x2>w.WIDTH-1)
			x2=w.WIDTH-1;
		
		if(y1<0)
			y1=0;
		if(y2>w.HEIGHT-1)
			y2=w.HEIGHT-1;
		
		for(int y=y1; y<=y2; y++)
			for(int x=x1; x<=x2; x++) {
				if(w.getTileAt(x, y).isSolid() && getCollisionBounds().intersects(w.getCollisionFromTileAt(x, y))) {
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
	
	public Area2D getCollisionBounds() {
		return new Area2D((pos.getX() + getAABB().getX()), (pos.getY() + getAABB().getY()), getAABB().getWidth(), getAABB().getHeight());
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public boolean isAirborn() {
		return this.isAirborn;
	}
	
	public Random getRNG() {
		return rand;
	}
}
