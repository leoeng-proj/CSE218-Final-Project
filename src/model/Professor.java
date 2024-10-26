package model;

import java.util.LinkedList;

import structs.AVLTree;

public class Professor extends Person{

	private int id;
	private int numSections;
	private LinkedList<Section> sections;
	
	private static final AVLTree<Integer> UUIDLIST = new AVLTree<>();
	
	public Professor(Name name) {
		super(name);
		id = generateUUID();
		numSections = 0;
		sections = new LinkedList<>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumSections() {
		return numSections;
	}
	public void setNumSections(int numSections) {
		this.numSections = numSections;
	}
	public LinkedList<Section> getSections() {
		return sections;
	}
	public void addSection(Section section) {
		if(sections.contains(section)) {
			return;
		}
		sections.add(section);
		section.setInstructor(this);
		numSections++;
	}
	public void removeSection(Section section) {
		sections.remove(section);
		numSections--;
	}
	private static int generateUUID() {
		int id = 0;
		do{
			id = (int)(Math.random() * 10e8);
		}while(UUIDLIST.contains(id));
		UUIDLIST.add(id);
		return id;
	}
}
