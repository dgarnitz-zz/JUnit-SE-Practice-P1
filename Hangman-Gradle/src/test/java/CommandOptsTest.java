import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import cs5031.CommandOpts;


public class CommandOptsTest {
	private String defaultGuessesIndicator = "--guessesMade";
	private String defaultGuesses = "8";
	private String defaultHintsIndicator = "--hints";
	private String defaultHints = "2";
	private String correctFilePath = "/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/java/cs5031/Words.java";

	@Test
	public void optionsDefault() {
		String[] args = { defaultGuessesIndicator, defaultGuesses, defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 8);
		Assert.assertEquals(opts.maxhints, 2);
		Assert.assertEquals(opts.wordsource, correctFilePath);
	}

	@Test
	public void optionsIncorrectFilePath() {
		String[] args = { defaultGuessesIndicator, defaultGuesses, defaultHintsIndicator, defaultHints, "words.txt" };
		CommandOpts opts = new CommandOpts(args);
		TestCase.assertEquals(opts.wordsource, "");
	}

	@Test
	public void optionsDirectoryInsteadOfFile() {
		String[] args = { defaultGuessesIndicator, defaultGuesses, defaultHintsIndicator, defaultHints, "/cs/home/dag8/Documents/SE-Practices/Hangman-Gradle/src/main/java/cs5031" };
		CommandOpts opts = new CommandOpts(args);
		TestCase.assertEquals(opts.wordsource, "");
	}

	@Test
	public void optionsIncorrectGuessesIndicator() {
		String[] args = { "guessesMade", defaultGuesses, defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}

	@Test
	public void optionsNullGuessesIndicator() {
		String[] args = { null, defaultGuesses, defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}

	@Test
	public void optionsEmptyGuessesIndicator() {
		String[] args = { "", defaultGuesses, defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}

	@Test
	public void optionsNullGuesses() {
		String[] args = { defaultGuessesIndicator, null, defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}

	@Test
	public void optionsEmptyGuesses() {
		String[] args = { defaultGuessesIndicator, "", defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}

	@Test
	public void optionsSpacesGuesses() {
		String[] args = { defaultGuessesIndicator, " ", defaultHintsIndicator, defaultHints, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}

	@Test
	public void optionsNullHintsAndGuesses() {
		String[] args = { defaultGuessesIndicator, null, defaultHintsIndicator, null, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
	}
	@Test
	public void optionsNullHints() {
		String[] args = { defaultGuessesIndicator, defaultGuesses, defaultHintsIndicator, null, correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxhints, 2);
	}

	@Test
	public void optionsEmptyHints() {
		String[] args = { defaultGuessesIndicator, defaultGuesses, defaultHintsIndicator, "", correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxhints, 2);
	}

	@Test
	public void optionsSpacesHints() {
		String[] args = { defaultGuessesIndicator, defaultGuesses, defaultHintsIndicator, " ", correctFilePath };
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxhints, 2);
	}

	@Test
	public void optionsNoArgs() {
		String[] args = {};
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
		Assert.assertEquals(opts.maxhints, 2);
		Assert.assertEquals(opts.wordsource, "");
	}

	@Test
	public void optionsNullArgs() {
		String[] args = null;
		CommandOpts opts = new CommandOpts(args);
		Assert.assertEquals(opts.maxguesses, 10);
		Assert.assertEquals(opts.maxhints, 2);
		Assert.assertEquals(opts.wordsource, "");
	}
}
