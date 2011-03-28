package model.skeleton;

import java.util.List;
import model.rawData.Route;
import model.rawData.Station;

public interface Ligne {
	
	public void addRoute(Route r);
	
	public String getName();

	public String getType();

	public List<Route> getRoutes();
	
	public List<Station> getStations();
		
}
