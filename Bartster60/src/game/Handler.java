package game;

import game.gfx.Camera;
import game.gfx.Screen;
import game.util.InputHandler;
import game.util.MouseHandler;
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
	
	public void init() {
		this.screen = new Screen();
		this.world = new World(30, 20, 7);
		this.input = new InputHandler(screen);
		this.mouse = new MouseHandler(screen);
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
	
	public MouseHandler getMouse() {
		return mouse;
	}
}
