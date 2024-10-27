package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

import structs.StudentContainer;

public class Section implements Serializable{
	
	private int sectionNum;
	private boolean isOnline;
	private Professor instructor;
	private Classroom room;
	private Course course;
	private LinkedList<String> textbooks;
	private StudentContainer students;
	private Day[] daysOffered;
	private Hours time;
	
	public Section(int sectionNum, boolean isOnline, Classroom room, Course course, LinkedList<String> textbooks, Day[] daysOffered, Hours time) {
		this.sectionNum = sectionNum;
		this.isOnline = isOnline;
		this.instructor = null;
		this.room = room;
		this.course = course;
		this.textbooks = textbooks;
		this.daysOffered = daysOffered;
		this.time = time;
		setStudents(new StudentContainer());
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
	public LinkedList<String> getTextbooks() {
		return textbooks;
	}
	public void setTextbooks(LinkedList<String> textbooks) {
		this.textbooks = textbooks;
	}
	public StudentContainer getStudents() {
		return students;
	}
	public void setStudents(StudentContainer students) {
		this.students = students;
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
	public String getInfo() {
		return "Course:\t\t" + getCourse() +
				"\nInstructor:\t" + getInstructor() + 
				"\nDays Offered:\t" + Arrays.toString(getDaysOffered()) +
				"\nTime:\t\t" + getTime() + 
				"\nClassroom:\t" + room + 
				"\nTextbook(s):\t" + getTextbooks() +
				"\nStudents:\t" + students.toString();
	}
	public void clear() {
		students.unenrollAll(this);
		students.clear();
	}
}
