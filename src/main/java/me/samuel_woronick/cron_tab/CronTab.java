package me.samuel_woronick.cron_tab;

import java.util.Arrays;

import com.google.gson.Gson;

import me.samuel_woronick.cron_job.CronJob;
import me.samuel_woronick.cron_job.Process;
import me.samuel_woronick.schedule.InitSchedule;
import me.samuel_woronick.schedule.Schedule;
import me.samuel_woronick.utils.Utils;

public class CronTab {
	private final Entry[] entries;
	
	public CronTab(String absPath) {
		// Read the JSON file into memory.
		String json = Utils.readFile(absPath);
		
		// Convert the JSON string into actionable POJOs.
		entries = new Gson().fromJson(json, Entry[].class);
	}
	
	public CronJob[] getCronJobs() {
		CronJob[] cronJobs = new CronJob[entries.length];
		/* Each entry corresponds to one job.
		 * Since some jobs run immediately upon cron initialization,
		 * the array of recurring jobs will be no longer than the number of entries.
		 */
		
		int index = 0;
		for(Entry entry : entries) {
			Schedule schedule = entry.getSchedule();
			Process process = new Process(entry);
			
			// If the cron job is defined to run upon initialization, do it now.
			if(schedule instanceof InitSchedule) {
				process.instantiate();
			} else {
				cronJobs[index++] = new CronJob(process, schedule);
			}
		}
		
		return Arrays.copyOf(cronJobs, index);
	}
}
