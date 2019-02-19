package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

//TODO Clean up ugly comments


public class GameState {
	public String wordToGuess;
	public int guessesMade;
	public int remainingGuesses;
	public int hintsLeft;
	
	ArrayList<Character> lettersSuccessfullyGuessed;
	ArrayList<Character> lettersNotGuessedYet;
	
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String target, int guessesMade, int hintsLeft) {
		this.wordToGuess = target;
		lettersNotGuessedYet = new ArrayList<Character>();
		lettersSuccessfullyGuessed = new ArrayList<Character>();
		
		for(int i = 0; i < target.length(); ++i) {
			if (!lettersNotGuessedYet.contains(Character.toLowerCase(target.charAt(i))))
			lettersNotGuessedYet.add(Character.toLowerCase(target.charAt(i)));
		}
		//System.out.println(missing); //TODO What is this?
		
		this.guessesMade = 0;
		remainingGuesses = guessesMade;
		this.hintsLeft = hintsLeft;
	}

    /**
     * This function prints the game board in its current state to the console. It prints any letters already
	 * successfully guessed by the user. A dash is printed in place of any letter not yet guess by the user.
     * @param word a string containing the wordToGuess the user is trying to guess
     */
	void showCurrentGameBoard(String word) { //TODO write a test for this
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
		System.out.print("Guess a letter or word to guess (? for a hint): "); //TODO separate from read in the guess from separate the guess, need new function

		String str = sc.next().toLowerCase();

		return processLetter(str);
	}

	public Boolean processLetter(String str) {
		if (str.length() > 1) {
			return inputLengthGreaterThanOne(str);
		}

		char letter = str.charAt(0);

		if (letter == '?') {
			hint();
			return false;
		}

		if(checkGuessedLetter(letter)){
			return true;
		}

		guessesMade++;
		remainingGuesses--;
		return false;
	}

    //TODO I think this needs to be changed. if the user successfully enters all guesses. More things need to happen
	public Boolean inputLengthGreaterThanOne(String str) {
		if (str == wordToGuess) {
			lettersNotGuessedYet.clear();
			return true;
		} else {
			return false;
		}
	} //TODO FIX THIS

    /**
     * This function takes the letter entered by the user and compares it against the set of letters not yet guessed.
     * If the letter the user guessed is part of lettersNotGuessedYet, it removes it from there and adds it to
     * lettersSuccessfullyGuess. It then increments guessesMade and returns true. If the letter does not match it
     * returns false.
     * @param letter the letter being checked
     * @return
     */
	public Boolean checkGuessedLetter(char letter) {
		for(int i = 0; i < lettersNotGuessedYet.size(); ++i) {
			if (lettersNotGuessedYet.get(i) == letter) {
				lettersNotGuessedYet.remove(i);
				lettersSuccessfullyGuessed.add(letter);
				guessesMade++;
				return true;
			}
		}

		return false;
	}
	//TODO you need to write the test for these two functions

	//TODO do you even need two functions for this? Should won & lost be combine into one function?
	boolean won() {
		if (lettersNotGuessedYet.size() == 0) return true; else return false;
	}

	boolean lost() {
		if (lettersNotGuessedYet.size() > 0 && remainingGuesses == 0) return true; else return false;
	}

	/**
	 * The hint method offers the user a hint if the user has any hints remaining.
	 */
	void hint() { //TODO write a test for this
		if (hintsLeft == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(lettersNotGuessedYet.get((int)(Math.random()* lettersNotGuessedYet.size())));
	}
}
