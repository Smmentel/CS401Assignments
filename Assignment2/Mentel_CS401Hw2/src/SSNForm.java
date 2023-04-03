import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class SSNForm {
	
	private static LinkedList<SSNResources> SSNData= new LinkedList<SSNResources>();
	//Create a file instance
	private File file = new File("src/Sample");
	
	/* Constructor that reads input from file to the linked list */
	SSNForm() throws FileNotFoundException{
		
	try (//Create scanner for the file
	Scanner fileInput = new Scanner(file)) {
		//Read in file data
		while(fileInput.hasNext()) {
			String first = fileInput.next().trim();
			
			String last = fileInput.next().trim();
			
			String SSN = fileInput.nextLine().trim();
			
			SSNResources newInput  = new SSNResources(first, last, SSN);
			
			SSNData.add(newInput);
			}
	}
	}
	
	/** Method to return SSN**/
	public static String getSSN(String first, String last) {
		
		try {
			SSNForm form = new SSNForm();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//compare client word to each word in form until match
		SSNResources match = new SSNResources(null,null,null);
	for(int i = 0; i < SSNData.size(); i++) {
		match = SSNData.get(i);
		if(match.getFirst().equals(first) && (match.getLast().equals(last))) {
			return SSNData.get(i).getSSN();
		}
	}
	return "-1";
	}
	
}