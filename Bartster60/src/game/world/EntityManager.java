package game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import game.entity.Entity;
import game.entity.player.EntityPlayer;

public class EntityManager {
	
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	protected EntityPlayer player = new EntityPlayer();
	
	public EntityManager() {
		entities.add(player);
	}
	
	public void render(Graphics g) {
		for(Entity entity : entities) {
			entity.render(g);
		}
	}
	
	public void tick() {
		for(Entity entity : entities) {
			entity.tick();
		}
	}
	
	//SETTER
	public void add(Entity e) {
		this.entities.add(e);
	}

	//GETTER
	public Entity get(int i) {
		return entities.get(i);
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
}
