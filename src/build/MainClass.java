package build;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MainClass extends Frame {
  public static void main(String[] args) {
    (new MainClass()).setVisible(true);
  }

  public MainClass() {
    super("Shape Sampler");
    setSize(400, 550);
  }

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.fillOval(48, 298, 10, 10);
  }
}
