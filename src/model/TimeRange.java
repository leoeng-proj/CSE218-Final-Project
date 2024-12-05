package model;
import java.io.Serializable;
import java.time.LocalTime;

public class TimeRange implements Serializable{
	private LocalTime startTime;
	private LocalTime endTime;
	public TimeRange(int h1, int m1, int h2, int m2) {
		startTime = LocalTime.of(h1, m1);
		endTime = LocalTime.of(h2, m2);
	}
	public boolean contains(TimeRange tr) {
		return startTime.isBefore(tr.getStartTime()) && endTime.isAfter(tr.getEndTime());
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public String toString() {
		return startTime + "-" + endTime;
	}
	
}
