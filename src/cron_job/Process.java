package cron_job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cron_tab.Entry;

public class Process {
	private final ProcessBuilder processBuilder;
	// This object formats the date according to ISO 8601.
	private final SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	// This is the representation of the command that ProcessBuilder needs.
	private final ArrayList<String> command;
	// This is the pretty representation of the command.
	private String prettyCommand;
	
	public Process(Entry entry) {
		// Increment by one to include the job itself.
		int numEntires = entry.getArguments().length + 1;
		
		// This is defined how ProcessBuilder defines it internally.
		command = new ArrayList<String>(numEntires);
		// Populate the ArrayList.
		command.add(entry.getJob());
		for(String argument : entry.getArguments()) {
			command.add(argument);
		}
		
		// Instantiate ProcessBuilder with the given command.
		processBuilder = new ProcessBuilder(command);
	}
	
	public void instantiate() {
		try {
			// Instantiate the process.
			processBuilder.start();
			
			// Print a notification with the date the job was run.
			System.out.println(isoDate.format(new Date()) + "\t" + toString());
		} catch(IOException e) {
			throw new RuntimeException("JCron: \"" +  this + "\" has failed: " + e);
		}
	}

	@Override
	public String toString() {
		// If the pretty representation has already been generated, return it.
		if(prettyCommand == null) {
			StringBuilder strBuilder = new StringBuilder();
			
			// The first element is the job, so if it is not defined, throw an exception.
			if(command.size() == 0) {
				throw new RuntimeException("JCron: Job not defined.");
			} else {
				strBuilder.append(command.get(0));
			}
			
			// The remaining elements, if there are any, are arguments.
			for(int i = 1; i < command.size(); i++) {
				strBuilder.append(" ");
				strBuilder.append(command.get(i));
			}
			
			// Return the result.
			return prettyCommand = strBuilder.toString();
		} else {
			return prettyCommand;
		}
	}
}
