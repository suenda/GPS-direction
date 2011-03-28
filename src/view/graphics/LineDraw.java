package view.graphics;


import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;

import model.rawData.Route;
import model.rawData.RouteQueue;
import model.rawData.Station;
import model.skeleton.Ligne;
import model.skeleton.Map;



import utility.Util;
import view.graphics.ApplicationFrame;


public class LineDraw extends ApplicationFrame {
	
	private ArrayList<Line2D> lines = new ArrayList<Line2D>();
	Color color = Color.BLACK;
	
	public LineDraw() {
		
		super("Map graphics");
		setSize(900, 700);
		setVisible(true);
			
	}
	
	public void addComponent(Route r) {
		this.lines.add(Util.lnRoute(r));
	}		
	
	public void addComponent(Station p) {

		this.lines.add(Util.lnAPoint(p));
		this.lines.add(Util.lnBPoint(p));
	
	}
	

	
	public void addComponent(ArrayList<Station> p) {
		
		for(int i=0; i<p.size(); i++) {
			this.lines.add(Util.lnAPoint(p.get(i)));
			this.lines.add(Util.lnBPoint(p.get(i)));
		}
	}
	
	public void addComponent(Station[] s) {
		for(int i=0; i<s.length; i++) {
			lines.add(Util.lnAPoint(s[i]));
			lines.add(Util.lnBPoint(s[i]));
		}
	}

	public void addComponent(Route[] r) {
		for(int i=0; i<r.length; i++) {
			this.lines.add(Util.lnRoute(r[i]));
		}
	}
	

	
	public void drawLigne(Ligne t, Color color) {

		ArrayList<Route> r  = (ArrayList<Route>) t.getRoutes();
		ArrayList<Station> p  = (ArrayList<Station>) t.getStations();

		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		
		for(int i=0; i<r.size(); i++) {
			g2.setColor(color);
			g2.draw(Util.lnRoute(r.get(i)));
		}
		
		for(int c=0; c<p.size(); c++) {
			g2.draw(Util.lnAPoint(p.get(c)));
			g2.draw(Util.lnBPoint(p.get(c)));
		}
		
	}
	
/*	public void drawMap(Map mp) {

		ArrayList<Ligne> lns = (ArrayList<Ligne>) mp.getTransports();
		for(int i =0; i<lns.size(); i++) {
			drawLigne(lns.get(i), Color.BLACK);
		}
	}
	*/
	public void drawMap(Map mp) {

		ArrayList<Ligne> lns = (ArrayList<Ligne>) mp.getLignes();
		for(int i =0; i<lns.size(); i++) {
			ArrayList<Route> rts = (ArrayList<Route>) mp.getAllMapRoutes();
			ArrayList<Station> sts = (ArrayList<Station>) mp.getAllMapStations();
			
			for(int j=0; j<rts.size(); j++) {
				addComponent(rts.get(j));
			}
			
			for(int k=0; k<sts.size(); k++) {
				addComponent(sts.get(k));
			}
			
		}
	}
	
	public void drawRouteQueue(RouteQueue rQ) {

		
	} 
	
	public void drawStation(Station s) {
		Graphics g = getGraphics();
		g.drawLine(200, 100, 50, 100);
	}
	
	public void drawRoute(Route r) {
		
	}
	
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		Iterator<Line2D> l = lines.iterator();
		
		while(l.hasNext()) {
			g2.draw(l.next());
			g2.setPaint(color);
		}
	}

	
}
