package cron_job;

import java.util.Date;

import schedule.Schedule;

public class CronJob implements Comparable<CronJob> {
	private Process process;
	private Schedule schedule;
	private Date runDate;
	
	public CronJob(Process process, Schedule schedule) {
		this.process = process;
		this.schedule = schedule;
		
		// Schedule the next run.
		this.runDate = schedule.nextRunDate();
	}
	
	public void run() {
		// Run the job.
		process.instantiate();
		
		// Print the running job.
		System.out.println(runDate + " " + this);
		
		// Schedule the next run.
		runDate = schedule.nextRunDate();
	}
	
	// Getter
	public Date getRunDate() {
		return runDate;
	}
	
	@Override
	public int compareTo(CronJob job) {
		return runDate.compareTo(job.getRunDate());
	}
	
	@Override
	public String toString() {
		return process.toString();
	}
}
