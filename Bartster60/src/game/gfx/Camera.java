package game.gfx;

import game.Game;
import game.entity.Entity;

public class Camera {
	
	private int xOffset, yOffset;
	
	public Camera(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	//focus on Entity e
	public void centerOnEntity(Entity e) {
		xOffset = (int) (e.getPos().getX()*Game.TILESCALE - Game.getHandler().getScreen().getWidth()/2 + Game.TILESCALE/2);
		yOffset = (int) (e.getPos().getY()*Game.TILESCALE - Game.getHandler().getScreen().getHeight()/2 + Game.TILESCALE/2);
	}
	
	//Checking the Limits and letting the Camera stay at corners
	public void checkLimit() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > Game.getHandler().getWorld().WIDTH*Game.TILESCALE - Game.getHandler().getScreen().getWidth()) {
			xOffset = Game.getHandler().getWorld().WIDTH*Game.TILESCALE - Game.getHandler().getScreen().getWidth();
		}
		
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > Game.getHandler().getWorld().HEIGHT*Game.TILESCALE - Game.getHandler().getScreen().getHeight()) {
			yOffset = Game.getHandler().getWorld().HEIGHT*Game.TILESCALE - Game.getHandler().getScreen().getHeight();
		}
	}
	
	//moves the Camera by x in X direction and y in Y direction
	public void move(float x, float y) {
		this.xOffset+=x;
		this.yOffset+=y;
	}
	
	//GETTER
	public float getxOffset() {
		return xOffset;
	}
	public float getyOffset() {
		return yOffset;
	}
}
