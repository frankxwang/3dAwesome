import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Draw {
	static ArrayList vecs = new ArrayList<Vec3>();
	public static int W = 500;
	public static int H = 1000;
	
	private void DoStuff() {
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
	}
	Draw() {
//		vecs.add(new Vec3(500, 250, 0));
//		((Vec3)vecs.get(0)).Rotate("z", new Vec3(H/2, W/2, 0), -90);
//		System.out.println(((Vec3)vecs.get(0)).x + " " + ((Vec3)vecs.get(0)).y + " " + ((Vec3)vecs.get(0)).z);
		vecs = Vec3.getCube(new Vec3(W/2, H/2, 0), 100);
		DoStuff();
	}
	class FrameDraw extends JPanel {
		public static final int MAX_DIST = 100;
		protected void paintComponent(Graphics g) {
			for (int i = 0; i < vecs.size(); i++) {
				Vec3 vec = (Vec3) vecs.get(i);
				g.fillRect((int)(vec.x - (vec.x - W/2)*vec.z/MAX_DIST), (int) (vec.y - (vec.y - H/2)*vec.z/MAX_DIST), 5, 5);
			}
			Vec3.rotateArray(vecs, "x", new Vec3(W/2,H/2,0), 90);
		}
	}
}
