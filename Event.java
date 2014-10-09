import java.util.Calendar;

public class Event {
	protected boolean movable;
	protected Calendar startTimeAndDay;
	protected double duration;
	protected String title;

	public Event() {
		movable = false;
		startTimeAndDay = null;
		duration = 0;
		title = null;
	}

	public Event(final String et, final double dur) {
		movable = false;
		startTimeAndDay = Calendar.getInstance();
		duration = dur;
		title = et;
	}

	public Event(final String et, final Calendar st, final double dur) {
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