package cron_tab;

import schedule.FrequencySchedule;
import schedule.InitSchedule;
import schedule.Schedule;
import schedule.TimeInDaySchedule;

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
