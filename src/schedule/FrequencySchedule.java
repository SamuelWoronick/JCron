package schedule;

import java.util.Date;

import utils.Utils;

public class FrequencySchedule implements Schedule {
	// FREQUENCY Mode
	private long frequency;
	
	public FrequencySchedule(String schedule) {
		try {
			// Define the schedule.
			frequency = Utils.toMillis(Integer.valueOf(schedule));
		} catch (NumberFormatException e) {
			Schedule.throwIllegalScheduleError(schedule);
		}

		// Check whether the schedule is valid.
		if (frequency < 1) {
			Schedule.throwIllegalScheduleError(schedule);
		}
	}

	@Override
	public Date nextRunDate() {
		// Calculate the date of the next run.
		return new Date(System.currentTimeMillis() + frequency);
	}
}
