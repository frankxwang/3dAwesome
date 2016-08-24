import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Draw {
	static ArrayList vecs = new ArrayList<Vec3>();
	public static int W = 1000;
	public static int H = 1000;
	
	/*private void DoStuff() {
		
	}*/
	Draw() {
//		vecs.add(new Vec3(500, 250, 0));
//		((Vec3)vecs.get(0)).Rotate("z", new Vec3(H/2, W/2, 0), -90);
//		System.out.println(((Vec3)vecs.get(0)).x + " " + ((Vec3)vecs.get(0)).y + " " + ((Vec3)vecs.get(0)).z);
		vecs = Vec3.getCube(new Vec3(W/2, H/2, 0), 100);
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
		while(true){
			try{Thread.sleep(100);}catch(Exception e){}
		Vec3.rotateArray(vecs, "x", new Vec3(W/2, H/2, 0),90);
		//Vec3.rotateArray(vecs, "y", new Vec3(W/2, H/2, 0), 4);
		(panel).paintComponent(panel.getGraphics());
		
		}
	}
	class FrameDraw extends JPanel {
		public static final long MAX_DIST = 1000000000l;
		protected void paintComponent(Graphics g) {
			g.clearRect(0, 0, W*2, H*2);
			int[] a1 = {0,4,5,1};
			int[] a2 = {2,0,1,3};
			int[] a3 = {4,6,7,5};
			int[] a4 = {2,6,7,3};
			int[] a5 = {3,7,5,1};
			int[] a6 = {2,6,4,0};
			int[][] arrays = {a1, a2, a3, a4, a5, a6};
			
			for (int i = 0; i < vecs.size(); i++) {
				Vec3 vec = (Vec3) vecs.get(i);
				g.fillRect((int)(vec.x - (vec.x - W/2)*vec.z/MAX_DIST), (int) (vec.y - (vec.y - H/2)*vec.z/MAX_DIST), 5, 5);
				char[] charAr = {Integer.toString(i).charAt(0)};
				g.drawChars(charAr, 0, 1, (int)(vec.x - (vec.x - W/2)*vec.z/MAX_DIST), (int) (vec.y - (vec.y - H/2)*vec.z/MAX_DIST));
			}
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
		private int getX(int x, int z){
			return (int)(x - (x - W/2)*z/MAX_DIST);
		}
		private int getY(int y, int z){
			return (int)(y - (y - H/2)*z/MAX_DIST);
		}
	}
}
