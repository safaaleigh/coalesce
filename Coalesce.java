import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Coalesce {
	public static void main(String[] args) {
		CoalesceCalendar coacal = new CoalesceCalendar();
		int[] startTime = {2013,10,9,9,27};
		coacal.addEvent(new FixedEvent("Algorithms and Complexity", 3.0));
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

/*
class Category {}

class Tentative extends Event {}

class Work extends Category {}

class School extends Category {}

class Service extends Category {}

class Family extends Category {}

class Social extends Category {}

class Personal extends Category {}
*/