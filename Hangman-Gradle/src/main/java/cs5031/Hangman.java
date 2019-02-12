package cs5031;

import java.util.Scanner;

public class Hangman {

	private static Scanner scan;
	private static GameState game;
	private static CommandOpts opts;

	public static void main(String[] args) {
		scan = new Scanner(System.in);

		opts = new CommandOpts(args);

		initiateGame();

		playGame();

		concludeGame();
	}

	static void initiateGame() {

		if (opts.wordsource == "") {

			System.out.println("  1. Counties");
			System.out.println("  2. Countries");
			System.out.println("  3. Cities");

			System.out.print("Pick a category:");

			game = new GameState(Words.randomWord(scan.nextInt()), opts.maxguesses, opts.maxhints);
		} else {
			game = new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
		}
	}

	static void playGame() {
		boolean correct;

		while (!game.won() && !game.lost()) {
			game.showWord(game.word);

			System.out.println("Guesses remaining: " + game.remainingGuesses);

			correct = game.guessLetter();

			if (correct) System.out.println("Good guess!");
			if (!correct) System.out.println("Wrong guess!");
		}
	}

	static void concludeGame() {
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.guessesMade + " guessesMade");
		} else {
			System.out.println("You lost! The word was " + game.word);
		}
	}
}
