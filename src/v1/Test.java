package v1;

import v1.model.Course;
import v1.model.Major;
import v1.model.Name;
import v1.model.Student;
import v1.structs.AVLTree;

public class Test {

	public static void main(String[] args) {

		Student leo = new Student(new Name("Leo", "Eng"), Major.CSE, 4.0);
		System.out.println("hi");
		AVLTree<Student> students = new AVLTree<>();
		for(int i = 0; i < 10; i++) {
			students.add(Emitter.emitStudent());
		}
		Course dsa = new Course(3, "Data Structures and Algorithms", "", "CSE218", new Major[]{Major.CSE});
	}
}
