import static org.junit.Assert.*;

import cs5031.GameState;
import cs5031.Words;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The WordsTest class contains the JUnit tests for the Words class. The tests cover a range of possible inputs,
 * including when the user inputs a category option during the game initiation and when the user uploads their own
 * word source file. Both versions of the randomWord function are tested for a variety of scenarios. The
 * getSetOfWords function was explicitly left public so that it could also be tested in this class. The
 * getSetOfWords function invokes the randomize function, which is also indirectly tested. Likewise,
 * attemptToReadInWords is indirectly tested through the tests of randomWord.
 */
public class WordsTest {

    @Test
    public void getSetOfWordsOne() {
        HashSet<String> actual = Words.getSetOfWords(1);
        HashSet<String> expected = new HashSet<>(Arrays.asList(Words.words1));
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void getSetOfWordsTwo() {
        HashSet<String> actual = Words.getSetOfWords(2);
        HashSet<String> expected = new HashSet<>(Arrays.asList(Words.words2));
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void getSetOfWordsThree() {
        HashSet<String> actual = Words.getSetOfWords(3);
        HashSet<String> expected = new HashSet<>(Arrays.asList(Words.words3));
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void randomWordOne() {
        HashSet<String> actual = Words.getSetOfWords(1);
        String word = Words.randomWord(1);
        assertTrue(actual.contains(word));
    }

    @Test
    public void randomWordTwo() {
        HashSet<String> actual = Words.getSetOfWords(2);
        String word = Words.randomWord(2);
        assertTrue(actual.contains(word));
    }

    @Test
    public void randomWordThree() {
        HashSet<String> actual = Words.getSetOfWords(3);
        String word = Words.randomWord(3);
        assertTrue(actual.contains(word));
    }

    @Test
    public void randomWordRealTXT() {
        assertEquals("answer", Words.randomWord("/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/resources/test.txt"));
    }

    @Test
    public void randomWordRandomString() {
        assertEquals("", Words.randomWord("abcdefg"));
    }

    @Test
    public void randomWordIOError() {
        assertEquals("", Words.randomWord("/cs/home/dag8/Documents/David.txt"));
    }

}
