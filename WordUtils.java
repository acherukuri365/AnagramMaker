
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Anirudh Cherukuri
 *	@since	October 20, 2022
 */

import java.util.Scanner;
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	/* Constructor */
	public WordUtils() {
		words = new String[90934];
		loadWords();
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () {
		Scanner read = FileUtils.openToRead(WORD_FILE);
		//~ int count = 0;
		//~ while(read.hasNext()) {
			//~ count++;
		//~ }
		//~ words = new String[count];
		
		int index = 0;
		while(read.hasNextLine()) {
			words[index] = new String(read.nextLine());
			index++;
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{
		// int count = 0;
		// String usedLetters = new String(" ");
		// for(int i = 0; i < words.length; i++) {
		// 	String word = words[i];
		// 	for(int j = 0; j < word.length(); j++) {
		// 		char letter = word.charAt(j);
		// 		if(letters.indexOf(letter) != -1) {
		// 			if(usedLetters.charAt(j) != letter) {
		// 				count++;
		// 				usedLetters += letter;
		// 			}
		// 			else
		// 				usedLetters += " ";
		// 		}
		// 	}
		// }
		
		// String[] returnWords = new String[count];
		// usedLetters = new String(" ");
		// for(int i = 0; i < words.length; i++) {
		// 	String word = words[i];
		// 	for(int j = 0; j < word.length(); j++) {
		// 		char letter = word.charAt(j);
		// 		if(letters.indexOf(letter) != -1) {
		// 			if(usedLetters.charAt(j) != letter) {
		// 				returnWords[i] = word;
		// 				usedLetters += letter;
		// 			}
		// 			else
		// 				usedLetters += " ";
		// 		}
		// 	}
		// }

		int count = 0;
		for(int i = 0; i < words.length; i++) {
			if(matchWord(words[i], letters)) {
				count++;
			}
		}

		// System.out.println("\n\n" + count + "\n\n");

		String[] returnWords = new String[count];
		count = 0;	
		for(int i = 0; i < words.length; i++) {
			if(matchWord(words[i], letters)) {
				// System.out.println("added word");
				returnWords[count] = words[i];
				count++;
			}
		}
		
		return returnWords;
	}

	public boolean matchWord(String word, String letters) {
		String usedIndex = "";
		boolean match = false;

		if(word.length() < 4)
			return false;
		
		for(int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			// if(word.indexOf(letter) != -1 && !(usedIndex.contains("" + i))) {
			if(letters.indexOf(letter) != -1 && !(usedIndex.contains("" + i))) {
				// System.out.println("checked the match");
				// match = true;
				// usedIndex += i;
				letters = letters.substring(0, letters.indexOf(letter)) + 
					letters.substring(letters.indexOf(letter) + 1);
			}
			else
				return false;
		}
		return true;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) {
		// for(int i = 0; i < wordList.length - 4; i += 5) {
		// 	System.out.printf("%s%-20s%-20s%-20s%-20s\n", wordList[i], wordList[i + 1],
		// 		wordList[i + 2], wordList[i + 3], wordList[i + 4]);
		// }
		
		// for(int i = 0; i < wordList.length; i++) {
		// 	System.out.println(wordList[i]);
		// }

		for(int i = 0; i < wordList.length - 4; i += 5) {
			System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", wordList[i],
				wordList[i + 1], wordList[i + 2], wordList[i + 3], wordList[i + 4]);
		}

		int temp = wordList.length - 4;
		while(temp < wordList.length) {
			System.out.printf("%-20s", wordList[temp]);
			temp++;
		}
		System.out.println();
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		String best = new String();
		for(int i = 0; i < wordList.length; i++) {
			String word = wordList[i];
			int points = getScore(word, scoreTable);
			
			int bestPoints = getScore(best, scoreTable);
			
			if(points > bestPoints)
				best = word;
		}
		
		return best;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		int points = 0;
		for(int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if(letter >= 'a' && letter <= 'z');
				points += scoreTable[letter - 97];
		}
		
		return points;
	}
	
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}
