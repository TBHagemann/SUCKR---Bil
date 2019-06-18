package algorithm;

public class Move implements java.io.Serializable {
	
	double distance;
	//The angle to turn. Positive number means counterclockwise move
	double angle;
	boolean driveSlowly;
	
	public Move() {
	}
	
	public Move(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
		this.driveSlowly = false;
	}
	
	public Move(double distance, double angle, boolean driveSlowly) {
		this.distance = distance;
		this.angle = angle;
		this.driveSlowly = driveSlowly;
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

	public boolean isDriveSlowly() {
		return driveSlowly;
	}

	public void setDriveSlowly(boolean driveSlowly) {
		this.driveSlowly = driveSlowly;
	}
	
}
