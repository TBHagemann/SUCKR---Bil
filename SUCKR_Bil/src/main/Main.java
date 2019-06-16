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
		//movementTest();
		
	}
	
	public static void movementTest() {
		IMovementController mc = ControllerRegistry.getMovementController();
		
		mc.frontCollectorOn();

		//mc.driveCar(100);
		mc.turnLeft(1080);
		
		mc.frontCollectorOff();
	}
	
	public static void connectionTest() {
		SocketServer server = new SocketServer();
		server.start(6666);
		System.out.println("Connection");
		
		boolean driving = true;
		Move nextMove;
		IMovementController mc = ControllerRegistry.getMovementController();
		
		mc.frontCollectorOn();
		Move lastRecievedMove = new Move();
		
		while(driving) {
			nextMove = server.recieveMove();
			
			if(nextMove == null) { 
				
				continue;
			}
			
			//if(lastRecievedMove.equals(nextMove))
			if(nextMove.getAngle() > 0) {
				nextMove.setAngle(nextMove.getAngle() + 15);
			} else {
				nextMove.setAngle(nextMove.getAngle() - 15);
			}
			mc.turnLeft((int) nextMove.getAngle());
			
			mc.driveCar((int) (nextMove.getDistance()));
			System.out.println("Angle: " + nextMove.getAngle());
			System.out.println("Distance: " + nextMove.getDistance());
			
			server.respond("okiedokie");
			nextMove = null;
		}
		
		mc.frontCollectorOff();
		
		mc.openTrunk();
		Delay.msDelay(4000);
		mc.closeTrunk();
	}
}
