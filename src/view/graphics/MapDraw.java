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


public class MapDraw extends ApplicationFrame {
	
	private ArrayList<Line2D> lines = new ArrayList<Line2D>();
	Color color = Color.RED;
	
	public MapDraw() {
		
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
	
	public void drawMap(Map mp, Color c) {

		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		Dimension d = getSize();
		Route r;
		Station s,e;
		int x1,x2,y1,y2;
		
		int wfact = (int) d.getWidth()/45;
		int hfact = (int) d.getHeight()/30;
		
		System.out.println(wfact + "     " + hfact);
		
		g2.setColor(c);
		
		ArrayList<Ligne> lns = (ArrayList<Ligne>) mp.getLignes();
		for(int i =0; i<lns.size(); i++) {
			
			if(lns.get(i).getType().equalsIgnoreCase("TRAIN")) {
				c = Color.red;
			} else if(lns.get(i).getType().equalsIgnoreCase("BUS")) {
				c = Color.blue;
			} else if(lns.get(i).getType().equalsIgnoreCase("AUTO")) {
				c = Color.black;
			}
 
			
			drawLigne(lns.get(i), c);
			
/**			ArrayList<Route> rts = (ArrayList<Route>) mp.getAllMapRoutes();
			
			for(int j=0; j<rts.size(); j++) {
				r = rts.get(j);
	
				g2.draw(Util.lnRoute(r));

				g2.drawString(r.getsPoint().getName(), r.getsPoint().getCoordX(), r.getsPoint().getCoordY());
				g2.drawString(r.getePoint().getName(), r.getePoint().getCoordX(), r.getePoint().getCoordY());
				g2.draw(Util.lnAPoint(r.getsPoint()));
				g2.draw(Util.lnBPoint(r.getsPoint()));
				g2.draw(Util.lnAPoint(r.getePoint()));
				g2.draw(Util.lnBPoint(r.getePoint()));
	
			}		**/	

		}
	}
	
	public void drawLigne(Ligne ln, Color c) {
		ArrayList<Route> rts = (ArrayList<Route>) ln.getRoutes();
		Route r;

		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(c);
		
		for(int j=0; j<rts.size(); j++) {
			r = rts.get(j);

			g2.draw(Util.lnRoute(r));

			g2.drawString(r.getsPoint().getName(), r.getsPoint().getCoordX(), r.getsPoint().getCoordY());
			g2.drawString(r.getePoint().getName(), r.getePoint().getCoordX(), r.getePoint().getCoordY());
			g2.draw(Util.lnAPoint(r.getsPoint()));
			g2.draw(Util.lnBPoint(r.getsPoint()));
			g2.draw(Util.lnAPoint(r.getePoint()));
			g2.draw(Util.lnBPoint(r.getePoint()));
			
		}
		
	}
	
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
	
	public void drawRoutes(RouteQueue rQ, Color color) {
		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(color);

		while(!rQ.isEmpty()) {
			g2.draw(Util.lnRoute(rQ.poll()));
		}
		

	}
	
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		Iterator<Line2D> l = lines.iterator();
		
		while(l.hasNext()) {
			g2.draw(l.next());
		}
	}

	
}
