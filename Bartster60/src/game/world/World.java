package game.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.init.Tiles;
import game.tile.Tile;

public class World {
	
	protected final int width, height;
	
	protected BufferedImage background;
	protected Tile[][] world_tiles;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		world_tiles = new Tile[width][height];
		
		setTiles();
	}
	
	private void setTiles() { //TEMP
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				world_tiles[j][i] = Tiles.dirt;
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				g.drawImage(world_tiles[j][i].getTexture(), j*32, i*32, 32, 32, null);
			}
		}
	}

	public void tick() {}
	
	public Tile getTileAt(int x, int y) {
		return world_tiles[x][y];
	}
	
}