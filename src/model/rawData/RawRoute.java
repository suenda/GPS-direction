package model.rawData;

public class RawRoute {
	
	private String code;
	private String type;
	private String ligne;
	private String sStation;
	private String eStation;
	
	public RawRoute(String code, String type, String lnName, String sStation, String eStation) {
		this.code = code;
		this.type = type;
		this.ligne = lnName;
		this.sStation = sStation;
		this.eStation = eStation;
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
	

	public String getLigne() {
		return ligne;
	}

	public void setLigne(String lnName) {
		this.ligne = lnName;
	}

	public String getsStation() {
		return sStation;
	}

	public void setsStation(String sStation) {
		this.sStation = sStation;
	}

	public String geteStation() {
		return eStation;
	}

	public void seteStation(String eStation) {
		this.eStation = eStation;
	}
	
	

	
}
