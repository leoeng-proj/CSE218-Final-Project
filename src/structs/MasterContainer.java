package structs;

import java.io.Serializable;

import control.DataCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Course;
import model.Section;
import model.Student;

public class MasterContainer implements Serializable{
	private CourseContainer courses;
	private StudentContainer students;
	private SectionContainer sections;
	private ClassroomContainer classrooms;
	private ProfessorContainer professors;
	public MasterContainer() {
		courses = new CourseContainer();
		students = new StudentContainer();
		sections = new SectionContainer();
		classrooms = new ClassroomContainer();
		professors = new ProfessorContainer();
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
	public ProfessorContainer getProfessorContainer() {
		return professors;
	}
}
