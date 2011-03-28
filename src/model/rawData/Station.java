package model.rawData;

public class Station {

	private String name;
	private int coordX;
	private int coordY;
	
	
	public Station(String name, int x, int y) {	
		this.name = name;
		this.coordX = x;
		this.coordY = y;
	}
	
	public Station(int x, int y) {	
		this.name = "";
		this.coordX = x;
		this.coordY = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", coordX=" + coordX + ", coordY="
				+ coordY + "]";
	}
	
	








	
	
	
	
	
	
	
	
	
}
