package me.samuel_woronick.cron_tab;

import me.samuel_woronick.schedule.FrequencySchedule;
import me.samuel_woronick.schedule.InitSchedule;
import me.samuel_woronick.schedule.Schedule;
import me.samuel_woronick.schedule.TimeInDaySchedule;

public class Entry {
	private String job;
	private String[] arguments;
	private String schedule;
	
	public String getJob() {
		return job;
	}
	public String[] getArguments() {
		return arguments;
	}
	public Schedule getSchedule() {
		// If schedule is not defined, throw an exception.
		if(schedule == null || schedule.length() == 0) {
			Schedule.throwUndefinedScheduleError();
		}
		
		// INIT Mode
		if(schedule.equals("init")) {
			return new InitSchedule();
		}
		
		// TIME_IN_DAY Mode
		if (schedule.contains(":")) {
			return new TimeInDaySchedule(schedule);
		}
		
		// FREQUENCY Mode
		return new FrequencySchedule(schedule);
	}
}
