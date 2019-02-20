import static org.junit.Assert.*;

import cs5031.CommandOpts;
import cs5031.GameState;
import cs5031.Hangman;
import cs5031.Words;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class HangmanTest {
    public Scanner scan;
    public CommandOpts options;

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
        byte[] bytes = "1\n".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        scan = new Scanner(is);

        String[] path = new String[1];
        path[0] = "/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/resources/test.txt";
        options = new CommandOpts(path);
        Hangman.opts = options;

        GameState game = Hangman.initiateGame(scan);
    }

    @Test
    public void loseGame() {

    }
}
