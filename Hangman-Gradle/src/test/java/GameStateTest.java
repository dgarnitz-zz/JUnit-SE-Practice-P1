import static org.junit.Assert.*;

import cs5031.GameState;
import org.junit.Before;
import org.junit.Test;

public class GameStateTest {
	public static int guesses = 8;
	public static int hints = 2;
	public static String testWord = "testy";
	public static String empty = "";
	public static String testNull = null;
	public static String hint = "?";
	public static String space = " ";
	public static String incorrectLetter = "g";
	public static String incorrectWord = "nizzle";
	public static String correctLetter = "e";
	public static String correctCapitalized = "S";
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
		assertEquals("correct", test.processLetter(correctCapitalized)); //TODO this test failed
		assertEquals(1, test.guessesMade);
		assertEquals(8, test.remainingGuesses);
	}

}

