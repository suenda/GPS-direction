package model.rawData;

import java.util.ArrayList;
import java.util.Iterator;


public class RouteQueue {
	
	ArrayList<Route> arrayqueue = new ArrayList<Route>();
	
	public RouteQueue() {
		
	}
	
	public void clear() {
		arrayqueue.clear();		
	}


	public boolean contains(Route r) {
		if(arrayqueue.contains(r)) return true;
		else return false;
	}


	public boolean isEmpty() {
		if(arrayqueue.isEmpty()) return true;
		else return false;
	}

	public Iterator<Route> iterator() {
		return arrayqueue.iterator();
	}

	public boolean remove(Route r) {
		if(arrayqueue.contains(r)) {
			arrayqueue.remove(r);
			return true;
		} else return false;
	}


	public int size() {
		return arrayqueue.size();
	}

	public Route[] toArray() {
		Route[] r = new Route[arrayqueue.size()];
		
		for(int i = 0; i<arrayqueue.size(); i++) {
			r[i] = arrayqueue.get(i);
		}
		return r;
	}


	public boolean add(Route r) {
		
		if(arrayqueue.add(r)) return true;
		else return false;
	}


	public Route peek() {
		return arrayqueue.get(0);
	}

	public Route poll() {
		
		Route r = null;

		if(arrayqueue.size()>0) {
			r = arrayqueue.get(0);
			arrayqueue.remove(0);
		}
		
		return r;
	}



	
}
