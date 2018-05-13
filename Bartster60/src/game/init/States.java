package game.init;

import game.state.State;
import game.state.StateGame;

/* 
 * |=========================================|
 * |this Class is preinitializing every State|
 * |=========================================|
 */

public class States {
	public static State game;
	
	public static void init() {
		game = new StateGame();
	}
}
