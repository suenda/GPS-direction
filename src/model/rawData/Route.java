package model.rawData;

import model.rawData.Station;
import utility.Util;

public class Route {

	private String code;
	private String ligne;
	private String type;
	private Station sPoint;
	private Station ePoint;
	private double distance;
	private float time;

	
	public Route(String code, String type, String lnName, Station sPoint, Station ePoint) {
		this.code = code;
		this.ligne = lnName;
		this.type = type;
		this.sPoint = sPoint;
		this.ePoint = ePoint;
//		distance = Util.distance(sPoint, ePoint);
		time = (float) distance/60;
	}

	public Route(String code, Station sPoint, Station ePoint) {
		this.code = code;
		this.type = "";
		this.sPoint = sPoint;
		this.ePoint = ePoint;
		distance = Util.distance(sPoint, ePoint);
		time = (float) distance/50;
	}

	public Route(Station sPoint, Station ePoint) {
		this.code = "";
		this.type = "";
		this.sPoint = sPoint;
		this.ePoint = ePoint;
		distance = Util.distance(sPoint, ePoint);
		time = (float) distance/60;
	}
	
	public Route() {
		
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getLnName() {
		return ligne;
	}

	public void setLnName(String lnName) {
		this.ligne = lnName;
	}

	public Station getsPoint() {
		return sPoint;
	}

	public void setsPoint(Station sPoint) {
		this.sPoint = sPoint;
	}

	public Station getePoint() {
		return ePoint;
	}

	public void setePoint(Station ePoint) {
		this.ePoint = ePoint;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setTime(float time) {
		this.time = time;
	}
	
	public float getTime() {
		return time;
	}
	


	@Override
	public String toString() {
		return "Route [code=" + code + ", type=" + type + ", Ligne=" + ligne + "]";
	}
	
	
	


	
	
}
