import java.net.*;

public class Server {
	public static void main(String[] args) 
	{
		
			int clientPort, serverPort = 3000;
			
			byte[] buf= new byte[65535];	//it's better to keep the buffer size with in the MTU limit i.e. 1472 bytes
			
			String rcvMessage, sndMessage;
			DatagramPacket sndPacket, rcvPacket;
			InetAddress clientAddress;
			
			String cipherText;	
			
			try{
			
			try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {
				while(true) 
					
				{
					System.out.println("Waiting for incoming connections");
					
					rcvPacket = new DatagramPacket(buf, buf.length); //initialize the receive packet such that incoming data is stored in buf
					
					serverSocket.receive(rcvPacket); //wait for incoming data from client
					
					clientPort = rcvPacket.getPort(); //retrieve client's port number
					
					clientAddress = rcvPacket.getAddress(); //retrieve client's ip address
					
					System.out.println("Request from client with IP address "+ clientAddress + " and Port number "+ clientPort);
					
					rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength());
					
					 /*rcvPacket.getData() returns the entire buffer "buf" (which we initially created of size 1000)
					includes both the server information and leftover bytes
				   	rcvPacket.getLength() returns the actual size of data sent by the server
				   	rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength()); will return 
				   	only the server response and removes the extra bytes */
					//alternative to the above: rcvMessage = new String(rcvPacket.getData()).trim();	
					
					//incoming message from client encrypted
					cipherText = rcvMessage;
					
					//decrypt data, find SSN
					String decryptedName = decryptData(cipherText);
					
					//encrypt data
					sndMessage =  encryptData(decryptedName);
					
					//create a packet with the computed area and address it with the client's port number and IP address
					sndPacket = new DatagramPacket(sndMessage.getBytes(), sndMessage.getBytes().length, clientAddress, clientPort ); 
					
					serverSocket.send(sndPacket);//send the packet to the client (client details are in the sndPacket) through the serverSocket
					
					System.out.println("Response sent to client with IP address "+ clientAddress+ " and Port number "+clientPort);
					
					buf = new byte[1000];//reset the buffer and continue waiting for new incoming requests
					
				}
			}
					
		}
		catch(Exception E) {
			E.printStackTrace();
		}
		
	}

	/* Method to decrypt data from server*/
	//will receive encrypted first and last name separated by comma
	public static String decryptData(String cipherText) {
		String decrypted = "";
		
		//break name down into first and last
		String[] name = new String[2];
		String first, last;
		
		name = cipherText.split(",");
		
		first = name[0];
		last = name[1];
		
		//compare string value of first name and decrypt accordingly
		switch(first) {
		case"CC": first = "AA"; break;
		case"EE": first = "CC"; break;
		}
		
		switch(last) {
		case"DD": last = "BB"; break;
		case"FF": last = "DD"; break;
		}
		
		decrypted = first + "," + last;
		
		return decrypted; 
	}

	/* encrypt data being sent out */
	public static String encryptData(String rawName) {
		
		String encrypted = "";

		//break name into first and last
		String[] name = new String[2];
		String first, last;
		
		name = rawName.split(",");
		
		first = name[0];
		last = name[1];
		String SSN;
		
		//use first name to find corresponding SSN
		SSN = SSNForm.getSSN(first, last);
		
		//encrypted SSN
		String ESSN = "";
		
		//encrypt SSN
		switch(SSN) {
		case"111-111-1111": ESSN = "333-333-3333"; break;
		case"222-222-2222": ESSN = "444-444-4444"; break;
		case"-1": ESSN = "-1"; break;
		}
		
		//encrypt rest of name
		switch(first) {
		case"AA": first = "CC"; break;
		case"CC": first = "EE"; break;
		}
		
		switch(last) {
		case"BB": last = "DD"; break;
		case"DD": last = "FF"; break;
		}
		
		encrypted = first + "," + last + "," + ESSN;
		
		return encrypted;
	}}