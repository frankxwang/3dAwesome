import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Draw {
	public static int xRot = 0;
	public static int yRot = 0;
	public static int zRot = 0;
	public static String rotMode = "x";
	static ArrayList vecs = new ArrayList<Vec3>();
	public static int W = 1000;
	public static int H = 1000;
	public static Vec3 CENTER = new Vec3(W/2, H/2, 0);
	public static FrameDraw panel;
	public static JLabel label;
	/*private void DoStuff() {
		
	}*/
	Draw() {
//		vecs.add(new Vec3(500, 250, 0));
//		((Vec3)vecs.get(0)).Rotate("z", new Vec3(H/2, W/2, 0), -90);
//		System.out.println(((Vec3)vecs.get(0)).x + " " + ((Vec3)vecs.get(0)).y + " " + ((Vec3)vecs.get(0)).z);
		vecs = Vec3.getCube(CENTER, 100);
		//DoStuff();
		
		JFrame frame = new JFrame();
		
		label = new JLabel("X: 0, Y:0, Z:0");
		label.setOpaque(true);
	    label.setBackground(Color.GRAY);
	    label.setForeground(Color.WHITE);
		
		panel = new FrameDraw();
		panel.setSize(new Dimension(W, H));
		
		frame.setPreferredSize(new Dimension(W, H));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		
		(panel).paintComponent(panel.getGraphics());
		
		frame.pack();
		Vec3.translateArray(vecs, new Vec3(0, -150, 0));
//		while(true){
//			sleep(100);
//			Vec3.rotateArray(vecs, "z", CENTER, 3);
//			Vec3.rotateArray(vecs, "y", CENTER, 3);
//			Vec3.rotateArray(vecs, "x", CENTER, 3);
//		//Vec3.rotateArray(vecs, "y", new Vec3(W/2, H/2, 0), 4);
//			(panel).paintComponent(panel.getGraphics());
//		
//		}
		Timer t = new Timer();
		t.schedule(new DoStuff(), 0, 100);
	}
	class DoStuff extends TimerTask{
		@Override
		public void run() {
			//Vec3.dilateArray(vecs, CENTER, 0.99f);
			Vec3.rotateArray(vecs, "x", CENTER, xRot);
			Vec3.rotateArray(vecs, "y", CENTER, yRot);
			Vec3.rotateArray(vecs, "z", CENTER, zRot);
			
			label.setText("Mode: " + rotMode + " X:" + xRot + ", Y:" + yRot + ", Z:" + zRot);
			
			(panel).repaint();
		}
	}
	
	private static void sleep(int m){
		try{Thread.sleep(m);}catch(Exception e){}
	}
	
	class FrameDraw extends JPanel implements KeyListener{
		public static final long MAX_DIST = 5000l;
		FrameDraw(){
			addKeyListener(this);
		}
		protected void paintComponent(Graphics g) {
			g.clearRect(0, 0, W*2, H*2);
			int[] a1 = {0,4,5,1};
			int[] a2 = {2,0,1,3};
			int[] a3 = {6,7,5,4};
			int[] a4 = {6,7,3,2};
			int[] a5 = {3,7,5,1};
			int[] a6 = {2,6,4,0};
			int[][] arrays = {a1, a2, a3, a4, a5, a6};
			
			//lambda with arrow thing-a-am-gigs
//			Arrays.sort(arrays, (aa1, aa2) -> 
//			((Integer)(int)(Vec3.midpoint((Vec3)vecs.get(aa1[3]),
//					(Vec3)vecs.get(aa1[1])).z)).compareTo
//			((Integer)(int)(Vec3.midpoint((Vec3)vecs.get(aa2[3]),
//					(Vec3)vecs.get(aa2[1])).z)));
			
//			Arrays.sort(arrays, (aa1, aa2) -> 
//			((Integer)(int)(Vec3.midpoint((Vec3)vecs.get(aa1[0]),
//					(Vec3)vecs.get(aa1[2])).z)).compareTo
//			((Integer)(int)(Vec3.midpoint((Vec3)vecs.get(aa2[0]),
//					(Vec3)vecs.get(aa2[2])).z)));
			arrays = sortFaces(arrays);
			
			for(int[] i:arrays){
				System.out.print("{");
				for(int a:i){
					System.out.print(((Vec3)vecs.get(a)).z + ",");
				}
				System.out.println("}");
			}
			
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
//			for(int i=0; i<arrays.length; i++){
//				int[] aInt = arrays[i];
//				int[][] aaInt = getArray(vecs, aInt);
//				g.drawPolygon(aaInt[0], aaInt[1], aInt.length);
//			}
			draw3d(arrays, g);
		}
//		private Integer getZInt(int[] a){
//			return Vec3.midpoint((Vec3)vecs.get(a[0]), (Vec3)vecs.get(a[2]));
//		}
		private int[][] getArray(ArrayList vecs, int[] array){
			int[][] rArray = new int[2][array.length];
			
			for(int i=0; i<array.length; i++){
				Vec3 vec = (Vec3)(vecs.get(array[i]));
				int x = (int)vec.x;
				int y = (int)vec.y;
				int z = (int)vec.z;
				rArray[0][i] = getX(x, z);
				rArray[1][i] = getY(y, z);
			}
			return rArray;
		}
		private void draw3d(int[][] arrays, Graphics g){
			for(int i=0; i<arrays.length; i++){
				int[] aInt = arrays[i];
				int[][] aaInt = getArray(vecs, aInt);
				g.setColor(Color.gray);
				g.fillPolygon(aaInt[0], aaInt[1], aInt.length);
				g.setColor(Color.black);
				g.drawPolygon(aaInt[0], aaInt[1], aInt.length);
			}
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
		private int[][] sortFaces(int[][] arrays){

			Integer[] VecsMidZ = new Integer[arrays.length];
			for(int i=0; i<arrays.length; i++){
//				int[] iArray = arrays[i];
//				VecsMidZ[i] = (int)(Vec3.midpoint((Vec3)(vecs.get(iArray[0])), (Vec3)(vecs.get(iArray[2]))).z*10) + i;
//				VecsMidZ[i] = ((int)(Vec3.midpoint((Vec3)(vecs.get(iArray[0])), (Vec3)(vecs.get(iArray[0]))).z)) * 10 + i;
				Vec3 vec1 = new Vec3(0,0,0);
				vec1.x = ((Vec3)vecs.get(arrays[i][0])).x;
				vec1.y = ((Vec3)vecs.get(arrays[i][0])).y;
				vec1.z = ((Vec3)vecs.get(arrays[i][0])).z;
				Vec3 vec2 = new Vec3(0,0,0);//(Vec3) vecs.get(arrays[i][2]);
				vec2.x = ((Vec3)vecs.get(arrays[i][2])).x;
				vec2.y = ((Vec3)vecs.get(arrays[i][2])).y;
				vec2.z = ((Vec3)vecs.get(arrays[i][2])).z;
				Vec3.midpoint(vec1, vec2);
				
				VecsMidZ[i]=(Integer)(int) vec1.z;
//				VecsMidZ[i] = ((int) ((Vec3)(vecs.get(iArray[0]))).z);
				VecsMidZ[i] *= 10;
				VecsMidZ[i] += i;
			}
			Arrays.sort(VecsMidZ, Collections.reverseOrder());
			int[] sorted = new int[arrays.length];
			int[][] temp = new int[arrays.length][arrays[0].length];
			for(int i=0; i<arrays.length; i++){
				for(int j=0; j<arrays[0].length; j++){
					temp[i][j] = arrays[i][j];
				}
			}
//			temp = arrays;
			
			for(int i=0; i<arrays.length; i++){
				int mod = VecsMidZ[i]%10;
				if (mod<0) mod += 10;
				System.out.println(mod);
				arrays[i] = temp[mod];
			}
			
			return arrays;
		}
		public void addNotify() {
	        super.addNotify();
	        requestFocus();
	    }
		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("HUEONGIWVWOGOIJGWKLMFCJEUWIHBHUBWIHFHOBHWROFHJBKWNOENFJFONJKW NOJFNKE WNJOFNJK EWJIODSJEWO");
			if(e.getKeyChar() == 'x'){
				rotMode = "x";
			}else if(e.getKeyChar() == 'y'){
				rotMode = "y";
			}else if(e.getKeyChar() == 'z'){
				rotMode = "z";
			}
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int keyNum = e.getKeyCode();
			char keyChar = e.getKeyChar();
			if(keyNum == KeyEvent.VK_RIGHT){
				if(rotMode == "x"){
					xRot += 1;
				}else if(rotMode == "y"){
					yRot += 1;
				}else if(rotMode == "z"){
					zRot += 1;
				}
			}else if(keyNum == KeyEvent.VK_LEFT){
				if(rotMode == "x"){
					xRot -= 1;
				}else if(rotMode == "y"){
					yRot -= 1;
				}else if(rotMode == "z"){
					zRot -= 1;
				}
			}
			if(keyChar == '-'){
				Vec3.dilateArray(vecs, CENTER, 0.99f);
			}else if(keyChar == '='){
				Vec3.dilateArray(vecs, CENTER, 1.01f);
			}
			if(keyChar == 'w'){
				Vec3.translateArray(vecs, Vec3.UP.multiply(5));
			}else if(keyChar == 's'){
				Vec3.translateArray(vecs, Vec3.DOWN.multiply(5));
			}else if(keyChar == 'a'){
				Vec3.translateArray(vecs, Vec3.LEFT.multiply(5));
			}else if(keyChar == 'd'){
				Vec3.translateArray(vecs, Vec3.RIGHT.multiply(5));
			}else if(keyChar == 'q'){
				Vec3.translateArray(vecs, Vec3.FORWARD.multiply(5));
			}else if(keyChar == 'e'){
				Vec3.translateArray(vecs, Vec3.BACKWARD.multiply(5));
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
