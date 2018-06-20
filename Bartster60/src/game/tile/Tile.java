package game.tile;

import java.awt.image.BufferedImage;

import game.util.ImageLoader;
import game.util.math.Area2D;

public class Tile {
	
	protected String name;
	protected BufferedImage texture;
	protected boolean isSolid;
	protected Area2D AABB = new Area2D(0,0,1,1); //AxisAlignedBoundingBox
	
	
	public Tile() {}
	
	public Tile(String name) {
		init(name, true);
	}
	
	public Tile(String name, boolean isSolid) {
		init(name, isSolid);
	}
	
	protected void init(String name, boolean isSolid) {
		this.name=name;
		this.texture = ImageLoader.load("/resource/assets/textures/tiles/" + name + ".png");
		this.isSolid=isSolid;
	}
	
	public void tick() {}
	
	
	//SETTERS
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}
	
	//GETTERS
	public String getName() {
		return name;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public Area2D getAABB() {
		return AABB;
	}
	
	public Area2D getCollisionBounds() {
		return new Area2D(getAABB().getX(), getAABB().getY(), getAABB().getWidth(), getAABB().getHeight());
	}
}
