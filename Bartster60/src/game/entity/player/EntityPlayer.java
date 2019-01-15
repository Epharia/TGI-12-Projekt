package game.entity.player;

import java.awt.Graphics;

import game.Game;
import game.entity.Entity;
import game.entity.EntityLiving;
import game.gfx.animation.Animation;
import game.init.States;
import game.state.State;
import game.util.ImageLoader;

public class EntityPlayer extends EntityLiving {
	
	private int cooldown=0;
	
	public EntityPlayer(int spawnX, int spawnY) {
		this.maxHealth=5;
		this.currentHealth=maxHealth;
		this.pos.set(spawnX, spawnY);
		this.AABB.setBounds(0.3125, 0, 0.37, 0.99);
		this.animation = new Animation(150, ImageLoader.loadAnimationSheet("/resource/assets/textures/creatures/player.png", 4, 16, 16));
		facing = Facing.WEST;
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
		
	}
	
	@Override
	public void tick() {
		if(cooldown>0)
			cooldown--;
		super.tick();
	}
	
	@Override
	protected void onEntityCollision(Entity e) {
//		if(e instanceof EntityProjectile) //Old
//			return;
		
		EntityLiving entity;
		if(e instanceof EntityLiving)
			entity = (EntityLiving) e;
		else return;
			
		if(cooldown<=0) {
			damage(entity.getDamage()); //Damage player
			cooldown =(int) (Game.TPS * 0.5); //half second cooldown before getting damage by enemies again
		}
	}
	@Override
	public void onDamage() {
		this.momentumX = (facing == Facing.EAST ? 2 : -2); //Knockback
		this.momentumY = -2;
	}
	
	@Override
	public void onDeath() {
		State.setState(States.dead);
	}
}