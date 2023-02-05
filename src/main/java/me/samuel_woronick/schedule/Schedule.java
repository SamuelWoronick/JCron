package me.samuel_woronick.schedule;

import java.util.Date;

public interface Schedule {
	// This method returns the date of the next run.
	Date nextRunDate();
	
	// Define standard error messages.
	public static void throwIllegalScheduleError(String schedule) {
		throw new IllegalArgumentException("JCron: Illegal schedule: \"" + schedule + "\"");
	}
	
	public static void throwUndefinedScheduleError() {
		throw new IllegalArgumentException("JCron: All schedules must be defined.");
	}
}
