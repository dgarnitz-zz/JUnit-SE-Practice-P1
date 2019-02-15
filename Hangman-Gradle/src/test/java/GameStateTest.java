import static org.junit.Assert.*;

import cs5031.GameState;
import org.junit.Assert;
import org.junit.Test;

//TODO rewrite this test
public class GameStateTest {

	@Test
	public void showWord() {
		String testWord = "Test";
		GameState testGame = new GameState(testWord, 20, 20);
		assertEquals(testWord, testGame.word);
	}
	
	@Test
	public void test() {
		Assert.fail("Not yet implemented");
	}

}

