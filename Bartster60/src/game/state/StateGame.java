package game.state;

import java.awt.Graphics;

import game.Game;

public class StateGame extends State{

	@Override
	public void render(Graphics g) {
		Game.getHandler().getWorld().render(g);
	}

	@Override
	public void tick() {
		
	}

}
