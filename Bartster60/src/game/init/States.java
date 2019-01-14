package game.init;

import game.state.State;
import game.state.StateDeathScreen;
import game.state.StateGame;
import game.state.StateMenuMain;
import game.state.StateMenuSettings;
import game.state.StateWon;

/* 
 * |=========================================|
 * |this Class is preinitializing every State|
 * |=========================================|
 */

public class States {
	public static State game;
	public static State menu;
	public static State settings;
	public static State dead;
	public static State won;
	
	
	public static void init() {
		game = new StateGame();
		menu = new StateMenuMain();
		settings = new StateMenuSettings();
		dead = new StateDeathScreen();
		won = new StateWon();
	}	
}