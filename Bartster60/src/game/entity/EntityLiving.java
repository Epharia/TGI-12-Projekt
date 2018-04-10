package game.entity;

import java.awt.Graphics;

import game.Game;

public class EntityLiving extends Entity { //TODO improve movement REWORK Tile collision (fix collision for AABB > 1)

	//DEFAULTS
	public static final double DEFAULT_SPEED = 1;
	public static final double DEFAULT_JUMP_POWER=5;
	
	//ATTRIBUTES
	protected double speed = DEFAULT_SPEED;
	protected double jumpPower = DEFAULT_JUMP_POWER;
	protected double momentumX, momentumY;
	protected int momentumModifier = 25;
	
	@Override
	public void render(Graphics g) {}
	
	@Override
	public void tick() {
		moveY();
		moveX();
	}
	
	public void moveY() {
		isAirborn=true;
		
		if(momentumY>0)
		for(double i=0; i<momentumY;i+=0.01) {
			if  (!Game.getHandler().getWorld().getTileAt((int) (getPosX()+getAABB().getX()), (int) (getPosY()+getAABB().getY()+getAABB().getHeight()+(momentumY-i)/momentumModifier)).isSolid() && !Game.getHandler().getWorld().getTileAt((int)(getPosX()+getAABB().getX()+getAABB().getWidth()), (int) (getPosY()+getAABB().getY()+getAABB().getHeight()+(momentumY-i)/momentumModifier)).isSolid()) {
				momentumY-=i;
				break;
			}
			isAirborn=false;
		}
		
		if(isAirborn) {
			if (momentumY<Game.getHandler().getWorld().GRAVITATION) //NOTE Maybe Change (f.ex. MAX_SPEED_G)
				momentumY+=Game.getHandler().getWorld().GRAVITATION/100;
			
			if(momentumY<0) {
				for(double i=0; i<-momentumY;i+=0.01) {
					if (!Game.getHandler().getWorld().getTileAt((int) (getPosX()+getAABB().getX()), (int) (getPosY()+getAABB().getY()+(momentumY+i)/momentumModifier)).isSolid() && !Game.getHandler().getWorld().getTileAt((int)(getPosX()+getAABB().getX()+getAABB().getWidth()), (int) (getPosY()+getAABB().getY()+(momentumY+i)/momentumModifier)).isSolid() && getPosY()+momentumY/momentumModifier>0) {
						pos.setY(getPosY()+(momentumY+i)/momentumModifier);
						break;
					}
					momentumY=0;
				}
			} else pos.setY(getPosY()+momentumY/momentumModifier);
		}
	}
	
	public void jump() {
		momentumY = -jumpPower;
		isAirborn = true;
	}
	
	public void accelerate(double amount) {
		if(momentumX<speed && momentumX>-speed)
			momentumX+=amount;
		else if(momentumX>speed || momentumX<-speed)
			slowDown();
	}
	
	public void slowDown() {
		if(momentumX>=0.025)
			momentumX-=0.025;
		else if (momentumX<=-0.025)
			momentumX+=0.025;
		else
			momentumX = 0;
	}
	
	private void moveX() {
		if (momentumX>0) //R
			if (!Game.getHandler().getWorld().getTileAt((int) (getPosX()+getAABB().getX()+getAABB().getWidth()+(momentumX)/momentumModifier), (int) (getPosY()+AABB.getY())).isSolid() &&
				!Game.getHandler().getWorld().getTileAt((int) (getPosX()+getAABB().getX()+getAABB().getWidth()+(momentumX)/momentumModifier), (int) (getPosY()+getAABB().getY()+getAABB().getHeight()-0.01)).isSolid() && getPosX()+getAABB().getX()+getAABB().getWidth()+(momentumX)/momentumModifier<Game.getHandler().getWorld().WIDTH-0.1) {
				pos.setX(getPosX()+(momentumX)/momentumModifier);
				return;
			}
		
		if(momentumX<0) //L
			if (!Game.getHandler().getWorld().getTileAt((int) (getPosX()+getAABB().getX()+momentumX/momentumModifier), (int) (getPosY()+AABB.getY())).isSolid() &&
				!Game.getHandler().getWorld().getTileAt((int) (getPosX()+getAABB().getX()+momentumX/momentumModifier), (int) (getPosY()+getAABB().getY()+getAABB().getHeight()-0.01)).isSolid() && getPosX()+momentumX/momentumModifier>0) {
				pos.setX(getPosX()+momentumX/momentumModifier);
				return;
			}
		momentumX=0;
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
}
