package model;

import java.io.Serializable;

import structs.Information;

public class Course implements Comparable<Course>, Serializable, Information{

	private double credits;
	private String name;
	private String description;
	private String courseNum;
	private Major major;
	
	public Course(double credits, String name, String description, String courseNum, Major major) {
		this.credits = credits;
		this.name = name;
		this.description = description;
		this.courseNum = courseNum;
		this.major = major;
	}
	public int compareTo(Course o) {
		return this.getCourseNum().compareTo(o.getCourseNum());
	}
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof Course)) {
			return false;
		}
		Course c = (Course)o;
		return this.getCourseNum().equals(c.getCourseNum());
	}
	public String getCourseNum() {
		return courseNum;
	}
	public double getCredits() {
		return credits;
	}
	public String getDescription() {
		return description;
	}
	public String getInfo() {
		return "Course Name:\t\t" + name +
				"\nCourse Number:\t" + courseNum +
				"\nRequired Majors:\t" + major +
				"\nCredits:\t\t\t" + String.format("%.1f", credits) +
				"\nDescription:\n" + description;
	}
	public String getName() {
		return name;
	}
	public Major getMajor() {
		return major;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setReqMajors(Major reqMajors) {
		this.major = reqMajors;
	}
	public String toString() {
		return courseNum;
	}
}
