package cs5031;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

//TODO Analyze this class to see what should be refactored
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
	
	public static String randomWord(int category) {
		HashSet<String> wordList = getSetOfWords(category);
		return randomize(wordList);
	}

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
