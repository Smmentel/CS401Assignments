import java.io.*;
import java.net.*;
import java.util.*;

/** HandleClient class provides the running method that communicates with each client **/
public class HandleClient implements Runnable {

	//socket instance
	Socket socket;
	
	//constructor
	HandleClient(Socket socket){
		this.socket = socket;
	}
	
	/** Running method **/
	public void run() {
		
		//put in try block to catch any exceptions
		try {
			
			//dictionary object to retrieve word data for client
			Dictionary dictionary = new Dictionary();
			
			dictionary.seeDictionary();
			
			//printWriter to print out to client
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			//display number of clients
			out.println("Number of Clients: " + Server.clientCount);
			
			//read in client input
			String word = in.readLine().trim();
			
			//display definition to client
			out.println(dictionary.getDefinition(word));
			
		}catch(Exception e) {}
}
}