package cs5031;

import java.io.File;

/**
 * The CommandOpts class contains the game's main configuration options such as the maximum number of guesses and hints.
 * It also track a word source, should one be supplied by the user. It uses a series of private methods that parse the
 * command line input and decide how to configure the object. This method includes a considerable amount of error
 * handling for the different faulty scenarios that can occur.
 */
public class CommandOpts {

	public int maxguesses;
	public int maxhints;

	public String wordsource;

	public CommandOpts(String[] args) {
		maxguesses = 10;
		maxhints = 2;
		
		wordsource = "";

		processArguments(args);
	}

	private void processArguments(String[] args) {
		if(args == null) {
			return;
	}
		for(int i = 0; i < args.length; ++i) {
			if(args[i] == null) {
				continue;
			} else if (args[i].equals("--guessesMade")) {
				maxguesses = parseGuess(args[i+1]);
				i++;
			}
			else if (args[i].equals("--hints")) {
				maxhints = parseHints(args[i+1]);
				i++;
			}
			else {
				if(checkWordSource(args[i])) {
					wordsource = args[i];
				} else {
					System.out.println("Please enter valid word source");
				}
			}
		}
	}

	private Boolean checkWordSource(String source) {
		File checkPath = new File(source);
		if(checkPath.exists() && !checkPath.isDirectory()) {
			return true;
		}
		return false;
	}

	private int parseGuess(String arg) {
		if(arg != null) {
			try {
				return Integer.parseInt(arg);
			} catch (NumberFormatException e) {
				return maxguesses;
			}
		}
		return maxguesses;
	}

	private int parseHints(String arg) {
		if(arg != null) {
			try {
				return Integer.parseInt(arg);
			} catch (NumberFormatException e) {
				return maxhints;
			}
		}
		return maxhints;
	}
}
