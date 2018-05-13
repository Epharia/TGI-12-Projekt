package game.state;

import java.awt.Graphics;

/* 
 * |=============================================================|
 * |this Class is used to define different States in the Game and| 
 * |what the Program shall do while it is in one of those States |
 * |=============================================================|
 */

public abstract class State {
	
	private static State currentState;
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	//SETTER
	public static void setState(State stateIn) {
		currentState=stateIn;
	}
	
	//GETTER
	public static State getCurrentState() {
		return currentState;
	}
}
