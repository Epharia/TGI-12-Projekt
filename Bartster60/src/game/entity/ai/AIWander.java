package game.entity.ai;

import game.Game;
import game.entity.EntityLiving;
import game.entity.EntityLiving.Facing;
import game.util.math.Pos2D;

public class AIWander extends AIBase { //NOTE discarded (at least unused for now)
	
	protected final EntityLiving entity;
    protected Pos2D pos;
    protected boolean reachedX;
    protected int executionChance; //the higher the value, the lower the probability ([1/value]*100 = probability)
    private int tickCount;
    private int tickRate = 30;
	
    public AIWander(EntityLiving creatureIn) {
        this(creatureIn, 50);
    }

    public AIWander(EntityLiving creatureIn, int chance) {
        this.entity = creatureIn;
        this.executionChance = chance;
        pos = new Pos2D();
    }
    
	@Override
	public boolean shouldExecute() {
		if (tickCount++ % this.tickRate == 0) {
			if (this.entity.getRNG().nextInt(this.executionChance) != 0) {
		        return false;
			}
			tickCount=1;
			return true;
		}
		return false;
	}
	
	@Override
	public void startExecuting() {
		System.out.println(entity.toString() + " Started task: 'Wander'");
		reachedX=false;
		pos.setPos(Pos2D.generateRandomPosX(entity, 10));
		System.out.println(pos.getX() + " | " + pos.getY());
	}
	
	@Override
	public boolean continueExecuting() {
		if(pos.getX()<=0 || pos.getX()>=Game.getHandler().getWorld().WIDTH-1) {
			System.out.println(entity.toString() + " Ended Task: 'Wander', Position was out of Bounds!");
			return false;
		}
		
		if(reachedX && entity.getCurrentMomentum()==0) {
			System.out.println(entity.toString() + " Ended task: 'Wander'");
			return false;
		}
		return true;
	}
	
	@Override
	public void tick() {
		if(!reachedX) {
			if (round((entity.getPos().getX()+entity.getAABB().getX()+entity.getAABB().getWidth()/2), 1) > round(pos.getX(), 1)) {
				entity.accelerate(-0.05);
			} else if (round((entity.getPos().getX()+entity.getAABB().getX()+entity.getAABB().getWidth()/2), 1) < round(pos.getX(), 1)) {
				entity.accelerate(+0.05);
			} else reachedX=true;
			
			entity.setFacing();
			float offset = (entity.getFacing() == Facing.EAST) ? -0.25F : 1.25F;
			if(Game.getHandler().getWorld().getTileAt((int) (entity.getPosX()+offset), (int)entity.getPosY()).isSolid()) {
				entity.jump();
			}
		} else entity.slowDown();
	}
	
	private double round(double a ,int decimals) {
		decimals*=Math.pow(10, decimals);
		a*=decimals;
		a=Math.round(a);
		a/=decimals;
		return a; 
	}
}
