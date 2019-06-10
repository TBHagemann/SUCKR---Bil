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
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

public class MovementController implements IMovementController{
	
	
	private UnregulatedMotor trunk, collector;	
	private double diameter = 3;
	private double width = 0.3; 
	
	private File PKMON = new File("sjovtklip.wav");

	public MovementController(){
		
		/*
		wheel1 = new UnregulatedMotor(MotorPort.B);
		wheel2 = new UnregulatedMotor(MotorPort.C);
		*/
		
		/*
		trunk = new EV3MediumRegulatedMotor(MotorPort.A);
		collector = new EV3MediumRegulatedMotor(MotorPort.D);
		*/
		
	}
	
	public void driveCar(int distance) {
	
		DifferentialPilot difpilot = new DifferentialPilot(diameter, width, Motor.C, Motor.B );
		difpilot.travel(distance);
		
		Motor.B.close();
		Motor.C.close();
		
	}
	
	
	public void frontCollectorOn() {
		UnregulatedMotor collector = new UnregulatedMotor(MotorPort.D);
		collector.setPower(75);
		collector.backward();
	}
	
	public void frontCollectorOff() {
		collector.stop();
		Motor.D.close();
	}
	
	public void openTrunk() {
		//trunk.backward();
		Delay.msDelay(1000);	
	}
	
	public void closeTrunk() {
		//trunk.forward();
		Delay.msDelay(1000);
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
	
	
	//nytn req
	public void measureMovements(double diameter, double width) {
		DifferentialPilot difpilot = new DifferentialPilot(diameter, width, Motor.C, Motor.B );
		difpilot.travel(100);
		difpilot.rotate(1080);
		Motor.B.close();
		Motor.C.close();
	}
} 


