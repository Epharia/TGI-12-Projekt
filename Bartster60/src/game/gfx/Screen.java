package game.gfx;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Screen extends Canvas {
	
	//Frame
	private JFrame frame;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private static final String NAME = "TestGame";
	private static final String VERSION = "Alpha V0.0.1";
	
	public Screen() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(NAME + " @" + VERSION);
		
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