package structs;

import java.io.Serializable;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Course;

public class CourseContainer implements Serializable, Removal<Course>{

	private LinkedList<Course> courses;
	
	public CourseContainer() {
		super();
		courses = new LinkedList<>();
	}
	
	public boolean addCourse(Course course) {
		if(courses.contains(course)) {
			return false;
		}
		courses.add(course);
		return true;
	}
	public ObservableList<Course> getObservableCourseContainer(){
		return FXCollections.observableList(courses);
	}
	public Course getRandomCourse() {
		return courses.get((int)(Math.random()*courses.size()));
	}
	public boolean isEmpty() {
		return courses.isEmpty();
	}
	public boolean remove(Course obj) {
		return courses.remove(obj);
	}
	public Course[] toArray() {
		return courses.toArray(new Course[0]);
	}
}
