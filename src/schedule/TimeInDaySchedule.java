package schedule;

import java.util.Calendar;
import java.util.Date;

import utils.Utils;

public class TimeInDaySchedule implements Schedule {
	// TIME_IN_DAY Mode
	private int[] timeInDay;
	private Date lastRunDate;

	public TimeInDaySchedule(String schedule) {
		try {
			// Define the schedule.
			String[] temp = schedule.split(":");
			timeInDay = new int[2];
			timeInDay[0] = Integer.valueOf(temp[0]);
			timeInDay[1] = Integer.valueOf(temp[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("JCron: Illegal schedule: \"" + schedule + "\"");
		}

		// Check whether the schedule is valid.
		for (Integer timeUnit : timeInDay) {
			if (timeUnit < 0) {
				throw new IllegalArgumentException("JCron: Illegal schedule: \"" + schedule + "\"");
			}
		}
	}

	@Override
	public Date nextRunDate() {
		// Rather conveniently, we can increment by one day if the job has been run previously.
		if (lastRunDate == null) {
			// Calculate the date of the next run.
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, timeInDay[0]);
			calendar.set(Calendar.MINUTE, timeInDay[1]);
			calendar.set(Calendar.SECOND, 0);

			// If the date of the next run already passed, increment by one day.
			if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
				return lastRunDate = new Date(calendar.getTimeInMillis() + Utils.MILLIS_PER_DAY);
			} else {
				return lastRunDate = new Date(calendar.getTimeInMillis());
			}
		} else {
			return lastRunDate = new Date(lastRunDate.getTime() + Utils.MILLIS_PER_DAY);
		}

	}
}
