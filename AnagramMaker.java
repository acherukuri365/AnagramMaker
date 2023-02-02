import java.util.ArrayList;

/**
 *	AnagramMaker - This program prompts the user for any word(s), name(s),
 * 	or phrases(s). It then asks the user for the number of words in the
 * 	anagrams to print, followed by the maximum number of anagrams to print
 * 	to the screen. The program outputs as many anagrams as possible with
 * 	the given constraints and input String.
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Anirudh Cherukuri
 *	@since	January 24, 2023
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		//~ wu.readWordsFromFile(FILE_NAME);
		wu.loadWords(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!\n");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		String input = "";
		while(!input.equalsIgnoreCase("q")) {
			numPhrases = 0;
			while(input.length() == 0) {
				input = Prompt.getString("Word(s), name or phrase (q to quit)");
			}
			if(!input.equalsIgnoreCase("q")) {
				numWords = Prompt.getInt("Number of words in anagram");
				maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
				
				System.out.println("");
				
				ArrayList<String> anagram = new ArrayList<String>();
				input = checkString(input);
				getAnagrams(input, anagram);
				
				System.out.println();
				if(numPhrases >= maxPhrases)
					System.out.println("Stopped at " + maxPhrases + " anagrams\n");

				input = "";
			}
		}
	}
	
	/**
	 * 	Recursive method to find, construct, and print anagrams given
	 * 	the user's input string.
	 * 	@param	input		Initially the user's input, recursively passed
	 * 						and removed from until length is zero.
	 * 	@param	anagram		ArrayList to store all words in current anagram.
	 */
	public void getAnagrams(String input, ArrayList<String> anagram) {
		if(input.length() == 0 && anagram.size() == numWords) {
			numPhrases++;
			for(int j = 0; j < anagram.size(); j++) {
				System.out.print(anagram.get(j) + " ");
			}
			System.out.println();
			return;
		}
		
		if(anagram.size() == numWords) return;
		//~ if(input.length() > 0 && numPhrases < maxPhrases) {
		else {
			ArrayList<String> matches = wu.allWords(input);
			for(int i = 0; i < matches.size(); i++) {
				anagram.add(matches.get(i));
				String words = remove(input, matches.get(i));
				//~ if(words.length() == 0 && anagram.size() == numWords) {
					//~ numPhrases++;
					//~ for(int j = 0; j < anagram.size(); j++) {
						//~ System.out.print(anagram.get(j) + " ");
					//~ }
					//~ System.out.println();
				//~ }
				//~ else {
					getAnagrams(words, anagram);
				//~ }

				anagram.remove(anagram.size() - 1);
				
				if(maxPhrases < numPhrases) return;
			}
		}
	}
	
	/**
	 * 	Removes letter instances of a used word from the input string.
	 * 	@param	phrase		User's input, initial or modified.
	 * 	@param	word		Word to remove from phrase.
	 * 	Precondition: All letters in word are in phrase.
	 */
	private String remove(String phrase, String word) {
		while(word.length() > 0) {
            char lett = word.charAt(0);
            if(phrase.indexOf(lett) == phrase.length() - 1)
                phrase = phrase.substring(0, phrase.length() - 1);
            else
                phrase = phrase.substring(0, phrase.indexOf(lett)) + phrase.substring(phrase.indexOf(lett) + 1);
            
            word = word.substring(1);
        }
        return phrase;
	}
	
	/**
	 * 	Checks for and removes any non-alphanumeric characters in input string.
	 * 	@param	input		User's initial input
	 */
	private String checkString(String input) {
		for(int i = 0; i < input.length(); i++) {
			char lett = input.charAt(i);
			if(!Character.isLetter(lett) && !Character.isDigit(lett)) {
				input = input.substring(0, i) + input.substring(i + 1);
				i--;
			}
		}
		return input;
	}
}
