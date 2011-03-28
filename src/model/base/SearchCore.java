package model.base;

import java.util.ArrayList;
import java.util.List;

import model.rawData.Route;
import model.rawData.RouteQueue;
import model.rawData.Station;
import model.skeleton.Search;

public class SearchCore implements Search {

	public SearchCore() {
		
	}
	
	public static Search getInstance() {
		return new SearchCore();
	}

	@Override
	public List<RouteQueue> searchPath(Station s, Station d, List<Route> rt) {
		
		ArrayList<Route> rtat = (ArrayList<Route>) rt;
		
		List<RouteQueue> rQlst = new ArrayList<RouteQueue>();
		
		RouteQueue rQ = null;
		
		for(int i=0; i<rtat.size(); i++) {
			if(rtat.get(i).getType().equalsIgnoreCase("BUS")) {
				rQ.add(rtat.get(i));
			}
		}
		
		rQlst.add(rQ);
		
		return rQlst;
	}
	


}
