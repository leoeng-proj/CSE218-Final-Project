package model;

import structs.AVLTree;
import structs.SectionContainer;

public class Student extends Person implements Comparable<Student> {
	
	private int id;
	private double gpa;
	private Major major;
	private SectionContainer sections;
	
	private static final AVLTree<Integer> UUIDLIST = new AVLTree<>();
	
	public Student(Name name, Major major, double gpa) {
		super(name);
		this.major = major;
		this.gpa = gpa;
		this.sections = new SectionContainer();
		id = generateUUID();
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public SectionContainer getSections() {
		return sections;
	}
	public void addSection(Section s) {
		sections.addSection(s);
	}
	public void unenroll(Section sec) {
		this.sections.remove(sec);
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
	public String toString() {
		return super.toString();
	}
	public String getInfo() {
		return "Name: \t\t" + toString() + 
				"\nID: \t\t\t" + id +
				"\nMajor: \t\t" + major +
				"\nGPA: \t\t" + String.format("%.1f", gpa) +
				"\nEnrolled Classes:\n" + sections.toString();
	}
	private static int generateUUID() {
		int id = 0;
		do{
			id = (int)(Math.random() * 10e8);
		}while(UUIDLIST.contains(id));
		UUIDLIST.add(id);
		return id;
	}
	
}