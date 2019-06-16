
package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import algorithm.Move;
import Socket.SocketServer;

public class SocketController {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private ObjectInputStream objectInputStream;
	
	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

			Move move = (Move) objectInputStream.readObject();
			System.out.println(move.getAngle());
			System.out.println(move.getDistance());
			out.println("Recived");
			/*
			String inputLine;
			while((inputLine = (String) objectInputStream.readObject()) != null) {
				if(".".equals(inputLine)) {
					out.println("good bye");
					break;
				}
				out.println(inputLine);
			}
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.start(6666);
		
	}

}
