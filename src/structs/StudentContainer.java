package structs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.Section;
import model.Student;

public class StudentContainer implements Serializable, Removal<Student>{
	private TreeMap<Integer, Student> students;
	
	public StudentContainer() {
		super();
		students = new TreeMap<>();
	}
	public StudentContainer(StudentContainer other) {
		super();
		students = (TreeMap<Integer,Student>)other.students.clone();
	}
	public void addStudent(Student s) {
		students.put(s.getId(), s);
	}
	public void clear() {
		students.clear();
	}
	public ObservableList<Student> getObservableStudentContainer(){
		Collection<Student> col = students.values();
		return FXCollections.observableArrayList(col.toArray(new Student[0]));
	}
	public boolean remove(Student stu) {
		return students.remove(stu.getId()) != null;
	}
	public String toString() {
		return students.toString();
	}
	public StudentContainer trim(Predicate<Student> pred) {
		StudentContainer trimmed = new StudentContainer();
		students.forEach((id, stu) -> {
			if(!pred.test(stu)) {
				trimmed.addStudent(stu);
			}
		});
		return trimmed;
	}
	public void unenrollAll(Section sec) {
		students.forEach((id, stu) -> {
			stu.unenroll(sec);
		});
	}
}
