package control;

import model.Classroom;
import model.Course;
import model.Major;
import model.Name;
import model.Professor;
import model.Section;
import model.Student;
import structs.AVLTree;
import structs.StudentContainer;

public class Test {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		for(int i = 0; i < 10; i++) {
			tree.add(i);
		}
		System.out.println(tree);
	}
}
