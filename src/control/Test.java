package control;

import model.Classroom;
import model.Course;
import model.Major;
import model.Name;
import model.Professor;
import model.Section;
import model.Student;
import structs.StudentContainer;

public class Test {

	public static void main(String[] args) {

		Student leo = new Student(new Name("Leo", "Eng"), Major.CSE, 4.0);
		StudentContainer students = new StudentContainer();
		for(int i = 0; i < 10; i++) {
			students.addStudent(Emitter.emitStudent());
		}
		Course dsa = new Course(3, "Data Structures and Algorithms", "", "CSE218", Major.CSE);
		Professor prof = Emitter.emitProfessor();
	}
}
