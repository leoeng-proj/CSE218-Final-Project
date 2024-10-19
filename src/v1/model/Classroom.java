package v1.model;

import v1.structs.SectionContainer;

public class Classroom {
	
	private String roomID;
	private int maxCapacity;
	private SectionContainer sections;
	
	public Classroom(String roomID, int maxCapacity) {
		super();
		this.roomID = roomID;
		this.maxCapacity = maxCapacity;
		this.sections = new SectionContainer();
	}
	public String getRoomID() {
		return roomID;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public SectionContainer getSections() {
		return sections;
	}
}
