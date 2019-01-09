package game.entity.ai;

import game.entity.EntityLiving;

public class AIFollowPlayer extends AIBase { //TODO AI: follow and attack player
	
	protected final EntityLiving entity;

    public AIFollowPlayer(EntityLiving creatureIn) {
    	entity = creatureIn;
    }
    
	@Override
	public boolean shouldExecute() {
		return false;
	}
	
	@Override
	public void startExecuting() {
		System.out.println(entity.toString() + " Started task: 'FollowPlayer'");
	}
	
	@Override
	public boolean continueExecuting() {
		return false;
	}
	
	@Override
	public void tick() {
	}
}
