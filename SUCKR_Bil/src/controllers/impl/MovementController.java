package controllers.impl;

import java.io.File;

import controllers.ControllerRegistry;
import controllers.interfaces.IMovementController;
import lejos.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class MovementController implements IMovementController{


	NXTRegulatedMotor trunk;
	private NXTRegulatedMotor collector;	
	private double diameter = 5.6;
	private MovePilot movePilot;
	private Chassis chassis;
	private Wheel wheel1, wheel2; 

	private File PKMON = new File("sjovtklip.wav");

	public MovementController(){
		initializeMovePilot();
	}

	public void initializeMovePilot() {
		this.wheel1 = WheeledChassis.modelWheel(Motor.C, diameter).offset(15);
		this.wheel2 = WheeledChassis.modelWheel(Motor.B, diameter).offset(-15);
		this.trunk = Motor.A;
		this.collector = Motor.D;
		chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
		movePilot = new MovePilot(chassis);
	}

	public void driveCar(int distance) {


		//double trueDistance = distance * 0.33;

		//movePilot.setLinearSpeed(10);

		movePilot.travel(-distance);
		System.out.println("speed: " + movePilot.getLinearSpeed());
		movePilot.stop();

	}

	public void driveCarSlowly(int distance) {
		movePilot.setLinearSpeed(10);
		movePilot.travel(-distance);
		movePilot.setLinearSpeed(30);
		movePilot.stop();
	}

	public void driveCarBackwards(int distance) {
		driveCar(-distance);
	}

	public void driveCarBackwardsSlowly(int distance) {
		driveCarSlowly(-distance);
	}

	public void turnLeft(int angle) {

		if(angle<0) {
			turnRight(Math.abs(angle));
			return;
		}

		double trueAngle = angle * 0.575;

		movePilot.setAngularSpeed(50);
		movePilot.rotate(-trueAngle);
		movePilot.stop();
	}

	public void turnRight(int angle) {

		if(angle<0) {
			turnLeft(Math.abs(angle));
			return;
		}

		double trueAngle = angle * 0.575;

		movePilot.setAngularSpeed(50);
		movePilot.rotate(trueAngle);
		movePilot.stop();
	}


	public void frontCollectorOn() {
		collector.setSpeed(720);
		collector.backward();
	}

	public void frontCollectorOff() {
		collector.stop();
	}

	public void openTrunk() {
		trunk.backward();
	}

	public void closeTrunk() {
		trunk.forward();
		Delay.msDelay(2000);
		trunk.stop();
	}

	/*
	public void parallelPark() {
		wheel2.stop();
		wheel1.stop();
		driveCar(500, 100);
		turnLeft(180);
		driveBackwards(500, 100);
		openTrunk();
	}
	 */

	
	public void playSound() {
		Sound.playSample(PKMON);
	}
	
	public boolean isFrontCollectorOn() {
		return collector.isMoving();
	}
	
	public void reverseCollector() {
		collector.stop();
		collector.setSpeed(720);
		collector.forward();
		
		Delay.msDelay(2000);
		
		collector.backward();
	}


	public void twerk() {

		Motor.A.close();
		Motor.B.close();
		Motor.C.close();
		Motor.D.close();

		UnregulatedMotor wheel1UR, wheel2UR, trunkUR, collectorUR; 

		wheel1UR = new UnregulatedMotor(MotorPort.B);
		wheel2UR = new UnregulatedMotor(MotorPort.C);
		trunkUR = new UnregulatedMotor(MotorPort.A);
		collectorUR = new UnregulatedMotor(MotorPort.D);

		wheel1UR.setPower(92);
		wheel2UR.setPower(100);
		wheel1UR.stop();
		wheel2UR.stop();
		
		trunkUR.setPower(100);
		collectorUR.setPower(60);

		for(int j = 0; j < 2; j++) {
			trunkUR.backward();
			Delay.msDelay(4000);
			
			for(int i = 0; i < 20; i++) {
				if((i % 2) == 0) {
					wheel1UR.backward();
					wheel2UR.backward();
					Delay.msDelay(120);
				} else {
					wheel1UR.forward();
					wheel2UR.forward();
					Delay.msDelay(135);
				}
			}
			
			wheel1UR.stop();
			wheel2UR.stop();
			
			trunkUR.forward();
			Delay.msDelay(4000);
		}		
		
		wheel1UR.close();
		wheel2UR.close();
		trunkUR.close();
		collectorUR.close();
		
		initializeMovePilot();
		frontCollectorOn();
		
	}
}
