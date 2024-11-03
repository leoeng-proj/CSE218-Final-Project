package control;

import structs.AVLTree;

public class Test {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		for(int i = 0; i < 10; i++) {
			tree.add(i);
		}
		System.out.println(tree);
	}
}
