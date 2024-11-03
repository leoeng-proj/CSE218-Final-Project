package model;

import structs.AVLTree;
import structs.SectionContainer;

public class Professor extends Person{

	private static final AVLTree<Integer> UUIDLIST = new AVLTree<>();
	private static int generateUUID() {
		int id = 0;
		do{
			id = (int)(Math.random() * 10e8);
		}while(UUIDLIST.contains(id));
		UUIDLIST.add(id);
		return id;
	}
	private int id;
	
	private SectionContainer sections;
	
	private Hours prefTime;
	public Professor(Name name, Hours prefTime) {
		super(name);
		id = generateUUID();
		this.prefTime = prefTime;
		sections = new SectionContainer();
	}
	public void addSection(Section section) {
		sections.addSection(section);
	}
	public int getId() {
		return id;
	}
	public String getInfo() {
		return "Name: \t\t" + toString() + 
				"\nID: \t\t\t" + id +
				"\nPreferred Hours: " + prefTime +
				"\nClasses:\n" + sections.toString();
	}
	public Hours getPrefTime() {
		return prefTime;
	}
	public SectionContainer getSections() {
		return sections;
	}
	public void removeSection(Section section) {
		sections.remove(section);
	}
	public void setPrefTime(Hours prefTime) {
		this.prefTime = prefTime;
	}
}
