package main;

import controllers.ControllerRegistry;
import controllers.interfaces.IMovementController;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		
		movementTest();
		
	}
	
	public static void movementTest() {
		IMovementController mc = ControllerRegistry.getMovementController();
		
		mc.frontCollectorOn();

		mc.driveCar(100);
		
		mc.turnRight(180);
		
//		mc.driveCar(100);
		
		mc.frontCollectorOff();
	}
}
