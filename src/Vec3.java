
public class Vec3 {
	public float x;
	public float y;
	public float z;
	
	Vec3(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void Rotate(String axis, Vec3 point, float deg){
		if(axis.equals("y")){
			this.z = getX(point.z, this.z, point.x, this.x, deg);
			this.x = getY(point.z, this.z, point.x, this.x, deg);
			System.out.println("hi1");
		}else if(axis.equals("x")){
			this.y = getX(point.y, this.y, point.z, this.z, deg);
			this.z = getY(point.y, this.y, point.z, this.z, deg);
			System.out.println("hi2");
		}else if(axis.equals("z")){
			this.x = getX(point.x, this.x, point.y, this.y, deg);
			this.y = getY(point.x, this.x, point.y, this.y, deg);
			System.out.println("hi3");
		}
	}
	public float getX(float ptX, float thX, float ptY, float thY, float deg){
		return (float) ((ptX - thX)*Math.cos(deg) - (ptY - thY)*Math.sin(deg) + ptX);
	}
	public float getY(float ptX, float thX, float ptY, float thY, float deg){
		return (float) ((ptY - thY)*Math.cos(deg) + (ptX - thX)*Math.sin(deg) + ptY);
	}
}
