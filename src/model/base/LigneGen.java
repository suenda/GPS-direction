package model.base;

import java.util.ArrayList;
import java.util.List;

import model.rawData.Route;
import model.rawData.Station;
import model.skeleton.Ligne;




import exception.InvalidRouteArgument;

public class LigneGen implements Ligne{
	
	List<Route> routes = new ArrayList<Route>();
	public String type;
	public String name;
	


	public LigneGen(String type, String name){
		this.type = type;
		this.name = name;
	}

	
	public void addRoute(Route r) {

		routes.add(r);

	}
	
	public List<Route> getRoutes() {
	
		return routes;
	}
	
	public List<Station> getStations() {
		List<Station>p = new ArrayList<Station>();
		Route r;

		for(int i=0; i<routes.size(); i++) {
			r = routes.get(i);
			p.add(r.getsPoint());
			p.add(r.getePoint());
		}
		return p;
	}

	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	



}
