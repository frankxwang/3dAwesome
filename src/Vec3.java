import java.util.ArrayList;

public class Vec3 {
	public static final Vec3 UP = new Vec3(0, -1, 0);
	public static final Vec3 DOWN = new Vec3(0, 1, 0);
	public static final Vec3 LEFT = new Vec3(-1, 0, 0);
	public static final Vec3 RIGHT = new Vec3(1, 0, 0);
	public static final Vec3 FORWARD = new Vec3(0, 0, -1);
	public static final Vec3 BACKWARD = new Vec3(0, 0, 1);
	
	public float x;
	public float y;
	public float z;
	
	Vec3(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void rotate(String axis, Vec3 point, float deg){
		deg = (float) Math.toRadians(deg);
		if(axis.equals("y")){
			float[] coords = getCoords(point.z, this.z, point.x, this.x, deg);
			this.z = coords[0];
			this.x = coords[1];
//			this.z = getX(point.z, this.z, point.x, this.x, deg);
//			this.x = getY(point.z, this.z, point.x, this.x, deg);
			//System.out.println("hi1");
		}else if(axis.equals("x")){
			float[] coords = getCoords(point.y, this.y, point.z, this.z, deg);
			this.y = coords[0];
			this.z = coords[1];
//			this.y = getX(point.y, this.y, point.z, this.z, deg);
//			this.z = getY(point.y, this.y, point.z, this.z, deg);
			//System.out.println("hi2");
		}else if(axis.equals("z")){
			float[] coords = getCoords(point.x, this.x, point.y, this.y, deg);
			this.x = coords[0];
			this.y = coords[1];
//			this.x = getX(point.x, this.x, point.y, this.y, deg);
//			this.y = getY(point.x, this.x, point.y, this.y, deg);
			//System.out.println("hi3");
		}
	}
//	private float getX(float ptX, float thX, float ptY, float thY, float deg){
//		return (float) ((ptX - thX)*Math.cos(deg) - (ptY - thY)*Math.sin(deg) + ptX);
//	}
//	private float getY(float ptX, float thX, float ptY, float thY, float deg){
//		return (float) ((ptY - thY)*Math.cos(deg) + (ptX - thX)*Math.sin(deg) + ptY);
//	}
	private float[] getCoords(float ptX, float thX, float ptY, float thY, float deg){
//		float angle = (float) (deg * (Math.PI/180));
//		float angle = (float)Math.toRadians(deg);
		float angle = deg;
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		float x = cos*(thX-ptX) - sin*(thY-ptY) + ptX;
		float y = sin*(thX-ptX) + cos*(thY-ptY) + ptY;
		float[] coords = {x,y};
		return coords;
	}
//	angle = (angle ) * (Math.PI/180); // Convert to radians
//	var rotatedX = Math.cos(angle) * (point.x - center.x) - Math.sin(angle) * (point.y-center.y) + center.x;
//	var rotatedY = Math.sin(angle) * (point.x - center.x) + Math.cos(angle) * (point.y - center.y) + center.y;
//	
	public static ArrayList getCube(Vec3 center, float side){
		ArrayList vecs = new ArrayList<Vec3>();
		float s = side/2;
		for(int i=-1; i<=1; i+=2){
			for(int j=-1; j<=1; j+=2){
				for(int k=-1; k<=1; k+=2){
					vecs.add(new Vec3(center.x+s*i, center.y+s*j, center.z+s*k));
				}
			}
		}
		return vecs;
	}
	
	public static void rotateArray(ArrayList vecs, String axis, Vec3 point, float deg) {
		for (int i = 0; i < vecs.size(); i++) {
			((Vec3) vecs.get(i)).rotate(axis, point, deg);
		}
	}
	
	public Vec3 multiply(float m){
		Vec3 vec = new Vec3(this.x, this.y, this.z);
		vec.x *= m;
		vec.y *= m;
		vec.z *= m;
		return vec;
	}
	
	public void translate(Vec3 vec){
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
	}
	
	public static void translateArray(ArrayList vecs, Vec3 vec) {
		for (int i = 0; i < vecs.size(); i++) {
			((Vec3) vecs.get(i)).translate(vec);
		}
	}
	
	public void dilate(Vec3 vec, float scale){
		translate(new Vec3((this.x - vec.x)*(scale-1), (this.y - vec.y)*(scale-1), (this.z - vec.z)*(scale-1)));
	}
	
	public static void dilateArray(ArrayList vecs, Vec3 vec, float scale) {
		for (int i = 0; i < vecs.size(); i++) {
			((Vec3) vecs.get(i)).dilate(vec, scale);
		}
	}
	
	public static Vec3 midpoint(Vec3 vec1, Vec3 vec2){
		vec1.translate(vec2);
		vec1.x /= 2;
		vec1.y /= 2;
		vec1.z /= 2;
		return vec1;
	}
	
	public void print(){
		System.out.println("X: " + x + " Y: " + y + " Z: " + z);
	}
}
