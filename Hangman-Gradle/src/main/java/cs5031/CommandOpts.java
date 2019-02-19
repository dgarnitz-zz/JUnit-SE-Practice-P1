package cs5031;

import java.io.File;

public class CommandOpts {

	public int maxguesses;
	public int maxhints;

	public String wordsource;
	
	//TODO write a test(s) for this constructor method - it should make sure maxguesses = 10 and maxhints = 2
	public CommandOpts(String[] args) {
		maxguesses = 10;
		maxhints = 2;
		
		wordsource = "";

		processArguments(args);
	}

	public void processArguments(String[] args) {
		for(int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guessesMade")) {
				maxguesses = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equals("--hints")) {
				maxhints = Integer.parseInt(args[i+1]);
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

	public Boolean checkWordSource(String source) {
		File checkPath = new File(source);
		if(checkPath.exists() && !checkPath.isDirectory()) {
			return true;
		}
		return false;
	}
}
