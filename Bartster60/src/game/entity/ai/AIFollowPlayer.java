package game.entity.ai;

import game.Game;
import game.entity.EntityLiving;
import game.entity.EntityLiving.Facing;
import game.entity.player.EntityPlayer;
import game.util.math.Area2D;

public class AIFollowPlayer extends AIBase { //TODO AI: follow and attack player
	
	protected final EntityLiving entity;
	private Area2D aov; //Area of view
	private boolean shouldSlowDown;

    public AIFollowPlayer(EntityLiving creatureIn) {
    	entity = creatureIn;
    	aov = new Area2D(-6, -6, 12+entity.getAABB().getWidth()+entity.getAABB().getX(), 12+entity.getAABB().getHeight()+entity.getAABB().getY());
    }
    
	@Override
	public boolean shouldExecute() {
		return getFOVBounds().intersects(Game.getHandler().getWorld().getEntities().getPlayer().getCollisionBounds());
	}
	
	@Override
	public void startExecuting() {
		System.out.println(entity.toString() + " Started task: 'FollowPlayer'");
	}
	
	@Override
	public boolean continueExecuting() {
		if(getFOVBounds().intersects(Game.getHandler().getWorld().getEntities().getPlayer().getCollisionBounds())) {
			shouldSlowDown = false;
			return true;
		} else if(entity.getCurrentMomentum()!=0) {
			shouldSlowDown = true;
			return true;
		}
		
		System.out.println(entity.toString() + " Ended task: 'FollowPlayer'");
		return false;
	}
	
	@Override
	public void tick() {
		if(shouldSlowDown) {
			entity.slowDown();
			return;
		}
		
		EntityPlayer player = Game.getHandler().getWorld().getEntities().getPlayer();
		if (entity.getCollisionBounds().getX()+entity.getCollisionBounds().getWidth()<player.getCollisionBounds().getX()) {
			entity.accelerate(0.05);
		}
		
		if (entity.getCollisionBounds().getX()>player.getCollisionBounds().getX()+player.getCollisionBounds().getWidth()) {
			entity.accelerate(-0.05);
		}
		
		float offset = (entity.getFacing() == Facing.EAST) ? -0.25F : 1.25F;
		if(Game.getHandler().getWorld().getTileAt((int) (entity.getPosX()+offset), (int)entity.getPosY()).isSolid()) {
			entity.jump();
		}
	}
	
	private Area2D getFOVBounds() {
		return new Area2D((entity.getPos().getX() + aov.getX()), (entity.getPos().getY() + aov.getY()), aov.getWidth(), aov.getHeight());
	}
}
