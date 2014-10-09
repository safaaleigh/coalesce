import java.util.Calendar;
import java.util.ArrayList;
import java.util.Map;

public class EventRule {
	protected ArrayList<Integer> days = new ArrayList<Integer>();
	protected ArrayList<HourMinute> times = new ArrayList<HourMinute>();
	protected double duration;
	protected String name;

	public EventRule() {
		duration = 0;
		name = null;
	}

	public EventRule(final String eventRuleName, final int[] onDays, final int[][] onTimes, final double dur) {
		name = eventRuleName;
		for ( int i = 0; i < onDays.length; i++ ) {
			days.add(onDays[i]);
		}
		for ( int j = 0; j < onTimes.length; j++ ) {
			times.add(new HourMinute(onTimes[j][0],onTimes[j][1]));
		}
		duration = dur;
	}

	public void applyRule(Map<String,Event> events, final Calendar week) {
		int i = 0;
		for (Integer d : days) {
			for (HourMinute hm : times) {
				Calendar ecal = week;
				ecal.set(Calendar.DAY_OF_WEEK, d+1);
				ecal.set(Calendar.HOUR_OF_DAY, hm.hour);
				ecal.set(Calendar.MINUTE, hm.minute);
				events.put(name + "" + i, new Event(name, ecal, duration));
				i++;
			}
		}
	}

	public String toString() {
		String result = name + " occurs on ";
		for (int i = 0; i < days.size(); i++) {
			switch(days.get(i)) {
				case 0:
					result += "Monday ";
					break;
				case 1:
					result += "Tuesday ";
					break;
				case 2:
					result += "Wednesday ";
					break;
				case 3:
					result += "Thursday ";
					break;
				case 4:
					result += "Friday ";
					break;
				case 5:
					result += "Saturday ";
					break;
				case 6:
					result += "Sunday ";
					break;
				default:
					break;
			}
		}
		result += "at ";
		for (int i = 0; i < times.size(); i++) {
			result += times.get(i).toString();
		}
		return result + "\n";
	}
}

class HourMinute {
	public final int hour;
	public final int minute;

	public HourMinute(final int h, final int m) {
		this.hour = h;
		this.minute = m;
	}

	public boolean equals (HourMinute hm) {
		if (hour == hm.hour && minute == hm.minute) {
			return true;
		}
		else return false;
	}

	public String toString() {
		return hour + ":" + minute;
	}
}