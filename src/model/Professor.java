package model;

import java.util.LinkedList;

import structs.AVLTree;
import structs.SectionContainer;

public class Professor extends Person{

	private int id;
	private int numSections;
	private SectionContainer sections;
	
	private static final AVLTree<Integer> UUIDLIST = new AVLTree<>();
	
	public Professor(Name name) {
		super(name);
		id = generateUUID();
		numSections = 0;
		sections = new SectionContainer();
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
	public SectionContainer getSections() {
		return sections;
	}
	public void addSection(Section section) {
		sections.addSection(section);
	}
	public void removeSection(Section section) {
		sections.remove(section);
		numSections--;
	}
	public String getInfo() {
		return "Name: \t\t" + toString() + 
				"\nID: \t\t\t" + id +
				"\nClasses:\n" + sections.toString();
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
