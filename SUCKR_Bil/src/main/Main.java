package main;

import controllers.SocketController;

public class Main {

	public static void main(String[] args) {
		SocketController socketController = new SocketController();
		socketController.start(6666);
	}
}
