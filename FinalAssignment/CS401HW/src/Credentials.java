import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/** Credentials class to hold collection of client user names with corresponding passwords, and method to determine if valid client input **/

public class Credentials {

	//Linked List to hold all user credentials
	private static LinkedList<SingleClient> usrCred = new LinkedList();

	//Create a file instance
	private static File file = new File("/src/Login");

	/* Constructor that reads input from file to the linked list */
	Credentials() throws FileNotFoundException {

		//Create scanner for the file
		Scanner fileInput = new Scanner(file);

		//Read in file data
		while (fileInput.hasNext()) {
			String username = fileInput.next().trim();

			String password = fileInput.nextLine().trim();

			SingleClient newClient = new SingleClient(username, password);

			usrCred.add(newClient);
		}

	}

	/** Method to return if there is a match**/

	public Boolean determineMatch(String combined) {
		combined.trim();
		String[] set = new String[1];
		set = combined.split(",");
		String username = set[0];
		String password = set[1];

		//compare client word to each word in dictionary until match
		for (int i = 0; i < usrCred.size(); i++) {
			System.out.println(usrCred.get(i).getUsername());

			if (usrCred.get(i).getUsername().equals(username)) {
				if (usrCred.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}

		return false;
	}

}