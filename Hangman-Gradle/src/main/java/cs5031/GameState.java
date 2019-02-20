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
	
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
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

	//TODO can make certain methods private if you refactor

    /**
     * This function prints the game board in its current state to the console. It prints any letters already
	 * successfully guessed by the user. A dash is printed in place of any letter not yet guess by the user.
     * @param word a string containing the wordToGuess the user is trying to guess
     */
	void showCurrentGameBoard(String word) { 		//TODO may want to consider refactoring this to make it more testable
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
	
	String guessLetter() { 							//TODO may want to consider refactoring this to make it more testable
		System.out.print("Guess a letter or word to guess (? for a hint): ");

		String str = sc.next();

		return processLetter(str);
	}

	//TODO insert JavaDOC
	public String processLetter(String str) {
		if(str == null || str.length() == 0) {
			return "error";
		}

		str = str.toLowerCase();

		if (str.length() > 1) {
			return (inputLengthGreaterThanOne(str)) ? "victory": inCorrectGuess();
		}

		char letter = str.charAt(0);

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
		if (lettersNotGuessedYet.size() == 0) return true; else return false;
	}

	boolean lost() { 						//TODO may want to consider refactoring this to make it more testable
		if (lettersNotGuessedYet.size() > 0 && remainingGuesses == 0) return true; else return false;
	}

	/**
	 * The hint method offers the user a hint if the user has any hints remaining.
	 */
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
