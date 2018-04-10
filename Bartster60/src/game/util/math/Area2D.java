package game.util.math;

import java.awt.Rectangle;

public class Area2D {
	
	protected double x;
	protected double y;
	protected double width;
	protected double height;
  
	public Area2D(Area2D area) {
		this(area.getX(), area.getY(), area.getWidth(), area.getHeight());
	}
	    
    public Area2D() {
    	this(0,0,0,0);
    }
  
    public Area2D(double x, double y, double width, double height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public Area2D(double x, double y, double scale) {
        this(x, y, scale, scale);
    }
    
    public Area2D(Pos2D pos, double width, double height) {
        this.setX(pos.getX());
        this.setY(pos.getY());
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public Area2D(Pos2D pos, double scale) {
        this(pos, scale, scale);
    }
    
    public boolean contains(double x, double y) {
    		return x>this.x&&y>this.y&&x<this.x+this.width&&y<this.y+this.height;
    }
    

	public boolean contains(Pos2D pos) {
		return contains(pos.getX(), pos.getY());
	}
	
	public boolean intersects(double x1, double y1, double x2, double y2) {
    	return this.x < x2+x1 && this.x + this.width > x1 && this.y < y2 + y1 && this.y + this.height > y1;
	}
	
	public boolean intersects(Area2D area) {
    	return intersects(area.x, area.y, area.width, area.height);
	}
    
    //SETTER
    public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setBounds(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
	}
	
	public void setSize(double size) {
		this.width = size;
		this.height = size;
	}
	
    //GETTER
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public String toString()
    {
        return "Area[" + this.x + ", " + this.y + " -> " + (this.x+this.width) + ", " + (this.y+this.height) + "]";
    }
	
    public Rectangle toRectangle() {
    	return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
}
