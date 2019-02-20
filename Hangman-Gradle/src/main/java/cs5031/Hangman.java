package cs5031;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Hangman class contains the main method used to run the execution of the game.
 */
public class Hangman {

	public static Scanner scan;
	public static GameState game;
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

	/**
	 * The initiateGame method manages the process of starting the game. It either reads in the user input to configure
	 * and return GameState object, in which case it uses checkForMismatch to perform error handling, or it configures
	 * a GameState object using the a word source file passed in by the user.
	 * @param scanner The scanner created to read in the user input
	 * @return the Game object used to track the game's state and manage its execution
	 */
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

	private static int checkForMismatch(Scanner scan) {
		try {
			return scan.nextInt();
		} catch(InputMismatchException e) {
			scan.next(); 	//To prevent this while loop from going infinitely, need to read in the whole string
			return 0;
		}
	}

	/**
	 * The playGame manages the actual game play. It uses a while loop to keep prompting the user for input,
	 * then calls the GameState's guessLetter method to process the user's input. It then outputs information based
	 * on the results of the guessLetter function.
	 */
	public static void playGame() {
		if(game.wordToGuess.equals("")) { error = true; }
		while (!game.won() && !game.lost()) {
			game.showCurrentGameBoard(game.wordToGuess);

			System.out.println("Guesses remaining: " + game.remainingGuesses);

			String correct = game.guessLetter(scan);
			if(correct.equals("victory")) {
				break;
			}

			if (correct.equals("correct")) System.out.println("Good guess!");
			if (correct.equals("incorrect")) System.out.println("Wrong guess!");
			if (correct.equals("error")) System.out.println("Error with guess. Please enter it again");
		}
	}

	/**
	 * The conclude game method outputs information to the user as the game wraps up. Either it tells the user that
	 * they won and how long it took, that there was an error, or that the game was lost while revealing the target
	 * word.
	 */
	public static void concludeGame() {
		if (game.won()) {
			System.out.println(game.wordToGuess);
			System.out.println("Well done!");
			System.out.println("You took " + game.guessesMade + " guesses made");
		} else if(error) {
			System.out.println("Error loading the game");
		} else {
			System.out.println("You lost! The wordToGuess was " + game.wordToGuess);
		}
	}
}
