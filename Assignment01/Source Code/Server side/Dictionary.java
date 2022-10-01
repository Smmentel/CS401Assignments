import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/** Dictionary class to hold collection of the words with corresponding defintions, and method to determine what word the client chose **/
public class Dictionary {
	
	//Linked List to hold all dictionary definitions
	private static LinkedList<DictionaryDefinition> dictionary= new LinkedList();
	
	//Create a file instance
	private static File file = new File("src/Dictionary.txt");
	
	/* Constructor that reads input from file to the linked list */
	Dictionary() throws FileNotFoundException{
		
	//Create scanner for the file
	Scanner fileInput = new Scanner(file);

	
	//Read in file data
	while(fileInput.hasNext()) {
		String word = fileInput.next().trim();
		
		String definition = fileInput.nextLine().trim();
		
		DictionaryDefinition newWord = new DictionaryDefinition(word, definition);
		
		dictionary.add(newWord);
		}
	}
	
	/** Method to return definition of client's word**/
	public String getDefinition(String clientWord) {
		
	//compare client word to each word in dictionary until match
	for(int i = 0; i < dictionary.size(); i++) {
		
		if(dictionary.get(i).getWord().equals(clientWord)) {
			return dictionary.get(i).getDefinition();
		}
	}
	return "Word not found, please try again.";
	}
	
}
