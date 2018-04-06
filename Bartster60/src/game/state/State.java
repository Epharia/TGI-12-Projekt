package game.state;

import java.awt.Graphics;

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
