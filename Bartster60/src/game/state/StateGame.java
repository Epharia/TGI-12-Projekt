package game.state;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.entity.player.EntityPlayer;
import game.gfx.gui.GUIHandler;
import game.gfx.gui.TextLabel;
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
	
	private GUIHandler gui;
	TextLabel hp;
	TextLabel level;
	
	public StateGame() {
		gui = new GUIHandler();
		
		hp = new TextLabel(10, 10, 128, 24, "0/0 HP", Color.RED, 24);
		gui.add(hp);
		
		level = new TextLabel(10, Game.getHandler().getScreen().getHeight()-34, 128, 24, ("Map: " + Game.getHandler().getWorld().getName()), new Color(255, 255, 255, 50), 24);
		gui.add(level);
	}
	
	@Override
	public void render(Graphics g) {
		Game.getHandler().getWorld().render(g);
		gui.render(g);
	}

	@Override
	public void tick() {
		Game.getHandler().getWorld().tick();
		InputGame.tick();
		EntityPlayer player = Game.getHandler().getWorld().getEntities().getPlayer();
		hp.setText((player.getHealth()+"/"+ player.getHealthMax() +"HP"));
		level.setText(("Map: " + Game.getHandler().getWorld().getName()));
		gui.tick();
	}

}
