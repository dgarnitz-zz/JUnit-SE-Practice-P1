package cs5031;

import java.util.Scanner;


public class Hangman {

	private static Scanner scan;
	private static GameState game;
	private static CommandOpts opts;

	/**
	 * The main method of Hangman game. It first creates a scanner to read the user input from the console that will
	 * be inputted  throughout the length of the game. It then instantiates a CommandOpts object, calls
	 * initiateGame to start the game, playGame to conduct the game, and concludeGame to wrap the game up.
	 * @param args command line arguments passed in at the start of the program
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);

		opts = new CommandOpts(args);

		initiateGame();

		playGame();

		concludeGame();
	}

	/**
	 * The initiateGame method handles the set up of the hangman game. It asks the users to pick between categories
	 * then uses that input result along with previously configured options to instantiate a GameState object that
	 * will be used to conduct the game play.
	 */
	static void initiateGame() {

		if (opts.wordsource == "") {

			int userChoice = 0;
			do  {
				System.out.println("  1. Counties");
				System.out.println("  2. Countries");
				System.out.println("  3. Cities");

				System.out.print("Pick a category:");
				if(scan.hasNext()){
					userChoice = scan.nextInt();
				}

			} while(!checkUserChoice(userChoice));



			game = new GameState(Words.randomWord(userChoice), opts.maxguesses, opts.maxhints);
		} else {
			game = new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
		}
	}

	public static Boolean checkUserChoice(int userChoice) {
		if(userChoice == 1 || userChoice == 2 || userChoice == 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The playGame function uses a while loop to conduct the actual play of the hangman game. It first calls
	 * showCurrentGameBoard to display the game board, then prompts the user to enter a letter. It then calls the
	 * GameState object's guessLetter method to process the user input, return a boolean to represent whether the
	 * user guessed correctly or not and outputs this to the console.
	 */
	static void playGame() {
		while (!game.won() && !game.lost()) {
			game.showCurrentGameBoard(game.wordToGuess);

			System.out.println("Guesses remaining: " + game.remainingGuesses);

			boolean correct = game.guessLetter();
			if(game.won()) {
				break;
			}

			if (correct) System.out.println("Good guess!");
			if (!correct) System.out.println("Wrong guess!");
		}
	}

	static void concludeGame() {
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.guessesMade + " guessesMade");
		} else {
			System.out.println("You lost! The wordToGuess was " + game.wordToGuess);
		}
	}
}
