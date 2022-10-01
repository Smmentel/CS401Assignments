/** Object to represent the dictionary word and definition */

public class DictionaryDefinition {
	
	private String word;
	
	private String definition;
	
	//Constructor
	public DictionaryDefinition(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}
	
	//getter method to access the word's definition
	public String getDefinition() {
		return definition;
	}
	
	//getter method to access the word itself
	public String getWord() {
		return word;
	}

}
