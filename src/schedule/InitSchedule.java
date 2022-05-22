package schedule;

import java.util.Date;

public class InitSchedule implements Schedule {
	// INIT Mode
	
	@Override
	public Date nextRunDate() {
		return new Date();
	}

}
