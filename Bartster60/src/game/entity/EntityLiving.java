package game.entity;

import java.awt.Graphics;

public class EntityLiving extends Entity {
	
	//DEFAULTS
	protected static final double DEFAULT_SPEED = 1.5;
	
	//ATTRIBUTES
	protected double speed = DEFAULT_SPEED;
	protected double momentumX, momentumY;
	
	@Override
	public void render(Graphics g) {}
	
	@Override
	public void tick() {
		gravitation();
		move();
	}
	
	public void gravitation() {
		if(isAirborn) {
			if (momentumY<3)
				momentumY+=0.05;
			
			pos.setY(getPosY()+momentumY/32);
		}
		if (getPosY()>14)
			isAirborn = false;
	}
	
	public void jump() {
		momentumY = -3;
		isAirborn = true;
	}
	
	public void accelerate(double amount) {
		if(momentumX<speed && momentumX>-speed)
			momentumX+=amount;
	}
	
	public void slowDown() {
		if(momentumX>=0.01)	
			momentumX-=0.01;
		else if (momentumX<=-0.01)
			momentumX+=0.01;
		else
			momentumX = 0;
	}
	
	private void move() {
		pos.setX(getPosX()+momentumX/32);
	}
}
