package controllers.interfaces;

public interface IMovementController {
	
	public void driveCar(int distance);
	public void frontCollectorOn();
	public void frontCollectorOff();
	public void openTrunk();
	public void closeTrunk();
//	public void parallelPark();
	public void playSound();
//	public void twerk();
	public void measureMovements(double diameter, double width);

}
