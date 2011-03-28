package model.skeleton;

import java.util.List;

import model.rawData.RouteQueue;

public interface SearchFilter {
	
	public List<RouteQueue> filter(List<RouteQueue> rQ);

}
