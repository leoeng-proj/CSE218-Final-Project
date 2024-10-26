package structs;

import java.io.Serializable;
import java.util.LinkedList;

import model.Course;

public class CourseContainer implements Serializable, Removal{

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
	public Course[] toArray() {
		return courses.toArray(new Course[0]);
	}
	public boolean remove(Object obj) {
		return courses.remove(obj);
	}
	public Course getRandomCourse() {
		return courses.get((int)(Math.random()*courses.size()));
	}
	public boolean isEmpty() {
		return courses.isEmpty();
	}
}
