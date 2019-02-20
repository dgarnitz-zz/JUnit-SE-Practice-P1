package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	public String wordToGuess;
	public int guessesMade;
	public int remainingGuesses;
	public int hintsLeft;
	
	public ArrayList<Character> lettersSuccessfullyGuessed;
	public ArrayList<Character> lettersNotGuessedYet;
	
	public GameState(String target, int guessesMade, int hintsLeft) {
		this.wordToGuess = target.toLowerCase();
		lettersNotGuessedYet = new ArrayList<Character>();
		lettersSuccessfullyGuessed = new ArrayList<Character>();
		
		for(int i = 0; i < target.length(); ++i) {
			if (!lettersNotGuessedYet.contains(Character.toLowerCase(target.charAt(i))))
			lettersNotGuessedYet.add(Character.toLowerCase(target.charAt(i)));
		}
		
		this.guessesMade = 0;
		remainingGuesses = guessesMade;
		this.hintsLeft = hintsLeft;
	}

    /**
     * This function prints the game board in its current state to the console. It prints any letters already
	 * successfully guessed by the user. A dash is printed in place of any letter not yet guess by the user.
     * @param word a string containing the wordToGuess the user is trying to guess
     */
	void showCurrentGameBoard(String word) { 		//I chose to test this manually
		for (int i = 0; i < word.length(); ++i) {
			if (lettersSuccessfullyGuessed.contains(word.charAt(i)) && word.charAt(i) != ' ') {
				System.out.print(word.charAt(i));
			} else if(word.charAt(i) == ' ') {
				System.out.print(" ");
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
	}

	/**
	 * The guessLetter method prompts the user to enter a letter. It then uses the scanner it has been passed to
	 * acess the user's input, then passes it to processLetter.
	 * @param scan the scanner it has been passed from the Hangman class.
	 * @return a String containing the results of the processLetter method
	 */
	String guessLetter(Scanner scan) {
		System.out.print("Guess a letter or word to guess (? for a hint): ");

		scan.useDelimiter("\n");
		String input = scan.next();

		return processLetter(input);
	}

	/**
	 * The processLetter method takes a string read in from the scanner, analyzes it and calls private methods that
	 * then make update to the GameState's state. It handles a variety of scenarios regarding the input and responds
	 * accordingly.
	 * @param input A string containing the
	 * @return
	 */
	public String processLetter(String input) {
		if(input == null || input.length() == 0) {
			return "error";
		}

		input = input.toLowerCase();

		if (input.length() > 1) {
			return (inputLengthGreaterThanOne(input)) ? "victory": inCorrectGuess();
		}

		char letter = input.charAt(0);

		if (letter == '?') {
			hint();
			return "hint";
		}

		if(checkGuessedLetter(letter)){
			return "correct";
		}

		return inCorrectGuess();
	}

	private String inCorrectGuess() {
		guessesMade++;
		remainingGuesses--;
		return "incorrect";
	}

	private Boolean inputLengthGreaterThanOne(String str) {
		if (str.equals(wordToGuess)) {
			lettersNotGuessedYet.clear();
			guessesMade++;
			return true;
		} else {
			return false;
		}
	}

	private Boolean checkGuessedLetter(char letter) {
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

	boolean won() {
		return (lettersNotGuessedYet.size() == 0);
	}

	boolean lost() {
		return (lettersNotGuessedYet.size() > 0 && remainingGuesses == 0);
	}

	private void hint() {
		if (hintsLeft == 0) {
			System.out.println("No more hints allowed");
		} else {
			System.out.print("Try: ");
			System.out.println(lettersNotGuessedYet.get((int)(Math.random()* lettersNotGuessedYet.size())));
			hintsLeft--;
		}
	}
}
