package game.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.entity.Entity;
import game.entity.player.EntityPlayer;
import game.init.Tiles;
import game.tile.Tile;
import game.util.math.Area2D;

public class World {
	
	//Constants
	public final int WIDTH, HEIGHT, SPAWNX, SPAWNY;
	public final double GRAVITATION, MAX_G_SPEED; //TEST MAX_G_SPEED
	
	//Attributes
	protected BufferedImage background;
	protected Tile[][] world_tiles;
	
	protected EntityManager entities;
	
	public World(int width, int height, int spawnX, int spawnY, double gravitation) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SPAWNX = spawnX;
		this.SPAWNY = spawnY;
		this.GRAVITATION = gravitation;
		this.MAX_G_SPEED = 0;
		world_tiles = new Tile[width][height];
		
		entities = new EntityManager(new EntityPlayer(SPAWNX, SPAWNY));
		
		this.background = new BufferedImage(Game.getHandler().getScreen().getWidth(), Game.getHandler().getScreen().getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i=0; i<Game.getHandler().getScreen().getWidth(); i++) {
			for(int j=0; j<Game.getHandler().getScreen().getHeight(); j++) {
				background.setRGB(i, j, new Color(0,150,190).getRGB());
			}
		}
	}
	
	//Rendering the World (Tiles & Entities)
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, null);
		
		//Demonstration
//		int xMin=(int) Math.max(0, Game.getHandler().getCamera().getxOffset()/Game.TILESCALE+1);
//		int xMax=(int) Math.min(WIDTH, (Game.getHandler().getCamera().getxOffset()+Game.getHandler().getScreen().getWidth())/Game.TILESCALE);
//		int yMin=(int) Math.max(0, Game.getHandler().getCamera().getyOffset()/Game.TILESCALE+1);
//		int yMax=(int) Math.min(HEIGHT, (Game.getHandler().getCamera().getyOffset()+Game.getHandler().getScreen().getHeight())/Game.TILESCALE);
		
		int xMin=(int) Math.max(0, Game.getHandler().getCamera().getxOffset()/Game.TILESCALE);
		int xMax=(int) Math.min(WIDTH, (Game.getHandler().getCamera().getxOffset()+Game.getHandler().getScreen().getWidth())/Game.TILESCALE+1);
		int yMin=(int) Math.max(0, Game.getHandler().getCamera().getyOffset()/Game.TILESCALE);
		int yMax=(int) Math.min(HEIGHT, (Game.getHandler().getCamera().getyOffset()+Game.getHandler().getScreen().getHeight())/Game.TILESCALE+1);
		
		for(int i=yMin; i<yMax; i++) {
			for(int j=xMin; j<xMax; j++) {
				if(world_tiles[j][i]!=null)
					g.drawImage(world_tiles[j][i].getTexture(), (int) (j*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (i*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
			}
		}
		entities.render(g);
	}
	
	//sets the cameras focus to the Player on every tick and calls the entities tick method
	public void tick() {
		entities.tick();
		Game.getHandler().getCamera().centerOnEntity(entities.getPlayer());
		Game.getHandler().getCamera().checkLimit();
	}
	
	//SETTER
	public void setMap(Tile[][] map) {
		this.world_tiles=map;
	}
	
	//GETTER
	public Tile getTileAt(int x, int y) { //Returns the Tile at Index [x][y]
		if(x>=0 && x<WIDTH && y>=0 && y<HEIGHT)
			return world_tiles[x][y];
		else {
			System.out.println("ERROR@getTileAt(int x, int y)\n=>return 'Tiles.air'");
			return Tiles.air;
		}
	}
	
	public Area2D getCollisionFromTileAt(int x, int y) {//Returns the Tiles CollisionBounds at Index [x][y]
			Tile tile = world_tiles[x][y];
			return new Area2D(tile.getAABB().getX() + x, tile.getAABB().getY() + y, tile.getAABB().getWidth(), tile.getAABB().getHeight());
	}

	public EntityManager getEntities() {
		return entities;
	}

	public Tile[][] getMap() {
		return world_tiles;
	}
	
	public Tile[] getMap1D() { //Returns an one Dimensional Array Map
		Tile[] tiles = new Tile[HEIGHT*WIDTH];
		for(int y=0; y<HEIGHT; y++) {
			for(int x=0; x<WIDTH; x++) {
				tiles[x*HEIGHT+y] = world_tiles[x][y];
			}
		}
		return tiles;
	}

	public void setEntities(ArrayList<Entity> eIn) {
		for(Entity e : eIn) 
			this.entities.add(e);;
	}
	
	public void addEntity(Entity e) { 
			this.entities.add(e);;
	}
}