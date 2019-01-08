package game.gfx;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import game.Game;
import game.util.ImageLoader;

@SuppressWarnings("serial")
public class Screen extends Canvas {
	
	//Frame
	private JFrame frame;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private BufferedImage cursor;
	
	public Screen() {
		cursor = ImageLoader.load("/resource/assets/textures/gui/cursor.png");
		
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(Game.NAME + " @" + Game.VERSION);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setCursor(frame.getToolkit().createCustomCursor(cursor, new Point(), null));
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	//GETTER
	public int getWidth() {
		return frame.getContentPane().getWidth();
	}
	
	public int getHeight() {
		return frame.getContentPane().getHeight();
	}
	
	public int getMidX() {
		return frame.getContentPane().getWidth()/2;
	}
	
	public int getMidY() {
		return frame.getContentPane().getHeight()/2;
	}
	
	//SETTER
	public void hideCursor() {
		frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
	}
	
	public void showCursor() {
		frame.setCursor(frame.getToolkit().createCustomCursor(cursor, new Point(), null));
	}
}