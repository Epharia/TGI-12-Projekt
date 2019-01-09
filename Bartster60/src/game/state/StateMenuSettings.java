package game.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.Handler;
import game.gfx.gui.Button;
import game.gfx.gui.GUIHandler;
import game.gfx.gui.TextLabel;
import game.init.States;
import game.util.ImageLoader;

public class StateMenuSettings extends State {
	
	private GUIHandler gui;
	private BufferedImage background;
	private Handler handler = Game.getHandler();
	
	private TextLabel label = new TextLabel(10, 10, handler.getScreen().getWidth()-20, 64, "Work in Progress", Color.WHITE, 64);
	private long lastTimer=System.currentTimeMillis();
	private long timer=0;
	private boolean b;
	
	public StateMenuSettings() {
		super();
		
		this.background=ImageLoader.load("/resource/assets/textures/gui/background.png");
		
		gui = new GUIHandler();
		
		gui.add(label);
		
		gui.add(new Button(handler.getScreen().getMidX()-256, handler.getScreen().getMidY()-202, 512, 128, "Back") {
			@Override
			public void onClick() {
				State.setState(States.menu);
			}
		});
	}
	
	@Override
	public void render(Graphics g) {
		g.clearRect(0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight());
		g.drawImage(background, 0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight(), null);
		gui.render(g);
	}

	@Override
	public void tick() {
		gui.tick();
		
		long now = System.currentTimeMillis();
		timer+=now-lastTimer;
		lastTimer=now;
		
		if(timer<500)
			return;
		else timer = 0;
		
		if(!b) {
			label.setColor(Color.RED);
			b=true;
		} else {
			label.setColor(Color.WHITE);
			b=false;
		}
	}
}
