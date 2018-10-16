package game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.gfx.Screen;
import game.util.math.Pos2D;

public class MouseHandler implements MouseListener, MouseMotionListener {
	
	private Pos2D posMouse;
	private boolean leftPressed, rightPressed;
	
	public MouseHandler(Screen screen) {
		posMouse = new Pos2D(0, 0);
		screen.addMouseListener(this);
		screen.addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		posMouse.set(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed=true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed=true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed=false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed=false;
		}
	}
	
	
	//GETTER
	public Pos2D getPosMouse() {
		return posMouse;
	}
	
	public int getPosMouseX() {
		return (int) posMouse.getX();
	}
	
	public int getPosMouseY() {
		return (int) posMouse.getY();
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
}
