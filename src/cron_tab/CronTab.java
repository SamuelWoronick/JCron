package cron_tab;

import java.util.Arrays;

import com.google.gson.Gson;

import main.CronJob;
import schedule.InitSchedule;
import utils.Utils;

public class CronTab {
	private Entry[] entries;
	
	public CronTab(String absPath) {
		// Read the JSON file into memory.
		String json = Utils.readFile(absPath);
		
		// Convert the JSON string into actionable POJOs.
		entries = new Gson().fromJson(json, Entry[].class);
	}
	
	public CronJob[] getCronJobs() {
		CronJob[] cronJobs = new CronJob[entries.length];
		
		int index = 0;
		for(Entry entry : entries) {
			CronJob cronJob = new CronJob(entry);
			
			// If the cron job is defined to run upon initialization, do it now.
			if(entry.getSchedule() instanceof InitSchedule) {
				cronJob.run();
			} else {
				cronJobs[index++] = cronJob;
			}
		}
		
		return Arrays.copyOf(cronJobs, index);
	}
}
