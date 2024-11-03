package structs;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Section;
import model.Student;

public class StudentContainer implements Serializable, Removal{
	private LinkedList<Student> students;
	
	public StudentContainer() {
		super();
		students = new LinkedList<>();
	}
	public StudentContainer(StudentContainer other) {
		super();
		students = (LinkedList<Student>)other.students.clone();
	}
	public void addStudent(Student s) {
		students.add(s);
	}
	public void clear() {
		students.clear();
	}
	public ObservableList<Student> getObservableStudentContainer(){
		return FXCollections.observableArrayList(toArray());
	}
	public boolean remove(Object obj) {
		return students.remove(obj);
	}
	public Student[] toArray() {
		return students.toArray(new Student[0]);
	}
	public String toString() {
		return students.toString();
	}
	public void trim(Predicate<Student> pred) {
		Iterator<Student> iter = students.iterator();
		while(iter.hasNext()) {
			Student s = iter.next();
			if(pred.test(s)) {
				iter.remove();
			}
		}
	}
	public void unenrollAll(Section sec) {
		Iterator<Student> iter = students.iterator();
		while(iter.hasNext()) {
			Student s = iter.next();
			s.unenroll(sec);
		}
	}
}
