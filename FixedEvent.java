import java.util.Calendar;

public class FixedEvent extends Event {
	public FixedEvent() {
		super();
		movable = false;
	}

	public FixedEvent(final String et, final double dur) {
		super(et, dur);
		movable = false;
	}

	public FixedEvent(final String et, final Calendar st, final double dur) {
		super(et, st, dur);
		movable = false;
	}
}
