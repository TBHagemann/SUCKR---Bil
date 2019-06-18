package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import algorithm.Move;
import lejos.utility.Delay;

public class SocketServer {
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

//			System.out.println(move.getAngle());
//			System.out.println(move.getDistance());
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
			objectInputStream.close();
			clientSocket.close();
			serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void respond(String response) {
		out.println(response);
	}

	public ArrayList<Move> recieveMoves() {
		
		try {
			ArrayList<Move> moves = (ArrayList<Move>) objectInputStream.readObject();
			return moves;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
