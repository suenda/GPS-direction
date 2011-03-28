package exception;

import model.rawData.Route;

public class InvalidRouteArgument extends Throwable {

	public InvalidRouteArgument (Route r, String type) {
		super(type + "expected while route " + r.getCode() + " has type " + r.getType());
	}
}
