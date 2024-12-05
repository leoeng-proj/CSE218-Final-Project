package model;

import java.util.HashSet;

import structs.SectionContainer;

public class Student extends Person implements Comparable<Student> {
	
	private static final HashSet<Integer> UUIDLIST = new HashSet<>();
	private static int generateUUID() {
		int id = 0;
		do{
			id = (int)(Math.random() * 10e8);
		}while(UUIDLIST.contains(id));
		UUIDLIST.add(id);
		return id;
	}
	private int id;
	private double gpa;
	
	private Major major;
	
	private SectionContainer sections;
	public Student(Name name, Major major, double gpa) {
		super(name);
		this.major = major;
		this.gpa = gpa;
		this.sections = new SectionContainer();
		id = generateUUID();
	}
	public void addSection(Section s) {
		sections.addSection(s);
	}
	public int compareTo(Student o) {
		return this.getName().compareTo(o.getName());
	}
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		if(!(other instanceof Student)) {
			return false;
		}
		Student o = (Student)other;
		return super.equals(o) && 
				this.major.equals(o.getMajor()) &&
				this.gpa == o.getGpa();
	}
	public double getGpa() {
		return gpa;
	}
	public String getInfo() {
		return "Name: \t\t" + toString() + 
				"\nID: \t\t\t" + id +
				"\nMajor: \t\t" + major +
				"\nGPA: \t\t" + String.format("%.1f", gpa) +
				"\nEnrolled Classes:\n" + sections.toString();
	}
	public Major getMajor() {
		return major;
	}
	public SectionContainer getSections() {
		return sections;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public String toString() {
		return super.toString();
	}
	public void unenroll(Section sec) {
		this.sections.remove(sec);
	}
	
}