package structs;

import java.io.Serializable;

import model.Professor;
import model.Section;

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
	public ProfessorContainer getProfessorContainer() {
		return professors;
	}
	public SectionContainer getSectionContainer() {
		return sections;
	}
	public StudentContainer getStudentContainer() {
		return students;
	}
	public int autoAssign() {
		
		return 0;
	}
	public int assign(Professor prof, Section sec) {
		if(prof == null) {
			return 1;
		}
		prof.getSections().addSection(sec);
		if(sec.getInstructor() != null) {
			sec.getInstructor().removeSection(sec);
		}
		sec.setInstructor(prof);
		return 0;
	}
}
