import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Draw {
	static ArrayList vecs = new ArrayList<Vec3>();
	private void DoStuff() {
		JFrame frame = new JFrame();
		FrameDraw panel = new FrameDraw();
	}
	Draw() {
		vecs.add(new Vec3(0, 50, 20));
		DoStuff();
	}
	class FrameDraw extends JFrame {
		void paintComponent(Graphics g) {
			for (int i = 0; i < vecs.size(); i++) {
				Vec3 vec = (Vec3) vecs.get(i);
				g.fillRect((int) vec.x, (int) vec.y, 5, 5);
			}
		}
	}
}
