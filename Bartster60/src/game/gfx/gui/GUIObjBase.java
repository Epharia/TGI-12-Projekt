package game.gfx.gui;

import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.util.math.Area2D;

public abstract class GUIObjBase { //TODO Add activation by Keyboard (at least for Buttons)
	
	protected Area2D bounds;
	protected Handler handler;
	
	private static boolean clicked; //used to prevent multiple activations
	private static boolean clickedRight;
	
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
		if (isHovering() && !clicked && handler.getMouse().isLeftPressed()) {
			onClick();
			clicked=true;
		} else if(clicked && !handler.getMouse().isLeftPressed()) {
			clicked = false;
		}
	}
	
	public void checkMouseRight() {
		if (isHovering() && !clickedRight && handler.getMouse().isRightPressed()) {
			onClickRight();
			clickedRight=true;
		} else if(clickedRight && !handler.getMouse().isRightPressed()) {
			clickedRight = false;
		}
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void onClick();
	
	public void onClickRight(){}
}
