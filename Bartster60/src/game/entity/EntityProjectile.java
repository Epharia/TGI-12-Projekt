package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.entity.EntityLiving.Facing;
import game.gfx.animation.Animation;

public class EntityProjectile extends Entity {
	
	private EntityLiving origin = new EntityLiving();
	
	//Facing
	protected Facing facing = Facing.EAST;
	
	//Animation
	protected Animation animation;
	
	public static final double DEFAULT_SPEED = 0.075;
	protected double speed = DEFAULT_SPEED;
	public static final int DEFAULT_DMG = 1;
	protected int dmg = DEFAULT_DMG;
	
	public EntityProjectile(EntityLiving origin) {
		this.origin=origin;
		if(origin!=null)
			facing=origin.getFacing();
	}
	
	@Override
	public void render(Graphics g) {
	}
	
	@Override
	public void tick() {
		isEntityCollision();
		pos.setX(pos.getX()+(speed*getDirection()));
		if(isTileCollision() || getPosX()<-1 || getPosX()>Game.getHandler().getWorld().WIDTH-(getAABB().getWidth()+getAABB().getX()+1))
			this.setDead(true);
		
		animation.tick();
	}
	
	private int getDirection() {
		return facing == Facing.WEST ? 1 : -1;
	}
	
	protected BufferedImage getFrame() {
		switch(facing) {
		case WEST: return animation.getFrame();
		case EAST: return animation.getFrameFliped();
		}
		return null;
	}

	@Override
	protected void onEntityCollision(Entity e) {
		if (e == origin)
			return;
		e.damage(1);
		this.setDead(true);
	}
}