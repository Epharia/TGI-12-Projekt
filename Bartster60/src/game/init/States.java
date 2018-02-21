package game.init;

import game.state.State;
import game.state.StateGame;

public class States {
	public static State game;
	
	public static void init() {
		game = new StateGame();
	}
}
