package v1.structs;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import v1.DataCenter;
import v1.model.Course;
import v1.model.Section;
import v1.model.Student;

public class MasterContainer implements Serializable{
	private CourseContainer courses;
	private StudentContainer students;
	private SectionContainer sections;
	private ClassroomContainer classrooms;
	
	public MasterContainer() {
		courses = new CourseContainer();
		students = new StudentContainer();
		sections = new SectionContainer();
		classrooms = new ClassroomContainer();
	}
	public ClassroomContainer getClassroomContainer() {
		return classrooms;
	}
	public CourseContainer getCourseContainer() {
		return courses;
	}
	public StudentContainer getStudentContainer() {
		return students;
	}
	public SectionContainer getSectionContainer() {
		return sections;
	}
}
