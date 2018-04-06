package game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import game.gfx.Screen;

public class InputHandler implements KeyListener {
  
	public InputHandler(Screen screen) {
	    screen.addKeyListener(this);
	}
  
	public class Key {
	private int numTimesPressed = 0;
    private boolean pressed = false;
    
    public boolean isPressed() {
    	return pressed;
    }
    
    public int getNumTimesPressed () {
    	return numTimesPressed;
    }

    public void toogle(boolean isPressed) {
    	pressed = isPressed;
      	if (pressed) numTimesPressed++;
    }
}
  
	public List<Key> keys = new ArrayList<Key>();
  
	public Key left = new Key();
	public Key right = new Key();
	public Key jump = new Key();
	public Key run = new Key();
  
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent e) {
	}
  
	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
			left.toogle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
			right.toogle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			jump.toogle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			run.toogle(isPressed);
		}
	}
}
