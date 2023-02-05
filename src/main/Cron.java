package main;

import java.util.Arrays;

import cron_job.CronJob;
import cron_tab.CronTab;

public class Cron {
	public static void main(String[] args) {
		if (args.length != 1) {
			throw new RuntimeException("JCron requires one argument, the absolute path to the cron table.");
		}
		
		// This is the absolute path to the cron table.
		String absPath = args[0];
		
		// Get cron jobs from the cron table.
		CronJob[] cronJobs = new CronTab(absPath).getCronJobs();
		
		// Sort the list such that the nearest run dates are first.
		Arrays.sort(cronJobs);
		
		try {
			// This expression must be evaluated because it is possible for the array to be empty.
			while (cronJobs.length > 0) {
				// Run all jobs that are ready to run.
				runJobs(cronJobs);
				
				// Sort the list.
				Arrays.sort(cronJobs);

				// If there is a job to run, continue to the next iteration of the loop.
				// Otherwise, sleep the thread until the next job is ready to run.
				long nextRunDate = cronJobs[0].getRunDate().getTime();
				long currentDate = System.currentTimeMillis();
				
				if (nextRunDate <= currentDate) {
					continue;
				} else {
					Thread.sleep(nextRunDate - currentDate);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void runJobs(CronJob[] cronJobs) {
		for (CronJob cronJob : cronJobs) {
			long runDate = cronJob.getRunDate().getTime();
			long currentDate = System.currentTimeMillis();
			
			// If the job is ready to run, do it.
			// Otherwise, return because there are no jobs ready to run right now.
			if (runDate <= currentDate) {
				cronJob.run();
			} else {
				return;
			}
		}
	}
}
