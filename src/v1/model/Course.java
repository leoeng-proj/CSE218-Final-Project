package v1.model;

import java.io.Serializable;

public class Course implements Comparable<Course>, Serializable{

	private double credits;
	private String name;
	private String description;
	private String courseNum;
	private Major reqMajors;
	
	public Course(double credits, String name, String description, String courseNum, Major reqMajors) {
		this.credits = credits;
		this.name = name;
		this.description = description;
		this.courseNum = courseNum;
		this.reqMajors = reqMajors;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public Major getReqMajors() {
		return reqMajors;
	}
	public void setReqMajors(Major reqMajors) {
		this.reqMajors = reqMajors;
	}
	public String toString() {
		return courseNum;
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
}
