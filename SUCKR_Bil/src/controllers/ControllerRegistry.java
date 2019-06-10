package controllers;

import controllers.impl.MovementController;
import controllers.interfaces.IMovementController;

public class ControllerRegistry {

	private static IMovementController movementController;
	
	protected ControllerRegistry() {
		// Needs to be here to prevent instantiation.
	}	
	
	public static synchronized IMovementController getMovementController() {
		if (movementController == null) movementController = new MovementController();
		return movementController;
	}
	
}
