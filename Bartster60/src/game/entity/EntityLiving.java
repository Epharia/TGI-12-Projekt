package game.entity;

import java.awt.Graphics;

import game.Game;
import game.world.World;

public class EntityLiving extends Entity {

	//DEFAULTS
	public static final double DEFAULT_SPEED = 1;
	public static final double DEFAULT_JUMP_POWER=4;
	
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
		isAirborn = true;
		
		if (momentumY<Game.getHandler().getWorld().GRAVITATION) //NOTE Maybe Change (f.ex. MAX_GRAVITATION_SPEED)
			momentumY+=Game.getHandler().getWorld().GRAVITATION/100;
		
		if(momentumY<0 && getPosY()<0) {
			momentumY=0;
			return;
		} else if(momentumY>0 && getPosY()>Game.getHandler().getWorld().HEIGHT-1) {
			momentumY=0;
			return;
		}
		
		if(isTileCollision(0, momentumY/momentumModifier)) {
			if(momentumY>0) {
				for(double i=0; i<momentumY; i+=0.01) {
					if(!isTileCollision(0, (momentumY-i)/momentumModifier)) {
						pos.setY(getPosY()+(momentumY-i)/momentumModifier);
						momentumY=0;
						isAirborn=false;
						return;
					}
				}
				isAirborn=false;
			} else if(momentumY<0) {
				for(double i=0; i<-momentumY; i+=0.01) {
					if(!isTileCollision(0, (momentumY+i)/momentumModifier)) {
						pos.setY(getPosY()+(momentumY+i)/momentumModifier);
						momentumY=0;
						return;
					}
				}
			}
			momentumY=0;
			return;
		}
		
		pos.setY(getPosY()+momentumY/momentumModifier);
	}
	
	private boolean isTileCollision(double xOffset, double yOffset) {
		World w = Game.getHandler().getWorld();
		
		int x1 = (int) (getPosX()+getAABB().getX()+xOffset-1); //LEFT
		int y1 = (int) (getPosY()+getAABB().getY()+yOffset-1); //RIGHT
		
		int x2 = (int) (x1 + getAABB().getWidth()+2); //TOP
		int y2 = (int) (y1 + getAABB().getHeight()+2); //BOTTOM
		
		if(x1<0)
			x1=0;
		if(x2>w.WIDTH-1)
			x2=w.WIDTH-1;
		
		if(y1<0)
			y1=0;
		if(y2>w.HEIGHT-1)
			y2=w.HEIGHT-1;
		
		for(int y=y1; y<=y2; y++)
			for(int x=x1; x<=x2; x++) {
				if(w.getTileAt(x, y).isSolid() && getCollisionBounds(xOffset, yOffset).intersects(w.getCollisionFromTileAt(x, y))) {
					return true;
				}
			}
		return false;
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
		if(momentumX<0 && getPosX()<0) {
			momentumX = 0;
			return;
		} else if(momentumX>0 && getPosX()>Game.getHandler().getWorld().WIDTH-1) {
			momentumX = 0;
			return;
		}
		
		if (isTileCollision(momentumX/momentumModifier, 0)) {
			if(momentumX>0) {
				for(double i=0; i<momentumX; i+=0.01) {
					if(!isTileCollision((momentumX-i)/momentumModifier, 0)) {
						pos.setX(getPosX()+(momentumX-i)/momentumModifier);
						momentumX=0;
						return;
					}
				}
			} else if(momentumX<0) {
				for(double i=0; i<-momentumX; i+=0.01) {
					if(!isTileCollision((momentumX-i)/momentumModifier, 0)) {
						pos.setX(getPosX()+(momentumX+i)/momentumModifier);
						momentumX=0;
						return;
					}
				}
			}
			momentumX=0;
			return;
		}
		
		pos.setX(getPosX()+(momentumX)/momentumModifier);
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
