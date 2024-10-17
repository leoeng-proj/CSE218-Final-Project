package v1.structs;

import java.io.Serializable;
import java.util.LinkedList;

import v1.model.Student;

public class StudentContainer implements Serializable{
	private LinkedList<Student> students;
	
	public StudentContainer() {
		super();
		students = new LinkedList<>();
	}
	public void addStudent(Student s) {
		students.add(s);
	}
	public Student[] toArray() {
		return students.toArray(new Student[0]);
	}
}