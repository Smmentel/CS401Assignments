/* Description: The client prompts the user to enter a name, encrypts the name
and forwards it the server to decrypt, and compute the corresponding SSN; 
the client then displays the computed SSN that it receives from the server
 * Author: Poonam Dharam
 * Version: 1.0
 * */
import java.net.*;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) 
	
	{
		Scanner input;
		
		byte[] buf = new byte[65535];
		
		DatagramPacket sndPacket, rcvPacket;
		
		String rcvMessage, sndMessage, cipherTextRecieve, plainTextRecieve;
		
		int serverPort = 3000;
		
		String first, last;
		
		try 
		{
			input = new Scanner(System.in);
			
			//Client reads first name and last name from the user and store them in two separate variables first and last.
			System.out.print("Enter first name: "); 
			first = input.next();
			first.toUpperCase(); // ensure server receives only capital letter
			
			System.out.print("Enter last name:  ");
			last = input.next();
			last.toUpperCase(); // ensure server receives only capital letter

			
			String cipherTextSend = encryptData(first, last); //encrypt first/last name
			
		try (DatagramSocket clientSocket = new DatagramSocket()) {
			InetAddress serverAddress = InetAddress.getByName("localhost"); //Determines the IP address of a host, given the host's name. 
			sndMessage = cipherTextSend; 
			sndPacket = new DatagramPacket(sndMessage.getBytes(), sndMessage.getBytes().length, serverAddress, serverPort);//Constructs a datagram packet for sending packets of length length to the specified port number on the specified host. 
			clientSocket.send(sndPacket);//Sends a datagram packet from this socket.
					
			rcvPacket = new DatagramPacket(buf, buf.length);//Constructs a DatagramPacket for receiving packets of length length. 
			clientSocket.receive(rcvPacket);//Receives a datagram packet from this socket.
		}
				
				rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength()); 
			/* 	rcvPacket.getData() returns the entire buffer buf (which we initially created of size 1000)
			includes both the server information and leftover bytes
		   	rcvPacket.getLength() returns the actual size of data sent by the server
		   	rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength()); will return 
		   	only the server response and removes the extra bytes */
			
			cipherTextRecieve = rcvMessage;
			
			plainTextRecieve = decryptData(cipherTextRecieve);
		
			System.out.println("----------------");
			System.out.println(plainTextRecieve);
		}
		catch(Exception E) {
			System.out.print(E);
		}
	}
	
	/* encrypt data being sent out */
	public static String encryptData(String first, String last) {
		
		String encrypted;
		
		//compare string value and change variables accordingly
		switch(first) {
		case"AA": first = "CC"; break;
		case"CC": first = "EE"; break;
		}
		
		switch(last) {
		case"BB": last = "DD"; break;
		case"DD": last = "FF"; break;
		}
		
		encrypted = first + "," + last;
	
		return encrypted;
	}

/* Method to decrypt data from server*/
	
public static String decryptData(String cipherText) {
	String decrypted = "";
	
	String[] name = new String[3];
	String first, last, SSN;
	
	name = cipherText.split(",");
	
	first = name[0];
	last = name[1];
	SSN = name[2];
	String realSSN = "";
	
	switch(SSN) {
	case"333-333-3333": realSSN = "111-111-1111"; break;
	case"444-444-4444": realSSN = "222-222-2222"; break;
	case"-1": realSSN = "-1"; break;
	}
	
	//if user entered an invalid user name
	if(realSSN.contentEquals("-1")) {
		return "SSN Not Found. Invalid Username.";
	}
	
	decrypted = "Encrypted Username: " + first + " " + last + " --> " + "SSN: " + realSSN;

	
	return decrypted; 
}
}