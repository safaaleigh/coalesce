import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Coalesce {

	public static void main(String[] args) {
		CoalesceCalendar cal = new CoalesceCalendar();
		int[] startTime = {2013,10,9,9,27};
		Event eve = new Event("Algorithms and Complexity", 3.0, startTime);

		cal.add(eve);

		System.out.println(cal.getEvent(eve.getId()));
	}
}

class CoalesceCalendar {
	Map<UUID,Event> scheduledEvents = new HashMap<UUID,Event>();

	public boolean add(Event e) {
		scheduledEvents.put(e.getId(), e);
		return true; 
	}

	public boolean remove(Event e) {
		scheduledEvents.remove(e.getId());
		return true;
	}

	public Event getEvent(UUID id) {
		return scheduledEvents.get(id);
	}
}

class Event {
	private UUID id;
	private double duration;
	private boolean mutable;
	private Calendar timeBlock;
	private String title;

	Event(String et, double dur, int...dt) {
		id = UUID.randomUUID();
		duration = dur;
		title = et;
		timeBlock = new GregorianCalendar(dt[0], dt[1], dt[2], dt[3], dt[4]);
	}

	UUID getId() {
		return id;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

		return title + "\n" + sdf.format(timeBlock.getTime());
	}
}

/*
class Category {}

class Fixed extends Event {}

class Flexible extends Event {}

class Tentative extends Event {}

class Work extends Category {}

class School extends Category {}

class Service extends Category {}

class Family extends Category {}

class Social extends Category {}

class Personal extends Category {}
*/