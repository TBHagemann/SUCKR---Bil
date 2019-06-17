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
import lejos.robotics.navigation.DifferentialPilot;
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
		movePilot.stop();
		
	}
	
	public void driveCarBackwards(int distance) {
		driveCar(distance);
	}
	
	public void turnLeft(int angle) {
		
		if(angle < 0) {
			turnRight(Math.abs(angle));
			return;
		}
		
		double trueAngle = angle * 0.625;
		
		movePilot.setAngularSpeed(50);
		movePilot.rotate(-trueAngle);
		movePilot.stop();
	}
	
	public void turnRight(int angle) {
		
		if(angle < 0) {
			turnLeft(Math.abs(angle));
			return;
		}
		
		double trueAngle = angle * 0.58;
		
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
		Delay.msDelay(1000);	
		trunk.stop();
	}
	
	public void closeTrunk() {
		trunk.forward();
		Delay.msDelay(1000);
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
	
	/*
	public void twerk() {
		for(int i = 1; i < 40; i++) {
			if((i % 2) == 0) {
				driveBackwards(150, 100);
			} else {
				driveCar(120, 100);
			}
		}
	}
	*/

}
