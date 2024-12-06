package model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

import structs.SectionContainer;

public class Professor extends Person implements Comparable<Professor>{

	private static final HashSet<Integer> UUIDLIST = new HashSet<>();
	private int id;
	private GregorianCalendar dateOfHire;
	private SectionContainer sections;
	private Hours prefTime;
	private Day[] prefDays;
	
	public Professor(Name name, Hours prefTime, Day[] prefDays, GregorianCalendar dateOfHire) {
		super(name);
		id = generateUUID();
		this.prefTime = prefTime;
		this.prefDays = prefDays;
		this.dateOfHire = dateOfHire;
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
				"\nDate of Hire: " + dateOfHire.toString() +
				"\nID: \t\t\t" + id +
				"\nPreferred Hours: " + prefTime +
				"\nPreferred Days: " + Arrays.toString(prefDays) +
				"\nClasses:\n" + sections.toString();
	}
	public Hours getPrefTime() {
		return prefTime;
	}
	public GregorianCalendar getDateOfHire() {
		return dateOfHire;
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
	public int compareTo(Professor p) {
		return dateOfHire.compareTo(p.getDateOfHire());
	}
}
