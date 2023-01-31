import java.util.ArrayList;

/**
 *	AnagramMaker - <description goes here>
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
			input = Prompt.getString("Word(s), name or phrase (q to quit)");
			if(!input.equalsIgnoreCase("q")) {
				numWords = Prompt.getInt("Number of words in anagram");
				maxPhrases = Prompt.getInt("Maximun number of anagrams to print");
				
				System.out.println("\n");
				
				ArrayList<String> anagram = new ArrayList<String>();
				getAnagrams(input);
				
				System.out.println("\nStopped at " + maxPhrases + " anagrams\n");
			}
		}
	}
	
	public void getAnagrams(String input, ArrayList<String> anagram) {
		if(input.length() > 0) {
			ArrayList<String> matches = wu.allWords(input);
			for(int i = 0; i < matches.size(); i++) {
				anagram.add(matches.get(i));
				String words = remove(input, allwords.get(i))
			}
		}
	}

	
}
