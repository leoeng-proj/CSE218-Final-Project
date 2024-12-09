package structs;

import java.io.Serializable;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Course;

public class CourseContainer implements Serializable, Removal<Course>{

	private TreeSet<Course> courses;
	
	public CourseContainer() {
		super();
		courses = new TreeSet<>();
	}
	
	public boolean addCourse(Course course) {
		if(courses.contains(course)) {
			return false;
		}
		courses.add(course);
		return true;
	}
	public ObservableList<Course> getObservableCourseContainer(){
		return FXCollections.observableArrayList(courses);
	}
	public Course getRandomCourse() {
		return courses.toArray(new Course[0])[(int)(Math.random()*courses.size())];
	}
	public boolean isEmpty() {
		return courses.isEmpty();
	}
	public boolean remove(Course obj) {
		obj.clearSections();
		return courses.remove(obj);
	}
	public Course[] toArray() {
		return courses.toArray(new Course[0]);
	}
}
