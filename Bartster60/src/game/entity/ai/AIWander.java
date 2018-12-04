package game.entity.ai;

import game.Game;
import game.entity.EntityLiving;
import game.util.math.Pos2D;

public class AIWander extends AIBase { //TODO improve Wander AI (use Path)
	
	protected final EntityLiving entity;
    protected Pos2D pos;
    protected final double speed;
    protected boolean reachedX;
    protected int executionChance;
    private int tickCount;
    private int tickRate = 10;
	
    public AIWander(EntityLiving creatureIn) {
        this(creatureIn, 200);
    }

    public AIWander(EntityLiving creatureIn, int chance) {
        this.entity = creatureIn;
        this.speed = entity.getSpeed();
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
		if(reachedX && entity.getCurrentMomentum()==0) {
			System.out.println(entity.toString() + " Ended task: 'Wander'");
			return false;
		}
		return true;
	}
	
	@Override
	public void tick() {
		if(!reachedX) {
			if (round((entity.getPos().getX()*(double)Game.TILESCALE+entity.getAABB().getX()+entity.getAABB().getWidth()/2)/(double)Game.TILESCALE, 1) > round(pos.getX(), 1)) {
				entity.accelerate(-0.05);
			} else if (round((entity.getPos().getX()*(double)Game.TILESCALE+entity.getAABB().getX()+entity.getAABB().getWidth()/2)/(double)Game.TILESCALE, 1) < round(pos.getX(), 1)) {
				entity.accelerate(+0.05);
			} else reachedX=true;
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
