package cron_job;

import java.io.IOException;
import java.util.ArrayList;

import cron_tab.Entry;

public class Process {
	private ProcessBuilder processBuilder;
	private ArrayList<String> command;
	
	public Process(Entry entry) {
		// Increment by one to include the job itself.
		int numEntires = entry.getArguments().length + 1;
		
		// This is defined as ProcessBuilder defines it internally.
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
		// This method can be reused.
		
		try {
			// Instantiate the process.
			processBuilder.start();
		} catch(IOException e) {
			throw new RuntimeException("JCron: \"" +  this + "\" has failed: " + e.toString());
		}
	}

	@Override
	public String toString() {
		return command.toString();
	}
}
