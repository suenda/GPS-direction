package view.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import build.StringArt;

import model.rawData.Route;
import model.rawData.Station;
import model.skeleton.Ligne;
import model.skeleton.Map;
import utility.Util;

public class MapGraphics extends Frame {


	private int mNumberOfLines = 25;
	private Color[] mColors = { Color.red, Color.green, Color.blue };
	
	public MapGraphics(){
		super("Map graphics");
		setSize(900, 700);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Dimension d = getSize();
		for (int i = 0; i < mNumberOfLines; i++) {
			double ratio = (double)i / (double)mNumberOfLines;
			Line2D line = new Line2D.Double(0, ratio * d.height, ratio * d.width, d.height);
			g2.setPaint(mColors[i % mColors.length]);
			g2.draw(line);
		}
	}
	
	public static void main(String[] args) {
		new StringArt();

	}
	
	
	
}
