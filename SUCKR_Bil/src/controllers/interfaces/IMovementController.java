package controllers.interfaces;

public interface IMovementController {
	
	public void driveCar(int distance);
	public void turnLeft(int angle);
	public void turnRight(int angle);
	public void frontCollectorOn();
	public void frontCollectorOff();
	public void openTrunk();
	public void closeTrunk();
//	public void parallelPark();
	public void playSound();
//	public void twerk();

}
