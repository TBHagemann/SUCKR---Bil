package main;

import controllers.ControllerRegistry;
import controllers.interfaces.IMovementController;

public class Main {

	public static void main(String[] args) {
		
		IMovementController mc = ControllerRegistry.getMovementController();
		
		mc.driveCar(100);
		mc.turnLeft(360);
		
	}
}
