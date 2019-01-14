package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.entity.ai.AITasks;
import game.gfx.animation.Animation;

public class EntityLiving extends Entity {

	//Facing
	public enum Facing {EAST, WEST;}
	protected Facing facing=Facing.EAST;
	
	//Animation
	protected Animation animation;
	
	//DEFAULTS
	public static final double DEFAULT_SPEED = 1;
	public static final double DEFAULT_JUMP_POWER=3.5;
	public static final int DEFAULT_DMG= 1;
	
	//ATTRIBUTES
	protected double speed = DEFAULT_SPEED;
	protected double jumpPower = DEFAULT_JUMP_POWER;
	protected int dmg = DEFAULT_DMG;
	protected double momentumX, momentumY;
	protected int momentumModifier = 32;
	
	//AI
	protected final AITasks tasks = new AITasks();
	
	@Override
	public void render(Graphics g) {}
	
	@Override
	public void tick() {
		
		tasks.tick();

		if (momentumX!=0)
			animation.tick();
		else animation.setFrameIndex(0);
		
		moveX();
		moveY();
		
		isEntityCollision();
		
		setFacing();
	}
	
	public void setFacing() {
		if(momentumX<0)
			facing=Facing.EAST;
		else if(momentumX>0)
			facing=Facing.WEST;
	}
	
	public void flipFacing() {
		if(getFacing() == Facing.EAST)
			facing = Facing.WEST;
		else facing = Facing.EAST;
	}

	public void moveY() {		
		if(momentumY<0 && getPosY()<0) {
			momentumY=0;
			return;
		} else if(momentumY>0 && getPosY()>Game.getHandler().getWorld().HEIGHT-(getAABB().getHeight()+getAABB().getY()-1)) {
			momentumY=0;
			setDead(true);
			return;
		}
		
		gravitation();
		
		pos.setY(getPosY()+momentumY/momentumModifier);
		
		boolean b = false;
		while(isTileCollision()) {
			if(momentumY!=0)
				pos.setY(getPosY()-(momentumY/Math.abs(momentumY)*0.0005));
			b=true;
		}
		
		if(b) {
			if(momentumY>0)
				isAirborn=false;
			momentumY=0;
			return;
		}
		isAirborn=true;
	}
	
	private void gravitation() {
		if (momentumY<Game.getHandler().getWorld().GRAVITATION) //NOTE Maybe Change (f.ex. MAX_GRAVITATION_SPEED)
			momentumY+=Game.getHandler().getWorld().GRAVITATION/100;
	}
	
	private void moveX() {
		if(momentumX<0 && getPosX()<0) {
			momentumX = 0;
			return;
		} else if(momentumX>0 && getPosX()>Game.getHandler().getWorld().WIDTH-(getAABB().getWidth()+getAABB().getX()+momentumX/momentumModifier)) {
			momentumX = 0;
			return;
		}
		
		pos.setX(getPosX()+(momentumX)/momentumModifier);
		
		boolean b = false;
		while(isTileCollision()) {
			if(momentumX!=0)
				pos.setX(getPosX()-(momentumX/Math.abs(momentumX)*0.005));
			b=true;
		}
		
		if(b) {
			momentumX=0;
		}
	}
	
	public void jump() {
		momentumY = -jumpPower;
		isAirborn = true;
	}
	
	public void accelerate(double amount) {
		if(momentumX<=speed && momentumX>=-speed)
			momentumX+=amount;
		else if (momentumX>speed)
			momentumX=speed;
		else if (momentumX<-speed)
			momentumX=-speed;
	}
	
	public void slowDown(float amount) {
		if(momentumX>=amount)
			momentumX-=amount;
		else if (momentumX<=-amount)
			momentumX+=amount;
		else
			momentumX = 0;
	}
	
	public void slowDown() {
//		if(momentumX>=0.025)
//			momentumX-=0.025;
//		else if (momentumX<=-0.025)
//			momentumX+=0.025;
//		else
//			momentumX = 0;
		slowDown(0.025F);
	}
	
	//SETTER
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setJumpPower(double jumpPower) {
		this.jumpPower = jumpPower;
	}
	
	//GETTER
	public double getCurrentMomentum() {
		return momentumX;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	protected BufferedImage getFrame() {
		switch(facing) {
		case EAST: return animation.getFrame();
		case WEST: return animation.getFrameFliped();
		}
		return null;
	}
	
	public Facing getFacing() {
		return facing;
	}
	
	public int getDamage() {
		return dmg;
	}
}
