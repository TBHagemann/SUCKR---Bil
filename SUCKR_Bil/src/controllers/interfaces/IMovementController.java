package controllers.interfaces;

public interface IMovementController {
	
	public void initializeMovePilot(); 
	public void driveCar(int distance);
	public void driveCarSlowly(int distance);
	public void driveCarBackwards(int distance);
	public void driveCarBackwardsSlowly(int distance);
	public void turnLeft(int angle);
	public void turnRight(int angle);
	public void frontCollectorOn();
	public void frontCollectorOff();
	public void openTrunk();
	public void closeTrunk();
//	public void parallelPark();
	public void playSound();
	public boolean isFrontCollectorOn();
	public void reverseCollector();
	public void twerk();

}
