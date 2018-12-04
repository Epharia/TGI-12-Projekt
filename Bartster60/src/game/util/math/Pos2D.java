package game.util.math;

import java.util.Random;

import game.entity.Entity;

/* 
 * |========================================================|
 * |this Class is used instead of the given Point (2D) Class|
 * |========================================================|
 */

public class Pos2D {
	private double x;
	private double y;
	
	public Pos2D(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public Pos2D(Pos2D pos) {
		this(pos.getX(), pos.getY());
	}
	
	public Pos2D(double a) {
		this(a,a);
	}
	
	public Pos2D() {
		this(0,0);
	}
	
	public static Pos2D generateRandomPosX(Entity entity, double distance) {
		Pos2D pos = new Pos2D();
		pos.setX(entity.getPosX() + entity.getRNG().nextDouble()*(distance*2)-distance);
		pos.setY(entity.getPosY());
		return pos;
	}
	
	@Deprecated
	public static Pos2D generateRandomPos(Area2D area, Random rand) {
		Pos2D pos = new Pos2D();
		pos.setX((rand.nextDouble()*area.getWidth())+area.getX());
		pos.setY((rand.nextDouble()*area.getHeight())+area.getY());
		return pos;
	}
	
	@Deprecated
	public static Pos2D generateRandomPos(double x, double y, double width, double height, Random rand) {
		return generateRandomPos(new Area2D(x, y, width, height), rand);
	}
	
	//SETTER
	public void set(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void setPos(int a) {
		this.set(a,a);
	}
	
	public void setPos(Pos2D pos) {
		this.set(pos.getX(),pos.getY());
	}
	
	public void setX(double x) {
		this.x=x;
	}
	
	public void setY(double y) {
		this.y=y;
	}
	
	//GETTER
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String toString()
    {
        return "Pos[" + this.x + " | " + this.y + "]";
    }
}
