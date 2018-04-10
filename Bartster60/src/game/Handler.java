package game;

import game.gfx.Camera;
import game.gfx.Screen;
import game.util.InputHandler;
import game.world.World;

public class Handler {
	private Screen screen;
	private World world;
	private InputHandler input;
	private Camera camera;
	
	public void init() {
		this.screen = new Screen();
		this.world = new World(30, 20, 10);
		this.input = new InputHandler(screen);
		this.camera = new Camera(0, 0);
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
	
	public Camera getCamera() {
		return camera;
	}
}
