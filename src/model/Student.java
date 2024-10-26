package model;

import structs.AVLTree;

public class Student extends Person implements Comparable<Student> {
	
	private int id;
	private double gpa;
	private Major major;
	
	private static final AVLTree<Integer> UUIDLIST = new AVLTree<>();
	
	public Student(Name name, Major major, double gpa) {
		super(name);
		this.major = major;
		this.gpa = gpa;
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
	private static int generateUUID() {
		int id = 0;
		do{
			id = (int)(Math.random() * 10e8);
		}while(UUIDLIST.contains(id));
		UUIDLIST.add(id);
		return id;
	}
}