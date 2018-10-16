package game.gfx.gui;

import java.awt.Graphics;
import java.util.ArrayList;

public class GUIHandler { 
	
	private ArrayList<GUIObjBase> guiObjects;
	
	public GUIHandler() {
		this.guiObjects = new ArrayList<GUIObjBase>();
	}
	
	public void tick() {
		for(GUIObjBase guiObj : guiObjects) {
			guiObj.tick();
			guiObj.checkInputs();
		}
	}
	
	public void render(Graphics g) {
		for(GUIObjBase guiObj : guiObjects)
			guiObj.render(g);
	}
	
	public void add(GUIObjBase guiObj) {
		guiObjects.add(guiObj);
	}
	
	public void removeAt(int index) {
		guiObjects.remove(index);
	}
	
	public void remove(GUIObjBase guiObj) {
		guiObjects.remove(guiObj);
	}
	
	public void clear() {
		guiObjects.clear();
	}
}
