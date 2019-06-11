package Socket;

import java.util.Scanner;

import Algorithm.Move;
import Algorithm.Node;

public class SocketImpl {
	
	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		Scanner in = new Scanner(System.in);
		try {
			client.stopConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		client.startConnection("127.0.0.1", 6666);
		
		Move move = new Move();
		move.setAngle(20.0);
		move.setDistance(40.0);
		
		System.out.println(client.sendObject(move));
		/*
		String input = "Test";
		String output;
		while(!(input.equals(".")) ) {
			output = client.sendMessage(input);
			System.out.println(output);
			input = in.nextLine();
		}
		*/
		//client.sendMessage(input);
		client.stopConnection();
		
	}

}
