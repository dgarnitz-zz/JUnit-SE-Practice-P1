package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameState g = null;
		CommandOpts opts;

		opts = new CommandOpts(args);

		initiateGame(sc, opts, g);

	}

	static void initiateGame(Scanner sc, CommandOpts opts, GameState g) {

		if (opts.wordsource == "") {

			System.out.println("  1. Counties");
			System.out.println("  2. Countries");
			System.out.println("  3. Cities");

			System.out.print("Pick a category:");

			g = new GameState(Words.randomWord(sc.nextInt()), opts.maxguesses, opts.maxhints);
		} else {
			g = new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
		}

		playGame(g);

		concludeGame(g);
	}

	static void playGame(GameState g) {
		boolean correct;

		while (!g.won() && !g.lost()) {
			g.showWord(g.word);

			System.out.println("Guesses remaining: " + g.wrong);

			correct = g.guessLetter();

			if (correct) System.out.println("Good guess!");
			if (!correct) System.out.println("Wrong guess!");
		}
	}

	static void concludeGame(GameState g) {
		if (g.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + g.g + " guesses");
		} else {
			System.out.println("You lost! The word was " + g.word);
		}
	}
}
