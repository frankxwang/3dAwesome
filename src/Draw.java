import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Draw {
	static ArrayList vecs = new ArrayList<Vec3>();
	private void DoStuff() {
		JFrame frame = new JFrame();
		
		FrameDraw panel = new FrameDraw();
		panel.setSize(new Dimension(1000, 500));
		
		frame.setPreferredSize(new Dimension(1000, 500));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 2));
		frame.add(panel);
		
		(panel).paintComponent(panel.getGraphics());
		
		frame.pack();
	}
	Draw() {
		vecs.add(new Vec3(500, 20, 0));
		((Vec3)vecs.get(0)).Rotate("z", new Vec3(500, 250, 0), -90);
		System.out.println(((Vec3)vecs.get(0)).x + " " + ((Vec3)vecs.get(0)).y + " " + ((Vec3)vecs.get(0)).z);
		DoStuff();
	}
	class FrameDraw extends JPanel {
		protected void paintComponent(Graphics g) {
			for (int i = 0; i < vecs.size(); i++) {
				Vec3 vec = (Vec3) vecs.get(i);
				g.fillRect((int) vec.x, (int) vec.y, 5, 5);
			}
		}
	}
}
