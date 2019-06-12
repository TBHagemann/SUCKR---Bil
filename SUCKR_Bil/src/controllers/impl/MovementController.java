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
	private double diameter = 3;
	private double width = 0.3; 
	private MovePilot movePilot;
	private Chassis chassis;
	private Wheel wheel1, wheel2; 
	
	private File PKMON = new File("sjovtklip.wav");

	public MovementController(){
		
		this.wheel1 = WheeledChassis.modelWheel(Motor.C, diameter).offset(3);
		this.wheel2 = WheeledChassis.modelWheel(Motor.B, diameter).offset(-3);
		this.trunk = Motor.A;
		this.collector = Motor.D;
		chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
		movePilot = new MovePilot(chassis);
		
	}
	
	public void driveCar(int distance) {
		
		
		double trueDistance = distance * 0.67;
		
		movePilot.travel(trueDistance);
		movePilot.stop();
		
	}
	
	public void driveCarBackwards(int distance) {
		driveCar(-distance);
	}
	
	public void turnLeft(int angle) {
		
		double trueAngle = angle * 2;
		
		movePilot.setAngularSpeed(100);
		movePilot.rotate(trueAngle);
		movePilot.stop();
	}
	
	public void turnRight(int angle) {
		
		double trueAngle = angle * 2;
		
		movePilot.setAngularSpeed(100);
		movePilot.rotate(-trueAngle);
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
