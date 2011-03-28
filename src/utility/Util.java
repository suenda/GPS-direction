package utility;

import java.awt.geom.Line2D;

import java.io.*;
import java.text.DecimalFormat;

import model.rawData.Route;
import model.rawData.Station;





public class Util {
	
	public static Line2D lnRoute(Route r) {
		Station s = r.getsPoint();
		Station e = r.getePoint();
		
		Line2D line = new Line2D.Double(s.getCoordX(), 
										s.getCoordY(), 
										e.getCoordX(), 
										e.getCoordY());
		
		return line;
	}
	
	public static Line2D lnAPoint(Station p) {
		int x = p.getCoordX();
		int y = p.getCoordY();
		
		Line2D line = new Line2D.Double(x-2, y-2, x+2, y+2);

		
		return line;
		
	}
	
	public static Line2D lnBPoint(Station p) {
		int x = p.getCoordX();
		int y = p.getCoordY();
		
		Line2D line = new Line2D.Double(x+2, y-2, x-2, y+2);
		
		return line;
		
	}
	
	
	
	public static double distance(Station x, Station y) {
		double xx = (x.getCoordX()-y.getCoordX());
		double yy = (x.getCoordY()-y.getCoordY());
		double result = Math.sqrt((xx*xx + yy*yy));
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(result));
	}
	
	
	
}
