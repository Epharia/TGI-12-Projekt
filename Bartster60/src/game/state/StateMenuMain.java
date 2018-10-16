package game.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.Handler;
import game.gfx.gui.Button;
import game.gfx.gui.GUIHandler;
import game.init.States;
import game.util.ImageLoader;

public class StateMenuMain extends State {
	
	private GUIHandler gui;
	private BufferedImage background;
	private Handler handler = Game.getHandler();
	
	public StateMenuMain() {
		super();
		
		this.background=ImageLoader.load("/resource/assets/textures/gui/background.png");
		
		gui = new GUIHandler();
		
		gui.add(new Button(handler.getScreen().getMidX()-256, handler.getScreen().getMidY()-202, 512, 128, "Start") {
			@Override
			public void onClick() {
				State.setState(States.game);
				handler.getScreen().hideCursor();
			}
		});
		
		gui.add(new Button(handler.getScreen().getMidX()-256, handler.getScreen().getMidY()-64, 512, 128, "Options") {
			@Override
			public void onClick() {
			}
		});
		
		gui.add(new Button(handler.getScreen().getMidX()-256, handler.getScreen().getMidY()+74, 512, 128, "Exit") {
			@Override
			public void onClick() {
				System.exit(0);
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
	}
}
