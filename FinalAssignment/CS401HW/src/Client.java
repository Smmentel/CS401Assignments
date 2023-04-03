import java.io.*;
import java.net.*;
import java.util.*;

/** Client, wakls client through  ordering process, communicates with  TCP Server**/
public class Client {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		String serverResponse;

		String username, password;

		//System.out.println(add);
		Socket socket = new Socket("localhost", 8000);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		/* socket.getOutputStream() returns an output stream for this socket. 
		PrintWriter(socket.getOutputStream(), true) creates a new PrintWriter from an existing OutputStream. 
		This convenience constructor creates the necessary intermediateOutputStreamWriter, 
		which will convert characters into bytes using the default character encoding.*/

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		/* socket.getInputStream() returns an input stream for this socket. 
		new InputStreamReader(socket.getInputStream()) creates an InputStreamReader that uses 
		the default charset. new BufferedReader(new InputStreamReader(socket.getInputStream())) creates a buffering character-input 
		stream that uses a default-sized input buffer. */

		//then inform client ready for input
		System.out.print("Please enter username: ");

		//read in input
		username = input.next();
		username.trim();
		username.toLowerCase();

		System.out.print("Please enter password: ");

		password = input.next();
		password.trim();
		password.toLowerCase();

		//send credentials to server
		out.println(username + "," + password);

		//read server response, should be list  of items available
		serverResponse = in .readLine();

		if (serverResponse.contains("true"))
			System.out.println(printMenu());
		else System.out.println(serverResponse);

		String cart;
		cart = input.next();

		//send to order server 
		out.println(cart);

		//read server response of total
		serverResponse = in .readLine();
		double charge = Double.parseDouble(serverResponse);
		double tax = charge * 0.06;
		double sdFee = charge * 0.18;
		double total = charge + tax + sdFee;
		String lineBreak = "\r\n";

		System.out.println("------------------------------");
		System.out.printf("Cost: $ %.2f", charge);
		System.out.printf(lineBreak + "Tax: $ %.2f", tax);
		System.out.printf(lineBreak + "Service and Delivery Fee: $ %.2f", sdFee);
		System.out.printf(lineBreak + "Total: $ %.2f", total);

		//gathering purchase information from the client and send encrypted data to TCP server
		String cardNum, exp;

		System.out.println(lineBreak + "------------------------------");
		System.out.print("Please enter 16 digit card number:");
		cardNum = input.next().trim();
		System.out.print("Expiration date(mon/date): ");
		exp = input.next().trim();

		//encrypt information
		out.println(encrypt(cardNum + "," + exp));

		//read server response, should be confirmation of purchase
		serverResponse = in .readLine();

		System.out.println(lineBreak + "------------------------------");
		//display to client
		System.out.println(serverResponse);

	}

	public static String printMenu() {
		//store  hours  are 11am-8pm the following code generates a  random store hour
		//this is so that the store is always open, therein making the code always open
		int range = (12 - 1) + 1;
		int time = (int)(Math.random() * range) + 1;

		while (time == 9 || time == 10)
			time = (int)(Math.random() * range) + 1;

		//depending on the time depends what menu shall be printed to the client
		//BBQ normally sells out by the end of the night that way all meats that are smoked are always fresh the next day
		switch (time) {
			case (11):
			case (12):
			case (1):
				return morningMenu();
			case (2):
			case (3):
			case (4):
			case (5):
				return afternoonMenu();
			case (6):
			case (7):
			case (8):
				return nightMenu();
		}

		return "something got messed up in switch stmt";
	}

	public static String morningMenu() {
		String lineBreak = "\r\n";
		return lineBreak + "---------PRIMOS BBQ----------" +
			lineBreak +
			"Please enter meal # and side preference, if multiple meals in one order please separate with comma only (NO spaces)." +
			lineBreak +
			lineBreak + "For Example: 1a,2b would represent a brisket meal with mac and cheese AND a pork meal with cheesy potatoes for $30.00" +
			lineBreak +
			lineBreak + "-----------Meals------------" +
			lineBreak + "1.Brisket $14:         90 meals left" +
			lineBreak + "2.Pork  $10:            90 meals left" +
			lineBreak + "3.Pork Ribs $18:       18 meals left" +
			lineBreak + "4.Chicken Quarter $8:  6 meals left" +
			lineBreak + "5.Bone-in Wings $10:    6 meals left" +
			lineBreak +
			lineBreak + "------------Sides-------------" +
			lineBreak + "a:mac and cheese   d:coleslaw" +
			lineBreak + "b:cheesy potatoes  e:potato salad" +
			lineBreak + "c:smoked beans     f:no side" +
			lineBreak + "------------------------------";
	}

	public static String afternoonMenu() {
		String lineBreak = "\r\n";
		return lineBreak + "---------PRIMOS BBQ----------" +
			lineBreak +
			"Please enter meal # and side preference, if multiple meals in one order please separate with comma only (NO spaces)." +
			lineBreak +
			lineBreak + "For Example: 1a,2b would represent a brisket meal with mac and cheese AND a pork meal with cheesy potatoes for $30.00" +
			lineBreak +
			lineBreak + "-----------Meals------------" +
			lineBreak + "1.Brisket $14:        50 meals left" +
			lineBreak + "2.Pork $10:           60 meals left" +
			lineBreak + "3.Pork Ribs $18:       9 meals left" +
			lineBreak + "4.Chicken Quarter: SOLD OUT" +
			lineBreak + "5.Bone-in Wings:   SOLD OUT" +
			lineBreak +
			lineBreak + "------------Sides-------------" +
			lineBreak + "a:mac and cheese   d:coleslaw" +
			lineBreak + "b:cheesy potatoes  e:potato salad" +
			lineBreak + "c:smoked beans     f:no side" +
			lineBreak + "------------------------------";
	}

	public static String nightMenu() {
		String lineBreak = "\r\n";
		return lineBreak + "---------PRIMOS BBQ----------" +
			lineBreak +
			"Please enter meal # and side preference, if multiple meals in one order please separate with comma only (NO spaces)." +
			lineBreak +
			lineBreak + "For Example: 1a,2b would represent a brisket meal with mac and cheese AND a pork meal with cheesy potatoes for $30.00" +
			lineBreak +
			lineBreak + "-----------Meals------------" +
			lineBreak + "1.Brisket $14: 20 meals left" +
			lineBreak + "2.Pork $10: 30 meals left" +
			lineBreak + "3.Pork Ribs:        SOLD OUT" +
			lineBreak + "4.Chicken Quarter: SOLD OUT" +
			lineBreak + "5.Bone-in Wings:   SOLD OUT" +
			lineBreak +
			lineBreak + "------------Sides-------------" +
			lineBreak + "a:mac and cheese   d:coleslaw" +
			lineBreak + "b:cheesy potatoes  e:potato salad" +
			lineBreak + "c:smoked beans     f:no side" +
			lineBreak + "------------------------------";
	}

	public static String encrypt(String insecureData) {
		String secureData = " ";

		for (int i = 0; i < insecureData.length(); i++) {
			secureData += (char)(insecureData.charAt(i) + 2);
		}

		return secureData;
	}
}