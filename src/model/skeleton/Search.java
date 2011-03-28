package model.skeleton;

import java.util.List;

import model.rawData.Route;
import model.rawData.RouteQueue;
import model.rawData.Station;

public interface Search {
		
	public List<RouteQueue> searchPath(Station s, Station d, List<Route> rt);

	
	
}
