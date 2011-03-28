package model.base;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import model.rawData.Route;
import model.rawData.Station;
import model.skeleton.Ligne;
import model.skeleton.Map;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.apache.xerces.parsers.DOMParser;




import exception.CorruptData;
import exception.InvalidRouteArgument;

public class MapBase implements Map, Runnable {
	
	private List<Ligne> transports;
	private String filePath;
	
	public MapBase(String filepath) {
		this.filePath = filepath;
		
	}
	
	public void loadData() {


			
			try {
				DOMParser parser = new DOMParser();
				parser.parse(filePath);
				Document doc = parser.getDocument();

				// Serialize
				DOMSerializer serializer = new DOMSerializer();
				serializer.serializeNode(doc);
				transports = serializer.cookRoute();
				
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

	}
	
	
	public List<Ligne> getLignes() {

		return transports;
	}
	
	public List<Station> getAllMapStations() {
		List<Station> pp = new ArrayList<Station>();
		
		ArrayList<Ligne> t  = (ArrayList<Ligne>) transports;

		for(int i = 0; i<t.size(); i++) {
			for(int j=0; j<t.get(i).getStations().size(); j++) {
				pp.add(t.get(i).getStations().get(j));
			}
		}
		
		return pp;
		
	}
	
	public List<Route> getAllMapRoutes() {
		ArrayList<Route> rr = new ArrayList<Route>();
		
		ArrayList<Ligne> t  = (ArrayList<Ligne>) transports;

		for(int i = 0; i<t.size(); i++) {
			for(int j=0; j<t.get(i).getRoutes().size(); j++) {
				rr.add(t.get(i).getRoutes().get(j));
			}
		}
		
		
		return rr;
		
	}
	
	public static void main(String[] args) {
		Map mp = new MapBase("/home/surendra/Files/Mapdata.xml");
		ArrayList<Route> st = (ArrayList<Route>) mp.getAllMapRoutes();
		Iterator<Route> itst = st.iterator();
		while(itst.hasNext()) {
			System.out.println(itst.next().toString());
		}
	}

	@Override
	public void run() {
		loadData();
		
	}


	

	




	



}
