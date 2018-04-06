package game.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.init.Tiles;
import game.tile.Tile;

public class World {
	
	protected final int width, height;
	
	protected BufferedImage background;
	protected Tile[][] world_tiles;
	
	protected EntityManager entities;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		world_tiles = new Tile[width][height];
		
		entities = new EntityManager();
		
		setTiles();
	}
	
	private void setTiles() { //TEMP
		this.background = new BufferedImage(Game.getHandler().getScreen().getWidth(), Game.getHandler().getScreen().getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i=0; i<Game.getHandler().getScreen().getWidth(); i++) {
			for(int j=0; j<Game.getHandler().getScreen().getHeight(); j++) {
				background.setRGB(i, j, new Color(0,150,190).getRGB());
			}
		}
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				world_tiles[j][i] = Tiles.dirt;
			}
		}
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<width; j++) {
				world_tiles[j][i] = Tiles.air;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, null);
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				g.drawImage(world_tiles[j][i].getTexture(), j*32, i*32, 32, 32, null);
			}
		}
		entities.render(g);
	}

	public void tick() {
		entities.tick();
	}
	
	public Tile getTileAt(int x, int y) {
		return world_tiles[x][y];
	}

	public EntityManager getEntities() {
		return entities;
	}
	
}