package game.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.entity.aggressive.EntityDog;
import game.init.Tiles;
import game.tile.Tile;
import game.util.math.Area2D;

public class World {
	
	public final int WIDTH, HEIGHT;
	public final double GRAVITATION;
	
	protected BufferedImage background;
	protected Tile[][] world_tiles0; //LAYER 0
	protected Tile[][] world_tiles1; //LAYER 1 (Overlay)
	
	protected EntityManager entities;
	
	public World(int width, int height, double gravitation) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.GRAVITATION = gravitation;
		world_tiles0 = new Tile[width][height];
		world_tiles1 = new Tile[width][height];
		
		entities = new EntityManager();
		entities.add(new EntityDog());
		
		setTiles();
	}
	
	private void setTiles() { //TEMP
		this.background = new BufferedImage(Game.getHandler().getScreen().getWidth(), Game.getHandler().getScreen().getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i=0; i<Game.getHandler().getScreen().getWidth(); i++) {
			for(int j=0; j<Game.getHandler().getScreen().getHeight(); j++) {
				background.setRGB(i, j, new Color(0,150,190).getRGB());
			}
		}
		
		for(int i=0; i<HEIGHT; i++) {
			for(int j=0; j<WIDTH; j++) {
				world_tiles0[j][i] = Tiles.dirt;
			}
		}
		
		for(int i=0; i<WIDTH; i++) {
			if(i==10) continue;
			world_tiles1[i][15] = Tiles.grass;
			world_tiles1[i][14] = Tiles.grass_2;
		}
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<WIDTH; j++) {
				world_tiles0[j][i] = Tiles.air;
			}
		}
		
		world_tiles0[10][14] = Tiles.dirt;
		world_tiles1[10][14] = Tiles.grass;
		world_tiles1[10][13] = Tiles.grass_2;
	}
	
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, null);
		for(int i=0; i<HEIGHT; i++) {
			for(int j=0; j<WIDTH; j++) {
				if(world_tiles0[j][i]!=null)
					g.drawImage(world_tiles0[j][i].getTexture(), (int) (j*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (i*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
				if(world_tiles1[j][i]!=null)
					g.drawImage(world_tiles1[j][i].getTexture(), (int) (j*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (i*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
			}
		}
		entities.render(g);
	}

	public void tick() {
		entities.tick();
		Game.getHandler().getCamera().centerOnEntity(entities.getPlayer());
		Game.getHandler().getCamera().checkLimit();
	}
	
	public Tile getTileAt(int x, int y) {
		if(x>=0 && x<WIDTH && y>=0 && y<HEIGHT)
			return world_tiles0[x][y];
		else {
			System.out.println("ERROR@getTileAt(int x, int y)\n=>return 'Tiles.air'");
			return Tiles.air;
		}
	}
	
	public Area2D getCollisionFromTileAt(int x, int y) {
			Tile tile = world_tiles0[x][y];
			return new Area2D(tile.getAABB().getX() + x, tile.getAABB().getY() + y, tile.getAABB().getWidth(), tile.getAABB().getHeight());
	}

	public EntityManager getEntities() {
		return entities;
	}

	public Tile[][] getMap() {
		return world_tiles0;
	}
	
	public Tile[] getMap1D() {
		Tile[] tiles = new Tile[HEIGHT*WIDTH];
		for(int y=0; y<HEIGHT; y++) {
			for(int x=0; x<WIDTH; x++) {
				tiles[x*HEIGHT+y] = world_tiles0[x][y];
			}
		}
		return tiles;
	}
}