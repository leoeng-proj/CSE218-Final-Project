package v1.model;

import v1.structs.ListBag;

public class Section {
	
	private int sectionNum;
	private boolean isOnline;
	private Professor instructor;
	private Classroom room;
	private Course course;
	private ListBag<String> textbooks;
	private ListBag<Integer> studentIDs;
	private Day[] daysOffered;
	private Hours time;
	
	public Section(int sectionNum, boolean isOnline, Classroom room, Course course, ListBag<String> textbooks, Day[] daysOffered, Hours time) {
		this.sectionNum = sectionNum;
		this.isOnline = isOnline;
		this.instructor = null;
		this.room = room;
		this.course = course;
		this.textbooks = textbooks;
		this.daysOffered = daysOffered;
		this.time = time;
	}
	public int getSectionNum() {
		return sectionNum;
	}
	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public Professor getInstructor() {
		return instructor;
	}
	public void setInstructor(Professor instructor) {
		this.instructor = instructor;
		instructor.addSection(this);
	}
	public Classroom getRoom() {
		return room;
	}
	public void setRoom(Classroom room) {
		this.room = room;
	}
	public Course getCourse() {
		return course;
	}
	public ListBag<String> getTextbooks() {
		return textbooks;
	}
	public void setTextbooks(ListBag<String> textbooks) {
		this.textbooks = textbooks;
	}
	public ListBag<Integer> getStudentIDs() {
		return studentIDs;
	}
	public void setStudentIDs(ListBag<Integer> studentIDs) {
		this.studentIDs = studentIDs;
	}
	public Day[] getDaysOffered() {
		return daysOffered;
	}
	public void setDaysOffered(Day[] daysOffered) {
		this.daysOffered = daysOffered;
	}
	public Hours getTime() {
		return time;
	}
	public void setTime(Hours time) {
		this.time = time;
	}
	public String toString() {
		return course + " | " + sectionNum;
	}
}
