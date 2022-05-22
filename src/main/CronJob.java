package main;

import java.io.IOException;
import java.util.Date;

import cron_tab.Entry;
import schedule.Schedule;

public class CronJob implements Comparable<CronJob> {
	// These should look familiar.
	private String job;
	private String arguments;
	private Schedule schedule;
	// This will be used to instantiate the process.
	private ProcessBuilder process;
	// This is the date of the next run.
	private Date runDate;
	
	public CronJob(Entry entry) {
		this.job = entry.getJob();
		this.arguments = entry.getArguments();
		this.schedule = entry.getSchedule();
		this.process = new ProcessBuilder(job, arguments);
		
		// Schedule the next run.
		runDate = schedule.nextRunDate();
	}
	
	public void run() {
		try {
			// Instantiate the process.
			process.start();
			
			// Verbosity for those that want it.
			System.out.println(runDate + " " + this);
		} catch(IOException e) {
			throw new RuntimeException("JCron: \"" +  this + "\" has failed: " + e.toString());
		}
		
		// Schedule the next run.
		runDate = schedule.nextRunDate();
	}
	
	// Getters
	public Date getRunDate() {
		return runDate;
	}
	
	@Override
	public int compareTo(CronJob job) {
		return runDate.compareTo(job.getRunDate());
	}
	
	@Override
	public String toString() {
		return job + (arguments == null ? "" : " " + arguments);
	}
}
