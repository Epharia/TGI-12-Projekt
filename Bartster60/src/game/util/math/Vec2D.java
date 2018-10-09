package game.util.math;

//TEST might be deleted again in the future

public class Vec2D {
	private double v1, v2;
	
	public Vec2D(double v1, double v2) {
		set(v1, v2);
	}
	
	public Vec2D() {
		set(0,0);
	}
	
	//SETTER
	public void set(double v1, double v2) {
		this.v1=v1;
		this.v2=v2;
	}
	
	public void setV1(double v1) {
		this.v1=v1;
	}
	
	public void setV2(double v2) {
		this.v2=v2;
	}
	
	//GETTER
	public double getV1() {
		return v1;
	}
	
	public double getV2() {
		return v2;
	}
	
	public double getMagnitude() {
		return Math.sqrt((v1*v1) + (v2*v2));
	}
	
	public String toString() {
		return "Vec[" + v1 + "," + v2 +"]";
	}
	
	//MATH
	public static Vec2D multScalar(double scalar, Vec2D vec) {
		return new Vec2D(vec.getV1()*scalar, vec.getV2()*scalar);
	}
	
	public static double multDot(Vec2D vec1, Vec2D vec2) {
		return vec2.getV1()*vec1.getV1() + vec2.getV2()*vec1.getV2();
	}
	
	public static double multCross(Vec2D vec1, Vec2D vec2) {
		return vec1.getV1()*vec2.getV2() - vec1.getV2()*vec2.getV1();
	}
	
	public static double getAngle(Vec2D vec1, Vec2D vec2) {
		return Math.acos(multDot(vec1, vec2)/(vec1.getMagnitude()*vec2.getMagnitude()));
	}
}
