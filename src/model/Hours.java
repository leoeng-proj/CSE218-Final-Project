package model;

public enum Hours {
	MORNING(new TimeRange(8, 0, 11, 59)),
	AFTERNOON(new TimeRange(12, 0, 16, 59)),
	EVENING(new TimeRange(17, 0, 21, 59));
	
	private TimeRange timeRange;
	
	Hours(TimeRange timeRange) {
		this.timeRange = timeRange;
	}
	public boolean contains(TimeRange tr) {
		return timeRange.contains(tr);
	}
	public TimeRange getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}
//	public String toString() {
//		return timeRange.toString();
//	}
}
