 import java.io.*;
 import java.net.*;
 /** Server class, contains main method **/

 public class TCPServer {

 	//variable to hold client count
 	static int clientCount = 0;

 	/*main method*/

 	public static void main(String[] args) throws IOException {

 		//Server initialized with port 8000
 		ServerSocket s = new ServerSocket(8000);

 		//while loop to ensure server can handle multiple clients
 		while (true) {
 			Socket socket = s.accept(); //create socket to accept client
 			HandleClient c = new HandleClient(socket); //object to handle client input
 			Thread t = new Thread(c); //create into thread
 			clientCount++; //increment count
 			t.start(); //start thread
 		}
 	}
 }