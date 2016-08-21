
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
			z = (float) (point.z*Math.cos(deg) - point.x*Math.sin(deg));
			x = (float) (point.x*Math.cos(deg) + point.z*Math.sin(deg));
		}else if(axis.equals("x")){
			y = (float) (point.y*Math.cos(deg) - point.x*Math.sin(deg));
			x = (float) (point.x*Math.cos(deg) + point.y*Math.sin(deg));
		}else if(axis.equals("z")){
			x = (float) (point.x*Math.cos(deg) - point.y*Math.sin(deg));
			y = (float) (point.y*Math.cos(deg) + point.x*Math.sin(deg));
		}
	}
}
