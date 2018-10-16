package game.gfx.gui;

import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.util.math.Area2D;

public abstract class GUIObjBase { //TODO Add activation by Keyboard
	
	protected Area2D bounds;
	protected Handler handler;
	
	public GUIObjBase(int x, int y, int width, int height) {
		bounds = new Area2D(x, y, width, height);
		this.handler = Game.getHandler();
	}
	
	public boolean isHovering() {
		return bounds.contains(handler.getMouse().getPosMouseX(), handler.getMouse().getPosMouseY());
	}
	
	public void checkInputs() {
		checkMouseLeft();
		checkMouseRight();
	}
	
	public void checkMouseLeft() {
		if (isHovering() && handler.getMouse().isLeftPressed())
			onClick();
	}
	
	public void checkMouseRight() {
		if (isHovering() && handler.getMouse().isRightPressed())
			onClick();
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void onClick();
}
