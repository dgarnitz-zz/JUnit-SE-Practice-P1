import static org.junit.Assert.*;

import cs5031.GameState;
import org.junit.Before;
import org.junit.Test;

/**
 * The GameStateTest class contains the principle set of JUnit tests for the GameState class. The functionality inside
 * GameState can be divided into those classes which can be tested directly, which are tested in here, and those which
 * must be tested indirectly, which are accessed from Hangman. Every test here is begun by calling the GameState's
 * processLetter function. A variety of inputs are used to process letter in an attempt to catch all possible cases.
 * The processLetter method calls several private methods that are also tested in this class.
 */
public class GameStateTest {
	private static int guesses = 8;
	private static int hints = 2;
	private static String testWord = "testy";
	private static String empty = "";
	private static String testNull = null;
	private static String hint = "?";
	private static String space = " ";
	private static String incorrectLetter = "g";
	private static String incorrectWord = "nizzle";
	private static String correctLetter = "e";
	private static String correctCapitalized = "S";
	GameState test;

	@Before
	public void setup() {
		test = new GameState(testWord, guesses, hints);
	}

	@Test
	public void processLetterEmpty() {
		assertEquals("error", test.processLetter(empty));
	}

	@Test
	public void processLetterNull() {
		assertEquals("error", test.processLetter(testNull));
	}

	@Test
	public void processLetterSpace() {
		assertEquals("incorrect", test.processLetter(space));
		assertEquals(1, test.guessesMade);
		assertEquals(7, test.remainingGuesses);
	}

	@Test
	public void processLetterHint() {
		assertEquals("hint", test.processLetter(hint));
		assertEquals(1, test.hintsLeft);
		assertEquals("hint", test.processLetter(hint));
		assertEquals(0, test.hintsLeft);
		assertEquals("hint", test.processLetter(hint));
		assertEquals(0, test.hintsLeft);
	}

	@Test
	public void processLetterIncorrectLetter() {
		assertEquals("incorrect", test.processLetter(incorrectLetter));
		assertEquals(1, test.guessesMade);
		assertEquals(7, test.remainingGuesses);
	}
	@Test
	public void processLetterIncorrectWord() {
		assertEquals("incorrect", test.processLetter(incorrectWord));
		assertEquals(1, test.guessesMade);
		assertEquals(7, test.remainingGuesses);
	}

	@Test
	public void processLetterCorrectLetter() {
		assertEquals("correct", test.processLetter(correctLetter));
		assertEquals(1, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
	}

	@Test
	public void processLetterCorrectLetterCapitalized() {
		assertEquals("correct", test.processLetter(correctCapitalized));
		assertEquals(1, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
	}

	@Test
	public void processLetterFullWordGuess() {
		assertEquals("victory", test.processLetter("testy"));
		assertEquals(1, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
	}

	@Test
	public void processLetterFullWordGuessCapitalized() {
		assertEquals("victory", test.processLetter("TestY"));
		assertEquals(1, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
	}

	@Test
	public void processLetterRemainingLettersGuessCorrect() {
		assertEquals("correct", test.processLetter("t"));
		assertEquals(1, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
		assertEquals("victory", test.processLetter("testy"));
		assertEquals(2, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
	}

}

