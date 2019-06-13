package algorithm;

public class Move implements java.io.Serializable {
	
	double distance;
	//The angle to turn. Positive number means counterclockwise move
	double angle;
	
	public Move() {
	}
	
	public Move(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
