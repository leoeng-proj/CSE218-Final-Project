package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import structs.Information;
import structs.StudentContainer;

public class Section implements Serializable, Information{
	
	private int sectionNum;
	private boolean isOnline;
	private Professor instructor;
	private Classroom room;
	private Course course;
	private StudentContainer students;
	private ArrayList<Day> daysOffered;
	private TimeRange time;
	
	public Section(int sectionNum, boolean isOnline, Classroom room, Course course, ArrayList<Day> daysOffered, TimeRange time) {
		this.sectionNum = sectionNum;
		this.isOnline = isOnline;
		this.instructor = null;
		this.room = room;
		this.course = course;
		this.daysOffered = daysOffered;
		this.time = time;
		setStudents(new StudentContainer());
	}
	public void clear() {
		students.unenrollAll(this);
		students.clear();
		if(instructor != null) {
			instructor.removeSection(this);
			instructor = null;
		}
	}
	public Course getCourse() {
		return course;
	}
	public ArrayList<Day> getDaysOffered() {
		return daysOffered;
	}
	public String getInfo() {
		return "Course:\t\t" + course +
				"\nInstructor:\t" + instructor + 
				"\nDays Offered:\t" + daysOffered +
				"\nTime:\t\t" + time + 
				"\nClassroom:\t" + room + 
				"\nStudents:\t" + students;
	}
	public Professor getInstructor() {
		return instructor;
	}
	public Classroom getRoom() {
		return room;
	}
	public int getSectionNum() {
		return sectionNum;
	}
	public StudentContainer getStudents() {
		return students;
	}
	public TimeRange getTime() {
		return time;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setInstructor(Professor instructor) {
		this.instructor = instructor;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public void setRoom(Classroom room) {
		this.room = room;
	}
	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}
	public void setStudents(StudentContainer students) {
		this.students = students;
	}
	public void setTime(TimeRange time) {
		this.time = time;
	}
	public String toString() {
		return course + " | " + sectionNum;
	}
}
