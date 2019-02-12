package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

//TODO Clean up ugly comments

// The game state
public class GameState {
	public String word; // letters
	public int guessesMade;
	public int remainingGuesses;
	public int hintsLeft;
	
	ArrayList<Character> lettersSuccessfullyGuessed;
	ArrayList<Character> lettersNotGuessedYet;
	
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String target, int guessesMade, int hintsLeft) {
		this.word = target;
		lettersNotGuessedYet = new ArrayList<Character>();
		lettersSuccessfullyGuessed = new ArrayList<Character>();
		
		for(int i = 0; i < target.length(); ++i) {
			if (!lettersNotGuessedYet.contains(Character.toLowerCase(target.charAt(i))))
			lettersNotGuessedYet.add(Character.toLowerCase(target.charAt(i)));
		}
		//System.out.println(missing);
		
		this.guessesMade = 0; // guessesMade made
		remainingGuesses = guessesMade; // guessesMade remaining
		this.hintsLeft = hintsLeft;
	}
	
	void showWord(String word) { //TODO write a test for this
		for (int i = 0; i < word.length(); ++i) {
			if (lettersSuccessfullyGuessed.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing); TODO figure out what this does - it appears several times
	}
	
	boolean guessLetter() { //TODO break this function down into several smaller functions
		int i;
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String str = sc.next().toLowerCase();
		
		if (str.length() > 1) {
			if (str == word) {
				lettersNotGuessedYet.clear(); //TODO this is confusing, why is it necessary to remove all the items from the list
				return true;
			} else return false;
		}
		
		letter = str.charAt(0);
		
		if (letter == '?') {
			hint();
			return false;
		}
		
		for(i = 0; i < lettersNotGuessedYet.size(); ++i) { // Loop over the lettersNotGuessedYet lettersSuccessfullyGuessed
			if (lettersNotGuessedYet.get(i) == letter) {
				lettersNotGuessedYet.remove(i);
				lettersSuccessfullyGuessed.add(letter);
				guessesMade++;
				return true;
			}
		}

		guessesMade++; // One more guess
		remainingGuesses--;
		return false;
	}
	
	boolean won() { //TODO write a test for this
		if (lettersNotGuessedYet.size() == 0) return true; else return false;
	}

	boolean lost() { //TODO write a test for this
		if (lettersNotGuessedYet.size() > 0 && remainingGuesses == 0) return true; else return false;
	}

	void hint() { //TODO write a test for this
		if (hintsLeft == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(lettersNotGuessedYet.get((int)(Math.random()* lettersNotGuessedYet.size())));
	}
}
