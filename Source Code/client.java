import java.io.*;
import java.net.*;
import java.util.*;
		
public class client{
		
	public static void main(String[] args) throws Exception{
		
		Scanner input = new Scanner(System.in);
		
		String serverResponse;
		
		String word, wordDefinition = "";
		
		//System.out.println(add);
		Socket socket = new Socket("10.16.51.83",8000);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  
		/* socket.getOutputStream() returns an output stream for this socket. 
		PrintWriter(socket.getOutputStream(), true) creates a new PrintWriter from an existing OutputStream. 
		This convenience constructor creates the necessary intermediateOutputStreamWriter, 
		which will convert characters into bytes using the default character encoding.*/
		
		BufferedReader in = new BufferedReader(new 
		InputStreamReader(socket.getInputStream()));
		/* socket.getInputStream() returns an input stream for this socket. 
		new InputStreamReader(socket.getInputStream()) creates an InputStreamReader that uses 
		the default charset. new BufferedReader(new InputStreamReader(socket.getInputStream())) creates a buffering character-input 
		stream that uses a default-sized input buffer. */
		
		//first read in client count
		serverResponse = in.readLine();

		//and display to client
		System.out.println(serverResponse);
		//then inform client ready for input
		System.out.print("Please enter word: ");

		//read in input
		word = input.next();
		word.trim();
		word.toLowerCase();

		//send to server
		out.println(word);

		//read server response
		serverResponse = in.readLine();
		wordDefinition = serverResponse;

		//display to client
		System.out.println(word + ": " + wordDefinition);
		}
		}
