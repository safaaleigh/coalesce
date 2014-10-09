import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Coalesce {
	public static void main(String[] args) {
		CoalesceCalendar cal = new CoalesceCalendar();
		int[] startTime = {2013,10,9,9,27};
		cal.addEvent(new Event("Algorithms and Complexity", 3.0));
		int[] onDays = {0,1,2,3,4,5,6};
		int[][] onHm = {{12,0}};
		cal.addEventRule(new EventRule("Lunch", onDays, onHm));
	}
}

class CoalesceCalendar {
	Map<String,Event> scheduledEvents = new HashMap<String,Event>();
	ArrayList<EventRule> eventRules = new ArrayList<EventRule>();

	public void addEvent(Event e) {
		scheduledEvents.put(e.getID(), e);
	}

	public void removeEvent(Event e) {
		scheduledEvents.remove(e.getID());
	}

	public Event getEvent(String title) {
		return scheduledEvents.get(title);
	}

	public void addEventRule(EventRule er) {
		eventRules.add(er);
	}
}

class Event {
	private double duration;
	private boolean mutable;
	private Calendar timeBlock;
	private String title;

	public Event(String et, double dur) {
		duration = dur;
		title = et;
	}

	String getID() {
		return title;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

		return title + "\n" + sdf.format(timeBlock.getTime());
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
	private String name;

	public EventRule(String eventRuleName, int[] onDays, int[][] onHm) {
		name = eventRuleName;
		for ( int i = 0; i < onDays.length; i++ ) {
			days.add(onDays[i]);
		}
		for ( int j = 0; j < onHm.length; j++ ) {
			times.add(new HourMinute(onHm[j][0],onHm[j][1]));
		}
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