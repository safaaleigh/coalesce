import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Coalesce {
	public static void main(String[] args) {
		CoalesceCalendar coacal = new CoalesceCalendar();
		int[] startTime = {2013,10,9,9,27};
		coacal.addEvent(new Event("Algorithms and Complexity", 3.0));
		int[] onDays = {0,1,2,3,4,5,6};
		int[][] onTimes = {{12,0}};
		coacal.addEventRule(new EventRule("Lunch", onDays, onTimes, 1));
		coacal.generate();

		System.out.println(coacal);
	}
}

class CoalesceCalendar {
	Map<String,Event> scheduledEvents = new HashMap<String,Event>();
	ArrayList<EventRule> eventRules = new ArrayList<EventRule>();
	Calendar week;

	public CoalesceCalendar() {
		week = Calendar.getInstance();
	}
	public void addEvent(Event e) {
		scheduledEvents.put(e.getTitle(), e);
	}

	public void removeEvent(Event e) {
		scheduledEvents.remove(e.getTitle());
	}

	public Event getEvent(String title) {
		return scheduledEvents.get(title);
	}

	public void addEventRule(EventRule er) {
		eventRules.add(er);
	}

	public void generate() {
		for (EventRule er : eventRules) {
			er.applyRule(scheduledEvents, week);
		}
	}

	public String toString() {
		String result= "Calendar this week:\n";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (Event e : scheduledEvents.values()) {
			result += e.getTitle() + " on " + dateFormat.format(e.getStart().getTime()) + (e.getDuration() != 0 ? " for " + e.getDuration() + " hour(s)" : "") + ".\n";
		}
		return result;
	}
}

class Event {
	private boolean mutable;
	private Calendar startTimeAndDay;
	private double duration;
	private String title;

	public Event(String et, double dur) {
		startTimeAndDay = Calendar.getInstance();
		duration = dur;
		title = et;
	}

	public Event(String et, Calendar st, double dur) {
		title = et;
		startTimeAndDay = st;
		duration = dur;
	}

	public String getTitle() {
		return title;
	}

	public double getDuration() {
		return duration;
	}

	public Calendar getStart() {
		return startTimeAndDay;
	}

	public String toString() {
		return "";
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


class EventRule {
	private ArrayList<Integer> days = new ArrayList<Integer>();
	private ArrayList<HourMinute> times = new ArrayList<HourMinute>();
	private double duration;
	private String name;

	public EventRule(String eventRuleName, int[] onDays, int[][] onTimes, double dur) {
		name = eventRuleName;
		for ( int i = 0; i < onDays.length; i++ ) {
			days.add(onDays[i]);
		}
		for ( int j = 0; j < onTimes.length; j++ ) {
			times.add(new HourMinute(onTimes[j][0],onTimes[j][1]));
		}
		duration = dur;
	}

	public void applyRule(Map<String,Event> events, Calendar week) {
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

/*
class Category {}

class Flexible extends Event {}

class Tentative extends Event {}

class Work extends Category {}

class School extends Category {}

class Service extends Category {}

class Family extends Category {}

class Social extends Category {}

class Personal extends Category {}
*/