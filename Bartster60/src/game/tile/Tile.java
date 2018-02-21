package game.tile;

import java.awt.image.BufferedImage;

import game.util.ImageLoader;

public class Tile {
	
	protected String name;
	protected BufferedImage texture;
	protected boolean isSolid;
	
	
	public Tile() {}
	
	public Tile(String name) {
		init(name, true);
	}
	
	public Tile(String name, boolean isSolid) {
		init(name, isSolid);
	}
	
	protected void init(String name, boolean isSolid) {
		this.name=name;
		this.texture = ImageLoader.load("/assets/textures/tiles/" + name + ".png");
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
	
}
