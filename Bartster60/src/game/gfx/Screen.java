package game.gfx;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import game.Game;

@SuppressWarnings("serial")
public class Screen extends Canvas {
	
	//Frame
	private JFrame frame;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	public Screen() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(Game.NAME + " @" + Game.VERSION);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}