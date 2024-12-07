package structs;

import java.io.Serializable;
import java.util.PriorityQueue;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		PriorityQueue<Professor> q = new PriorityQueue<Professor>(professors.size(), ((Professor p1, Professor p2) -> {
			return p1.compareTo(p2);
		}));
		q.addAll(professors.getProfessors());
		while(!q.isEmpty()) {
			Professor p = q.poll();
			while(p.getCredits() < Professor.MIN_CREDITS) {
				SectionContainer potentialSections = getPotentialSections(p);
				Section best = potentialSections.getBestSection(p);
				if(best == null) {
					Alert noCourses = new Alert(AlertType.WARNING);
					noCourses.setHeaderText("Not Enough " + p.getMajor()  + " " + p.getPrefTime() + " Sections");
					noCourses.show();
					break;
				}
				assign(p, best);
			}
		}
		if(!sections.allHaveProfessors()) {
			Alert noCourses = new Alert(AlertType.WARNING);
			noCourses.setHeaderText("Not Enough Professors");
			noCourses.show();
		}
		return 0;
	}
	private SectionContainer getPotentialSections(Professor p) {
		SectionContainer sects = new SectionContainer(sections);
		sects.trim(s -> {
			return !s.getCourse().getMajor().equals(p.getMajor())  ||
					s.getInstructor() != null;
		});
		return sects;
	}
	public int assign(Professor prof, Section sec) {
		if(prof == null) {
			return 1;
		}
		prof.addSection(sec);
		if(sec.getInstructor() != null) {
			sec.getInstructor().removeSection(sec);
		}
		sec.setInstructor(prof);
		return 0;
	}
}
