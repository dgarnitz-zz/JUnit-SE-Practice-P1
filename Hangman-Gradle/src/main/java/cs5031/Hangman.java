package cs5031;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Hangman {

	private static Scanner scan;
	private static GameState game;
	public static CommandOpts opts;
	private static Boolean error = false;

	/**
	 * The main method of Hangman game. It first creates a scanner to read the user input from the console that will
	 * be inputted  throughout the length of the game. It then instantiates a CommandOpts object, calls
	 * initiateGame to start the game, playGame to conduct the game, and concludeGame to wrap the game up.
	 * @param args command line arguments passed in at the start of the program
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);

		opts = new CommandOpts(args);

		game = initiateGame(scan);

		playGame();

		concludeGame();
	}

	public static GameState initiateGame(Scanner scanner) {
		if (opts.wordsource.equals("")) {
			int userChoice = 0;
			do  {
				System.out.println("  1. Counties");
				System.out.println("  2. Countries");
				System.out.println("  3. Cities");

				System.out.print("Pick a category:");


				if(scanner.hasNext()){
					userChoice = checkForMismatch(scanner);
				}

			} while(!(userChoice == 1 || userChoice == 2 || userChoice == 3));

			return new GameState(Words.randomWord(userChoice), opts.maxguesses, opts.maxhints);
		} else {
			return new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
		}
	}

	public static int checkForMismatch(Scanner scan) {
		try {
			return scan.nextInt();
		} catch(InputMismatchException e) {
			scan.next(); 	//To prevent this while loop from going infinitely, need to read in the whole string
			return 0;
		}
	}

	static void playGame() {
		if(game.wordToGuess.equals("")) { error = true; }
		while (!game.won() && !game.lost()) {
			game.showCurrentGameBoard(game.wordToGuess);

			System.out.println("Guesses remaining: " + game.remainingGuesses);

			String correct = game.guessLetter();
			if(correct.equals("victory")) {
				break;
			}

			if (correct.equals("correct")) System.out.println("Good guess!");
			if (correct.equals("incorrect")) System.out.println("Wrong guess!");
			if (correct.equals("error")) System.out.println("Error with guess. Please enter it again");
		}
	}

	static void concludeGame() {
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.guessesMade + " guessesMade");
		} else if(error) {
			System.out.println("Error loading the game");
		} else {
			System.out.println("You lost! The wordToGuess was " + game.wordToGuess);
		}
	}
}
