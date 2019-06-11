package Socket;

import java.util.Scanner;

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
		String input = "Test";
		String output;
		while(!(input.equals(".")) ) {
			output = client.sendMessage(input);
			System.out.println(output);
			input = in.nextLine();
		}
		client.sendMessage(input);
		client.stopConnection();
		
	}

}
