import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Draw {
	static ArrayList vecs = new ArrayList<Vec3>();
	public static int W = 1000;
	public static int H = 1000;
	public static Vec3 CENTER = new Vec3(W/2, H/2, 0);
	/*private void DoStuff() {
		
	}*/
	Draw() {
//		vecs.add(new Vec3(500, 250, 0));
//		((Vec3)vecs.get(0)).Rotate("z", new Vec3(H/2, W/2, 0), -90);
//		System.out.println(((Vec3)vecs.get(0)).x + " " + ((Vec3)vecs.get(0)).y + " " + ((Vec3)vecs.get(0)).z);
		vecs = Vec3.getCube(CENTER, 100);
		//DoStuff();
		JFrame frame = new JFrame();
		
		FrameDraw panel = new FrameDraw();
		panel.setSize(new Dimension(W, H));
		
		frame.setPreferredSize(new Dimension(W, H));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1,1));
		frame.add(panel);
		
		(panel).paintComponent(panel.getGraphics());
		
		frame.pack();
		Vec3.translateArray(vecs, new Vec3(0, -150, 0));
		while(true){
			sleep(100);
			Vec3.rotateArray(vecs, "z", CENTER, 3);
			Vec3.rotateArray(vecs, "y", CENTER, 3);
			Vec3.rotateArray(vecs, "x", CENTER, 3);
		//Vec3.rotateArray(vecs, "y", new Vec3(W/2, H/2, 0), 4);
		(panel).paintComponent(panel.getGraphics());
		
		}
	}
	
	private static void sleep(int m){
		try{Thread.sleep(m);}catch(Exception e){}
	}
	
	class FrameDraw extends JPanel {
		public static final long MAX_DIST = 500l;
		protected void paintComponent(Graphics g) {
			g.clearRect(0, 0, W*2, H*2);
			int[] a1 = {0,4,5,1};
			int[] a2 = {2,0,1,3};
			int[] a3 = {4,6,7,5};
			int[] a4 = {2,6,7,3};
			int[] a5 = {3,7,5,1};
			int[] a6 = {2,6,4,0};
			int[][] arrays = {a1, a2, a3, a4, a5, a6};
			
			g.setColor(Color.red);
			drawPoint(CENTER, g);
			g.setColor(Color.black);
			g.drawChars("Center".toCharArray(), 0, "Center".length(), getX(CENTER.x, CENTER.z), getY(CENTER.y, CENTER.z));
			
			for (int i = 0; i < vecs.size(); i++) {
				Vec3 vec = (Vec3) vecs.get(i);
				vec.print();
				drawPoint(vec, g);
				char[] charAr = {Integer.toString(i).charAt(0)};
				g.drawChars(charAr, 0, 1, getX(vec.x, vec.z), getY(vec.y, vec.z));
			}
			System.out.println("\n\n");
			for(int i=0; i<arrays.length; i++){
				int[] aInt = arrays[i];
				int[][] aaInt = getArray(vecs, aInt);
				g.drawPolygon(aaInt[0], aaInt[1], aInt.length);
			}
		}
		private int[][] getArray(ArrayList vecs, int[] array){
			int[][] rArray = new int[2][array.length];
			for(int i=0; i<array.length; i++){
				int x = (int)((Vec3)(vecs.get(array[i]))).x;
				int y = (int)((Vec3)(vecs.get(array[i]))).y;
				int z = (int)((Vec3)(vecs.get(array[i]))).z;
				rArray[0][i] = getX(x, z);
				rArray[1][i] = getY(y, z);
			}
			return rArray;
		}
		private void drawPoint(Vec3 vec, Graphics g){
			g.fillRect(getX(vec.x, vec.z), getY(vec.y, vec.z), 5, 5);
		}
		private int getX(float x, float z){
			return (int)(x - (x - W/2)*z/MAX_DIST);
		}
		private int getY(float y, float z){
			return (int)(y - (y - H/2)*z/MAX_DIST);
		}
	}
}
