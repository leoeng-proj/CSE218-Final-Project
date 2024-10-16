package v1.structs;

import java.io.Serializable;
import java.util.LinkedList;

import v1.model.Course;

public class CourseContainer implements Serializable{

	private LinkedList<Course> courses;
	
	public CourseContainer() {
		super();
		courses = new LinkedList<>();
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	public Course[] toArray() {
		return courses.toArray(new Course[0]);
	}
}
