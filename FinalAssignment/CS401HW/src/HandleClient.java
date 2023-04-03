import java.io.*;
import java.net.*;
import java.util.*;

/** HandleClient class provides the running method that communicates with each client **/

public class HandleClient implements Runnable {

	//socket instance
	Socket socket;

	//constructor
	HandleClient(Socket socket) {
		this.socket = socket;
	}

	/** Running method **/

	public void run() {

		//put in try block to catch any exceptions
		try {

			//dictionary.seeDictionary();

			//printWriter to print out to client
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//display number of clients
			System.out.println("Number of Clients: " + TCPServer.clientCount);

			//read in client input
			//combined refers to user name and password in  one string combined
			String combined = in .readLine().trim();

			//client log-in data
			Credentials clientCredentials = new Credentials();

			//determine if matches with credentials
			if (clientCredentials.determineMatch(combined))
				out.println("true");
			else
				out.println("Invalid entry please try again.");

			//read in client order
			double charge = 0;
			String sOrder = in .readLine().trim();

			char[] order = new char[sOrder.length()];
			order = sOrder.toCharArray();

			//determine client price
			for (int i = 0; i < order.length; i++) {

				switch (order[i]) {
					case (','):
						continue;
					case ('1'):
						charge += 14;
						break;
					case ('2'):
						charge += 10;
						break;
					case ('3'):
						charge += 18;
						break;
					case ('4'):
						charge += 8;
						break;
					case ('5'):
						charge += 10;
						break;
					case ('a'):
						charge += 3;
						break;
					case ('b'):
						charge += 3;
						break;
					case ('c'):
						charge += 3;
						break;
					case ('d'):
						charge += 3;
						break;
					case ('e'):
						charge += 3;
						break;
					case (6):
						System.out.println("Please try again: invalid entry.");
				}

			}

			out.println(charge);

			//send card credentials to UDP Server
			String confirmation = sendToUDPServer( in .readLine().trim());

			out.println(confirmation);

		} catch (Exception e) {}
	}

	public static String sendToUDPServer(String encryptedData) {
		//TCP Server is like the client to the UDP Server 
		Scanner input;
		byte[] buf = new byte[65535];
		DatagramPacket sndPacket, rcvPacket;
		String rcvMessage, sndMessage;
		int serverPort = 5000;

		try {
			DatagramSocket clientSocket = new DatagramSocket(); //create a socket to send to and receive messages from the server 
			InetAddress serverAddress = InetAddress.getByName("localhost"); //Determines the IP address of a host, given the host's name. 
			sndMessage = encryptedData; //Returns a string representation of the double argument. 
			sndPacket = new DatagramPacket(sndMessage.getBytes(), sndMessage.getBytes().length, serverAddress, serverPort); //Constructs a datagram packet for sending packets of length length to the specified port number on the specified host. 
			clientSocket.send(sndPacket); //Sends a datagram packet from this socket.
			rcvPacket = new DatagramPacket(buf, buf.length); //Constructs a DatagramPacket for receiving packets of length length. 
			clientSocket.receive(rcvPacket); //Receives a datagram packet from this socket.
			rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength());
			/* 	rcvPacket.getData() returns the entire buffer buf (which we initially created of size 1000)
																						includes both the server information and leftover bytes
																					   	rcvPacket.getLength() returns the actual size of data sent by the server
																					   	rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength()); will return 
				  						  											   	only the server response and removes the extra bytes */
			String confirmation = rcvMessage;
			//Returns whether the transaction has been completed
			System.out.println(rcvMessage);

			return confirmation;

		} catch (Exception E) {
			System.out.println("Sending to Server 2:" + E);
		}

		return "Error in sending to Server 2.";
	}
}