package v1;

import v1.model.Classroom;
import v1.model.Course;
import v1.model.Major;
import v1.model.Name;
import v1.model.Professor;
import v1.model.Section;
import v1.model.Student;
import v1.structs.AVLTree;

public class Test {

	public static void main(String[] args) {

		Student leo = new Student(new Name("Leo", "Eng"), Major.CSE, 4.0);
		AVLTree<Student> students = new AVLTree<>();
		for(int i = 0; i < 10; i++) {
			students.add(Emitter.emitStudent());
		}
		Course dsa = new Course(3, "Data Structures and Algorithms", "", "CSE218", new Major[]{Major.CSE});
		Classroom room = new Classroom("RI215", true);
		Section section = Emitter.emitSection(room, dsa);
		Professor prof = Emitter.emitProfessor();
		section.setInstructor(prof);
		System.out.println(section);
	}
}
