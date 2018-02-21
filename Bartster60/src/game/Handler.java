package game;

import game.gfx.Screen;
import game.world.World;

public class Handler {
	private Screen screen;
	private World world;
	
	public void init() {
		this.screen = new Screen();
		this.world = new World(30, 20);
	}
	
	
	//GETTER
	public Screen getScreen() {
		return screen;
	}


	public World getWorld() {
		return world;
	}
}
