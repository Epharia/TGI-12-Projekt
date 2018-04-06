package game.util.math;

import java.util.Random;

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
	
	public static Pos2D generateRandomPos(Area2D area, Random rand) {
		Pos2D pos = new Pos2D();
		pos.setX((rand.nextDouble()*area.getWidth())+area.getX());
		pos.setY((rand.nextDouble()*area.getHeight())+area.getY());
		return pos;
	}
	
	public static Pos2D generateRandomPos(double x, double y, double width, double height, Random rand) {
		return generateRandomPos(new Area2D(x, y, width, height), rand);
	}
	
	//SETTER
	public void setPos(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void setPos(int a) {
		this.setPos(a,a);
	}
	
	public void setPos(Pos2D pos) {
		this.setPos(pos.getX(),pos.getY());
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
