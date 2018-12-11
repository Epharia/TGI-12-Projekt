package game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import game.entity.Entity;
import game.entity.player.EntityPlayer;

/* 
 * |==============================================|
 * |this Class stores all Entities and manage them|
 * |==============================================|
 */

public class EntityManager {
	
	protected ArrayList<Entity> entities;
	protected EntityPlayer player;
	
	public EntityManager(EntityPlayer player) {
		this.player = player;
		entities = new ArrayList<Entity>();
		entities.add(player);
	}
	
	//Render all Entities
	public void render(Graphics g) {
		for(Entity entity : entities) {
			entity.render(g);
		}
	}
	
	//Update all Entities
	public void tick() {
		for(Entity entity : entities) {
			entity.tick();
		}
	}
	
	//SETTER
	public void add(Entity e) {
		this.entities.add(e);
	}
	
	public void remove(int index) {
		entities.remove(index);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public void clear() {
		entities.clear();
	}

	//GETTER
	public Entity get(int i) {
		return entities.get(i);
	}
	
	public ArrayList<Entity> getList() {
		return entities;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
}
