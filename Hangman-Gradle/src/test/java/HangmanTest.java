import static org.junit.Assert.*;

import cs5031.CommandOpts;
import cs5031.GameState;
import cs5031.Hangman;
import cs5031.Words;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The HangmanTest contains the JUnit tests for the Hangman class. The tests are setup to test all the testable code
 * inside the Hangman class, as well as some of the code from the GameState class that could not be tested directly.
 * This class uses a ByteArrayOutputStream to simulate the user input being passed to the scanner and a PrintStream
 * to catch the output of the console. With those configured, it is possible to test both the setup and execution of the
 * game.
 */
public class HangmanTest {
    public Scanner scan;
    public CommandOpts options;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void initiateGameNormal() {
        byte[] bytes = "1".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        scan = new Scanner(is);

        String[] path = new String[0];
        options = new CommandOpts(path);
        Hangman.opts = options;

        GameState game = Hangman.initiateGame(scan);

        assertEquals(10, game.remainingGuesses);
        assertEquals(0, game.guessesMade);
        assertEquals(2, game.hintsLeft);
    }

    @Test
    public void initiateGameWithAlternativePath() {
        byte[] bytes = "1".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        scan = new Scanner(is);

        String[] path = new String[1];
        path[0] = "/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/resources/test.txt";
        options = new CommandOpts(path);
        Hangman.opts = options;

        GameState game = Hangman.initiateGame(scan);

        assertEquals("answer", game.wordToGuess);
        assertEquals("[a, n, s, w, e, r]", game.lettersNotGuessedYet.toString());
        assertEquals("[]", game.lettersSuccessfullyGuessed.toString());

    }

    @Test
    public void initiateGameAfterWrongEntry() {
        byte[] bytes = "asd\n2".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        scan = new Scanner(is);

        String[] path = new String[0];
        options = new CommandOpts(path);
        Hangman.opts = options;

        GameState game = Hangman.initiateGame(scan);

        HashSet<String> wordList = Words.getSetOfWords(2);
        List<String> result = wordList.stream().map(String::toLowerCase).collect(Collectors.toList());

        assertTrue(result.contains(game.wordToGuess)); //Check if the word chosen is in the countries category inside Words.java
    }

    @Test
    public void winGame() {
        byte[] bytes = "answer".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        scan = new Scanner(is);
        Hangman.scan = scan;

        String[] path = new String[1];
        path[0] = "/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/resources/test.txt";
        options = new CommandOpts(path);
        Hangman.opts = options;

        Hangman.game = Hangman.initiateGame(scan);
        Hangman.playGame();
        Hangman.concludeGame();

        String expectedOutput = "------\n" +
                "Guesses remaining: 10\n" +
                "Guess a letter or word to guess (? for a hint): answer\n" +
                "Well done!\n" +
                "You took 1 guesses made\n";

        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void loseGame() {
        byte[] bytes = "q\nt\ny\nu\ni\no\np\nd\nf\ng".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        scan = new Scanner(is);
        Hangman.scan = scan;

        String[] path = new String[1];
        path[0] = "/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/resources/test.txt";
        options = new CommandOpts(path);
        Hangman.opts = options;

        Hangman.game = Hangman.initiateGame(scan);
        Hangman.playGame();
        Hangman.concludeGame();

        String expectedOutput = "------\n" +
                "Guesses remaining: 10\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 9\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 8\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 7\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 6\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 5\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 4\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 3\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 2\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "------\n" +
                "Guesses remaining: 1\n" +
                "Guess a letter or word to guess (? for a hint): Wrong guess!\n" +
                "You lost! The wordToGuess was answer\n";

        assertEquals(expectedOutput, outContent.toString());

    }
}
