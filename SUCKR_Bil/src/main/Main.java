package main;

import java.util.ArrayList;

import Socket.SocketServer;
import algorithm.Move;
import controllers.ControllerRegistry;
import controllers.impl.SocketController;
import controllers.interfaces.IMovementController;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		//SocketController socketController = new SocketController();
		//socketController.start(6666);
		
		connectionTest();
//		movementTest();
		
	}

	public static void movementTest() {
		IMovementController mc = ControllerRegistry.getMovementController();

		mc.turnLeft(450);
		
	}

	public static void connectionTest() {
		SocketServer server = new SocketServer();
		server.start(6666);
		System.out.println("Connection");

		boolean driving = true;
		ArrayList<Move> nextMoves;
		IMovementController mc = ControllerRegistry.getMovementController();

		mc.frontCollectorOn();
		Move lastRecievedMove = new Move();

		while(driving) {
			nextMoves = server.recieveMoves();


			for(Move nextMove : nextMoves) {
				if(nextMove == null) { 

					continue;
				}
				//if(lastRecievedMove.equals(nextMove))
				if(nextMove.getAngle() > 0) {
					nextMove.setAngle(nextMove.getAngle());
				} else {
					nextMove.setAngle(nextMove.getAngle());
				}
				mc.turnLeft((int) nextMove.getAngle());
				
				
				if(nextMove.isDriveSlowly()) {
					mc.driveCarSlowly((int) (nextMove.getDistance()));
				}
				else {
					mc.driveCar((int) (nextMove.getDistance()));
				}
				
				System.out.println("Angle: " + nextMove.getAngle());
				System.out.println("Distance: " + nextMove.getDistance());

				server.respond("okiedokie");
				nextMove = null;

			}
			
			
		}

		mc.frontCollectorOff();

		mc.openTrunk();
		Delay.msDelay(4000);
		mc.closeTrunk();
	}

}
