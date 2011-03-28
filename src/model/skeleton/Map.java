package model.skeleton;

import java.util.List;

import model.rawData.Route;
import model.rawData.Station;
import exception.*;

public interface Map {

	public void loadData();
	
	public List<Ligne> getLignes();
	
	public List<Station> getAllMapStations();
	
	public List<Route> getAllMapRoutes();
	
}
