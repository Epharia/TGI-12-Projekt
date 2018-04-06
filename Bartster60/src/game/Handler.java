package game;

import game.gfx.Screen;
import game.util.InputHandler;
import game.world.World;

public class Handler {
	private Screen screen;
	private World world;
	private InputHandler input;
	
	public void init() {
		this.screen = new Screen();
		this.world = new World(30, 20);
		this.input = new InputHandler(screen);
	}
	
	
	//GETTER
	public Screen getScreen() {
		return screen;
	}

	public World getWorld() {
		return world;
	}

	public InputHandler getInput() {
		return input;
	}
}
