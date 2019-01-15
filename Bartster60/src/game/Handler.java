package game;

import game.gfx.Camera;
import game.gfx.Screen;
import game.util.InputHandler;
import game.util.MouseHandler;
import game.util.WorldLoader;
import game.world.World;

/* 
 * |============================================================|
 * |this Class stores Objects and grants access from every class|
 * |============================================================|
 */

public class Handler {
	private Screen screen;
	private World world;
	private InputHandler input;
	private MouseHandler mouse;
	private Camera camera;
	
	private int lvlcounter=0;
	
	public void init() {
		this.screen = new Screen();
		resetLevel();
		this.input = new InputHandler(screen);
		this.mouse = new MouseHandler(screen);
		this.camera = new Camera(0, 0);
	}
	
	
	public void reloadWorld() {
		String path = world.getName();
		loadWorld(path);
	}
	
	public void loadWorld(String path) {
		world = WorldLoader.load(path);
	}
	
	public void resetLevel() {
		 lvlcounter=0;
		 loadNextLevel();
	}
	
	public void loadNextLevel() {
		String path;
		
		switch(lvlcounter) {
		case 0: path="map_level_0";
		break;
		case 1: path="map_level_1";
		break;
		default: resetLevel();
		return;
		}
		
		world = WorldLoader.load(path);
		lvlcounter++;
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
	
	public MouseHandler getMouse() {
		return mouse;
	}
}
