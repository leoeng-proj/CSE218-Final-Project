package v1.structs;

import java.io.Serializable;

import v1.model.Student;

public class StudentContainer implements Serializable{
	AVLTree<Student> students;
	
	public StudentContainer() {
		super();
		students = new AVLTree<>();
	}
	public void addStudent(Student s) {
		students.add(s);
	}
}
