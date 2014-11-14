import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class Coalesce {
	public static void main(String[] args) {
		CoalesceCalendar coacal = new CoalesceCalendar();

		// add a few Events
		coacal.addEvent(new Event("Meeting with President", new GregorianCalendar(2013,10,9,9,30), 3.0, false));

		// add a few EventRules
		int[][] onTimes = {{12,0}};
		coacal.addEventRule(new EventRule("Lunch", DayCode.ALL, onTimes, 1, false));
		onTimes = new int[][] {{9,0}};
		coacal.addEventRule(new EventRule("Probability", DayCode.MWF, onTimes, 1, false));
		onTimes = new int[][] {{10,0}};
		coacal.addEventRule(new EventRule("Object Oriented Programming", DayCode.MWF, onTimes, 1, false));
		onTimes = new int[][] {{9,30}};
		coacal.addEventRule(new EventRule("Algorithms and Complexity", DayCode.TTH, onTimes, 1, false));
		onTimes = new int[][] {{12,30}};
		coacal.addEventRule(new EventRule("Architecture and Society", DayCode.TTH, onTimes, 1, false));

		coacal.generate();
		System.out.println(coacal);
	}
}

class CoalesceCalendar {
	Map<String,Event> scheduledEvents = new HashMap<String,Event>();
	ArrayList<EventRule> eventRules = new ArrayList<EventRule>();
	ArrayList<Event> unscheduledEvents = new ArrayList<Event>();
	Calendar week;

	public CoalesceCalendar() {
		week = Calendar.getInstance();
	}
	public void addEvent(Event e) {
		unscheduledEvents.add(e);
	}

	public void removeScheduledEvent(Event e) {
		scheduledEvents.remove(e.getTitle());
	}

	public Event getScheduledEvent(String title) {
		return scheduledEvents.get(title);
	}

	public void addEventRule(EventRule er) {
		eventRules.add(er);
	}

	public void generate() {
		//TODO check for collisions
		for (EventRule er : eventRules) {
			er.applyRule(scheduledEvents, week);
		}
		for (Event e : unscheduledEvents) {
			scheduledEvents.put(e.getTitle(), e);
		}
		unscheduledEvents.clear();
	}

	public String toString() {
		List<Event> eventOrderByTime = new ArrayList<Event>(scheduledEvents.values());
		Collections.sort(eventOrderByTime, new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				return e1.getStart().compareTo(e2.getStart());
			}
		});
		String result= "Calendar this week:\n";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (Event e : eventOrderByTime) {
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