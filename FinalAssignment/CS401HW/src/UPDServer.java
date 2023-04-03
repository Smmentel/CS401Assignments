import java.io.FileNotFoundException;
import java.net.*;
/** UDP Server decrypts client's card data to determine if valid entry, communicates through TCP Server*/

public class UPDServer {

	public static void main(String[] args) {
		int clientPort, serverPort = 5000;
		byte[] buf = new byte[65535]; //it's better to keep the buffer size with in the MTU limit i.e. 1472 bytes
		String rcvMessage, sndMessage;
		DatagramPacket sndPacket, rcvPacket;
		InetAddress clientAddress;
		String encryptedData, sendToClient;

		try {

			DatagramSocket serverSocket = new DatagramSocket(serverPort); // create a server socket to send and receive messages
			while (true) {
				System.out.println("Waiting for incoming connections");

				rcvPacket = new DatagramPacket(buf, buf.length); //initialize the receive packet such that incoming data is stored in buf
				serverSocket.receive(rcvPacket); //wait for incoming data from client
				clientPort = rcvPacket.getPort(); //retrieve client's port number
				clientAddress = rcvPacket.getAddress(); //retrieve client's ip address
				System.out.println("Request from client with IP address " + clientAddress + " and Port number " + clientPort);
				rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength());
				/* 	rcvPacket.getData() returns the entire buffer buf (which we initially created of size 1000)
																								includes both the server information and leftover bytes
																							   	rcvPacket.getLength() returns the actual size of data sent by the server
																							   	rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength()); will return 
																							   	only the server response and removes the extra bytes */
				//alternative to the above: 
				//rcvMessage = new String(rcvPacket.getData()).trim();	
				encryptedData = rcvMessage; //returns a string object holding the encrypted data
				sendToClient = decryptConfirm(encryptedData);

				sndMessage = sendToClient; //Returns a string representation of the double argument. 
				sndPacket = new DatagramPacket(sndMessage.getBytes(), sndMessage.getBytes().length, clientAddress, clientPort); //create a packet with the computed area and address it with the client's port number and IP address
				serverSocket.send(sndPacket); //send the packet to the client (client details are in the sndPacket) through the serverSocket
				System.out.println("Response sent to client with IP address " + clientAddress + " and Port number " + clientPort);

				buf = new byte[1000]; //reset the buf and continue waiting for new incoming requests

			}

		} catch (Exception E) {
			E.printStackTrace();
		}

	}

	public static String decryptConfirm(String secureData) throws FileNotFoundException {
		String insecureData = " ";

		for (int i = 0; i < secureData.length(); i++) {
			insecureData += (char)(secureData.charAt(i) - 2);
		}

		CardCredentials cards = new CardCredentials();
		//determine if matches with credentials
		if (cards.determineMatch(insecureData))
			return "Thank you for  your payment! Transaction complete. Your order has been submitted. Food will be ready in 10-15min.";
		else return "Invalid card entry please try again.";
	}
}