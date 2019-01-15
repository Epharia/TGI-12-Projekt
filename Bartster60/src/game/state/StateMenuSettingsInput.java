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

public class StateMenuSettingsInput extends State {
	
	private GUIHandler gui;
	private BufferedImage background;
	private Handler handler = Game.getHandler();
	
	public StateMenuSettingsInput() {
		super();
		
		this.background=ImageLoader.load("/resource/assets/textures/gui/background.png");
		
		gui = new GUIHandler();
		
		TextLabel label = new TextLabel(10, 64, handler.getScreen().getWidth()-20, 64, "How to Play", Color.WHITE, 64);
		label.setCentered(true);
		gui.add(label);
		
		TextLabel label2 = new TextLabel(10, 192, handler.getScreen().getWidth()-20, 32, "[A], [D] to Move", Color.WHITE, 32);
		label2.setCentered(true);
		gui.add(label2);
		
		TextLabel label3 = new TextLabel(10, 256, handler.getScreen().getWidth()-20, 32, "[L-Shift] to Run", Color.WHITE, 32);
		label3.setCentered(true);
		gui.add(label3);
		
		TextLabel label4 = new TextLabel(10, 320, handler.getScreen().getWidth()-20, 32, "[Space] to Jump", Color.WHITE, 32);
		label4.setCentered(true);
		gui.add(label4);
		
		Button btn = new Button(32, handler.getScreen().getHeight()-96, 256, 64, "Back") {
			@Override
			public void onClick() {
				State.setState(States.settings);
			}
		};
		btn.setTextSize(32);
		gui.add(btn);
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
