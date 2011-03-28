package view.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalFrame extends JFrame {

	public GraphicalFrame() {
		super();
		setSize(700,600);
		setVisible(true);
	}
	

	 
	 public void drawany(){
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D)g;
			Line2D line = new Line2D.Double(30, 30, 300, 300);

			g2.draw(line);
	 }
	 
	 public void paint(Graphics g) {
		 
	 }
	 
		public static void main(String[] args) {
			GraphicalFrame frame = new GraphicalFrame();
			frame.drawany();
		}
	
}
