import java.util.Calendar;
import java.util.ArrayList;
import java.util.Map;

public class FixedEventRule extends EventRule {
	public FixedEventRule() {
		super();
	}

	public FixedEventRule(final String eventRuleName, final int[] onDays, final int[][] onTimes, final double dur) {
		super(eventRuleName, onDays, onTimes, dur);
	}

	public void applyRule(Map<String,Event> events, final Calendar week) {
		int i = 0;
		for (Integer d : days) {
			for (HourMinute hm : times) {
				Calendar ecal = week;
				ecal.set(Calendar.DAY_OF_WEEK, d+1);
				ecal.set(Calendar.HOUR_OF_DAY, hm.hour);
				ecal.set(Calendar.MINUTE, hm.minute);
				events.put(name + "" + i, new FixedEvent(name, ecal, duration));
				i++;
			}
		}
	}
}