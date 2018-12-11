package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.entity.ai.AITasks;
import game.gfx.animation.Animation;
import game.world.World;

public class EntityLiving extends Entity {

	//Facing
	protected enum Facing {EAST, WEST;}
	protected Facing facing=Facing.EAST;
	
	//Animation
	protected Animation animation;
	
	//DEFAULTS
	public static final double DEFAULT_SPEED = 1;
	public static final double DEFAULT_JUMP_POWER=4;
	
	//ATTRIBUTES
	protected double speed = DEFAULT_SPEED;
	protected double jumpPower = DEFAULT_JUMP_POWER;
	protected double momentumX, momentumY;
	protected int momentumModifier = 32;
	
	//AI
	protected final AITasks tasks = new AITasks();;
	
	@Override
	public void render(Graphics g) {}
	
	@Override
	public void tick() {
		
		tasks.tick();
		
		moveX();
		moveY();
		
		checkTileCollision();
		
		setFacing();
		
		if (momentumX!=0)
			animation.tick();
		else animation.setFrameIndex(0);
	}
	
	private void checkTileCollision() {
		boolean b = false;
		
		while(isTileCollision()) {
			onTileCollision();
			b=true;
		}
		if(b)
			momentumY=0;
	}
	
	private void onTileCollision() { //FIXME Improve
		if(momentumX!=0)
		pos.setX(getPosX()-(momentumX/Math.abs(momentumX)*0.005));
		if(momentumY!=0)
		pos.setY(getPosY()-(momentumY/Math.abs(momentumY)*0.005));
		isAirborn=false;
	}
	
	private void setFacing() {
		if(momentumX<0)
			facing=Facing.EAST;
		else if(momentumX>0)
			facing=Facing.WEST;
	}

	public void moveY() {
		if (momentumY<Game.getHandler().getWorld().GRAVITATION) //NOTE Maybe Change (f.ex. MAX_GRAVITATION_SPEED)
			momentumY+=Game.getHandler().getWorld().GRAVITATION/100;
		
		if(momentumY<0 && getPosY()<0) {
			momentumY=0;
			return;
		} else if(momentumY>0 && getPosY()>Game.getHandler().getWorld().HEIGHT-(getAABB().getHeight()+getAABB().getY()+momentumY/momentumModifier)) {
			momentumY=0;
			return;
		}
		
		pos.setY(getPosY()+momentumY/momentumModifier);
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
	}
	
	private boolean isTileCollision() {
		World w = Game.getHandler().getWorld();
		
		int x1 = (int) (getPosX()+getAABB().getX()-1); //LEFT
		int y1 = (int) (getPosY()+getAABB().getY()-1); //RIGHT
		
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
				if(w.getTileAt(x, y).isSolid() && getCollisionBounds().intersects(w.getCollisionFromTileAt(x, y))) {
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
	}
	
	public void slowDown() {
		if(momentumX>=0.025)
			momentumX-=0.025;
		else if (momentumX<=-0.025)
			momentumX+=0.025;
		else
			momentumX = 0;
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
}
