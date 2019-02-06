import static org.junit.Assert.*;

import org.junit.Test;
import uk.ac.standrews.cs5031.CommandOpts;

//TODO rewrite this test
public class CommandOptsTest {

	@Test
	public void optionsTest() {
		String[] args = { "--guessesMade", "2", "--hints", "4", "words.txt" };
		CommandOpts opts = new CommandOpts(args);
		assertEquals(opts.maxguesses, 2);
		assertEquals(opts.maxhints, 4);
		assertEquals(opts.wordsource, "words.txt");
	}

}
