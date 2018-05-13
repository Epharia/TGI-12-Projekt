package game.state;

import java.awt.Graphics;

import game.Game;
import game.input.InputGame;

/* 
 * |===============================|
 * |State Game					   |
 * |Renders the World			   |
 * |Updates the World			   |
 * |checking for Inputs (InputGame)|
 * |===============================|
 */

public class StateGame extends State{

	@Override
	public void render(Graphics g) {
		Game.getHandler().getWorld().render(g);
	}

	@Override
	public void tick() {
		Game.getHandler().getWorld().tick();
		InputGame.tick();
	}

}
