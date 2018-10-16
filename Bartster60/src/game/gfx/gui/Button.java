package game.gfx.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.util.ImageLoader;

public class Button extends GUIObjBase {
	
	private BufferedImage[] imgArray = new BufferedImage[2];
	TextLabel text;
	
	public Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.text=new TextLabel(x, y, width, height, text, 75);
		this.text.setCentered(true);
		this.text.deriveFont(Font.BOLD);
		imgArray[0]=ImageLoader.loadSub("/resource/assets/textures/gui/button.png", 0, 0, 64, 16);
		imgArray[1]=ImageLoader.loadSub("/resource/assets/textures/gui/button.png", 0, 16, 64, 16);
		
	}

	@Override
	public void render(Graphics g) {
		if (isHovering()) {
			g.drawImage(imgArray[1], (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight(), null);
		} else {
			g.drawImage(imgArray[0], (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight(), null);
		}
		text.render(g);
	}

	@Override
	public void tick() {
	}

	@Override
	public void onClick() {}

	//GETTER
	public String getText() {
		return text.getText();
	}

	//SETTER
	public void setText(String text) {
		this.text.setText(text);
	}
	
}
