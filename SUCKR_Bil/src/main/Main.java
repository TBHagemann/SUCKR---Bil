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
		
		//connectionTest();
		movementTest();
		
	}

	public static void movementTest() {
		IMovementController mc = ControllerRegistry.getMovementController();

		mc.twerk();
		
	}

	public static void connectionTest() {
		SocketServer server = new SocketServer();
		server.start(6666);
		System.out.println("Connection");

		boolean driving = true;
		ArrayList<Move> nextMoves;
		IMovementController mc = ControllerRegistry.getMovementController();

		mc.frontCollectorOn();
		ArrayList<Move> lastRecievedMove = new ArrayList<Move>();

		int count = 0;
		
		while(driving) {
			nextMoves = server.recieveMoves();
			
			count++;

			if(nextMoves.equals(lastRecievedMove)) {
				continue;
			}
			lastRecievedMove = nextMoves;

			for(Move nextMove : nextMoves) {
				if(!mc.isFrontCollectorOn()) {
					mc.frontCollectorOn();
				}
				
				if(nextMove == null) { 

					continue;
				}
				//if(lastRecievedMove.equals(nextMove))
				
				if(nextMove.getAngle() > 0) {
					nextMove.setAngle(nextMove.getAngle() + 10);
				} else {
					nextMove.setAngle(nextMove.getAngle() - 10);
				}
			
				mc.turnLeft((int) nextMove.getAngle());
				
				
				if(nextMove.isTwerk()){
					if(nextMove.isDriveSlowly()) {
						mc.driveCarSlowly((int)nextMove.getDistance());
						mc.twerk();
						server.respond("JATAKCHEF");
						Delay.msDelay(500);
					}
					else {
						mc.driveCar((int)nextMove.getDistance());
						mc.twerk();
					}
				}
				else if(nextMove.isDriveSlowly()) {
					mc.driveCarSlowly((int) (nextMove.getDistance()));
				}
				else {
					mc.driveCar((int) (nextMove.getDistance()));
				}
				
				System.out.println("Angle: " + nextMove.getAngle());
				System.out.println("Distance: " + nextMove.getDistance());

				
				nextMove = null;
				
				if(count == 2) {
					mc.reverseCollector();
					count = 0;
				}
				
			}
			server.respond("okiedokie");
			
			
		}

		System.out.println("FRONTCOLLECTOR OFF");
		mc.frontCollectorOff();

		mc.openTrunk();
		Delay.msDelay(4000);
		mc.closeTrunk();
	}

}
