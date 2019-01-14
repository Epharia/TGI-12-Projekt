package game.entity.misc;

import java.awt.Graphics;

import game.Game;
import game.entity.Entity;
import game.init.States;
import game.state.State;
import game.util.ImageLoader;

public class EntityGoal extends Entity {
	
	public EntityGoal(double posX, double posY) {
		super();
		this.pos.set(posX, posY);
		this.AABB.setBounds(0.125, 0.6875, 0.75, 0.3125);
		this.texture=ImageLoader.load("/resource/assets/textures/misc/key.png");
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
	}

	@Override
	public void tick() {
		isEntityCollision();
	}
	
	@Override
	protected void onEntityCollision(Entity e) {
		State.setState(States.won);
	}
}