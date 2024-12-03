package model;

import java.util.Arrays;

import structs.AVLTree;
import structs.SectionContainer;

public class Professor extends Person{

	private static final AVLTree<Integer> UUIDLIST = new AVLTree<>();
	private int id;
	private int seniority;
	private SectionContainer sections;
	private Hours prefTime;
	private Day[] prefDays;
	
	public Professor(Name name, Hours prefTime, Day[] prefDays) {
		super(name);
		id = generateUUID();
		this.prefTime = prefTime;
		this.prefDays = prefDays;
		sections = new SectionContainer();
	}
	public int getSeniority() {
		return seniority;
	}
	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}
	public void addSection(Section section) {
		sections.addSection(section);
	}
	public int getId() {
		return id;
	}
	public String getInfo() {
		return "Name: \t\t" + toString() + 
				"\nSeniority: " + seniority +
				"\nID: \t\t\t" + id +
				"\nPreferred Hours: " + prefTime +
				"\nPreferred Days: " + Arrays.toString(prefDays) +
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
	private static int generateUUID() {
		int id = 0;
		do{
			id = (int)(Math.random() * 10e8);
		}while(UUIDLIST.contains(id));
		UUIDLIST.add(id);
		return id;
	}
}
