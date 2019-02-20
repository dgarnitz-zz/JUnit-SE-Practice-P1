package cs5031;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Words {

	public static String[] words1 = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			"East Lothian", "Highland", "Dumfries and Galloway",
			"Renfrewshire", "Scottish Borders", "Perth and Kinross" };
	public static String[] words2 = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
	public static String[] words3 = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	public static ArrayList<String> customwords;

	/**
	 * This method takes an int, calls getSetOfWords to generate the list from that category, then calls the randomize
	 * method to pick a random member from that category.
	 * @param category int representing a word category the user has chose
	 * @return
	 */
	public static String randomWord(int category) {
		HashSet<String> wordList = getSetOfWords(category);
		return randomize(wordList);
	}

	/**
	 * This method takes an int representing a word category and returns a HashSet of the words in
	 * that category. It could be private but was made public to facilitate the testing. The choice of a hashset was
	 * also taken to facilitate the testing.
	 * @param category int representing a word category the user has chose
	 * @return a hash set containing the list of words from that category
	 */
	public static HashSet<String> getSetOfWords(int category) {
		if (category == 1) {
			return new HashSet<>(Arrays.asList(words1));
		}
		if (category == 2) {
			return new HashSet<>(Arrays.asList(words2));
		}
		else {
			return new HashSet<>(Arrays.asList(words3));
		}
	}

	private static String randomize(HashSet<String> wordList){
		String chosen = "";
		int size = wordList.size();
		int item = new Random().nextInt(size);
		int i = 0;
		for(String word : wordList) {
			if (i == item)
				chosen = word;
			i++;
		}
		return chosen;
	}

	/**
	 * This version of the randomWord method is used when the user uploads their own word source from which the game
	 * will draw its words from.
	 * @param wordsource a String containing a file path where the game should access a list of words
	 * @return a String containing a word from that file path or an empty string from an error
	 */
	public static String randomWord(String wordsource) {
		customwords = new ArrayList<String>();
		return attemptToReadInWords(wordsource);
	}

	private static String attemptToReadInWords(String wordsource) {
		try {
			FileReader file = new FileReader(wordsource);
			BufferedReader reader = new BufferedReader(file);
			String line;
			while((line = reader.readLine()) != null) {
				customwords.add(line);
			}
			return customwords.get((int)(Math.random()*customwords.size()));
		} catch(FileNotFoundException e) {
			System.out.println("File error");
			return "";
		} catch(IOException e) {
			System.out.println("IO error");
			return "";
		}
	}
}
