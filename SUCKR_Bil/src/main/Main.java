package main;

import controllers.SocketController;
import Socket.SocketServer;
import algorithm.Move;
import controllers.ControllerRegistry;
import controllers.interfaces.IMovementController;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		//SocketController socketController = new SocketController();
		//socketController.start(6666);
		
		connectionTest();
		movementTest();
		
	}
	
	public static void movementTest() {
		IMovementController mc = ControllerRegistry.getMovementController();
		
		mc.frontCollectorOn();

		//mc.driveCar(100);
		mc.turnRight(360);
		
		mc.frontCollectorOff();
	}
	
	public static void connectionTest() {
		SocketServer server = new SocketServer();
		server.start(6666);
		
		boolean driving = true;
		Move nextMove;
		IMovementController mc = ControllerRegistry.getMovementController();
		
		mc.frontCollectorOn();
		
		while(driving) {
			nextMove = server.recieveMove();
			
			if(nextMove == null) {
				break;
			}
			
			mc.turnRight((int) nextMove.getAngle());
			
			mc.driveCar((int) nextMove.getDistance());
			
			server.respond("okiedokie");
		}
		
		mc.frontCollectorOff();
		
		mc.openTrunk();
		Delay.msDelay(4000);
		mc.closeTrunk();
	}
}
