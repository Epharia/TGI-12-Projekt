package game.init;

import game.state.State;
import game.state.StateGame;
import game.state.StateMenuMain;

/* 
 * |=========================================|
 * |this Class is preinitializing every State|
 * |=========================================|
 */

public class States {
	public static State game;
	public static State menu;
	
	public static void init() {
		game = new StateGame();
		menu = new StateMenuMain();
	}
}
