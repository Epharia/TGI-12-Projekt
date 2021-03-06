package game.state;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.gfx.gui.GUIHandler;
import game.gfx.gui.TextLabel;
import game.init.States;

public class StateDeathScreen extends State {
	
	private GUIHandler gui;

	private Handler handler = Game.getHandler();
	
	private long timer=0;
	private TextLabel label = new TextLabel(handler.getScreen().getMidX()-256, (int) (handler.getScreen().getMidY()-64), 512, 128, "GAME OVER!", Color.YELLOW, 64);
	
	public StateDeathScreen() {
		super();
		
		gui = new GUIHandler();
		
		label.setCentered(true);
		gui.add(label);
	}
	
	@Override
	public void render(Graphics g) {
		g.clearRect(0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight());
		g.setColor(new Color(0, 0, 0, 200));
		Game.getHandler().getWorld().render(g);
		g.fillRect(0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight());
		
		gui.render(g);
	}

	@Override
	public void tick() {
		gui.tick();
		
		timer++;
		
		if(timer%2==0 && !(label.getBounds().getY()==handler.getScreen().getMidY()-192))
			label.setPos((int) (label.getBounds().getX()), (int) (label.getBounds().getY()-1));
		
		if(timer<Game.TPS*3)
			return;
		else timer = 0;
		
		
		label.setPos(handler.getScreen().getMidX()-256, (int) (handler.getScreen().getMidY()-64));
		
		Game.getHandler().getScreen().showCursor();
		Game.getHandler().resetLevel();
		State.setState(States.menu);
	}
}
