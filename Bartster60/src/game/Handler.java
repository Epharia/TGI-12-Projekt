package game;

import game.gfx.Screen;

public class Handler {
	private Screen screen;
	
	public void init() {
		this.screen = new Screen();
	}
	
	
	//GETTER
	public Screen getScreen() {
		return screen;
	}
}
