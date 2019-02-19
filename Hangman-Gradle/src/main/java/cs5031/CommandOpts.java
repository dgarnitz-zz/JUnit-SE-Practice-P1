package cs5031;

import java.io.File;

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

	public void processArguments(String[] args) {
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
