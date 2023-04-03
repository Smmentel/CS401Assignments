import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/** Credentials class to hold collection of client cardNum with corresponding exp, and method to determine if valid client input **/

public class CardCredentials {

	//Linked List to hold all user credentials
	private static LinkedList<CardData> cardData = new LinkedList();

	//Create a file instance
	private static File file = new File("/src/CardCredentials");

	/* Constructor that reads input from file to the linked list */
	CardCredentials() throws FileNotFoundException {

		//Create scanner for the file
		Scanner fileInput = new Scanner(file);

		//Read in file data
		while (fileInput.hasNext()) {
			String cardNum = fileInput.next().trim();

			String exp = fileInput.nextLine().trim();

			CardData card = new CardData(cardNum, exp);

			cardData.add(card);
		}
	}

	/** Method to return if there is a match**/

	public Boolean determineMatch(String combined) {
		combined.trim();
		String[] set = new String[1];
		set = combined.split(",");
		String cardNum = set[0];
		String exp = set[1];
		cardNum = cardNum.substring(1);

		//compare client word to each word in dictionary until match
		for (int i = 0; i < cardData.size(); i++) {

			if (cardData.get(i).getCardNum().equals(cardNum)) {
				if (cardData.get(i).getExp().equals(exp)) {
					return true;
				}
			}
		}

		return false;
	}

}