package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.PriorityQueue;

import structs.SectionContainer;

public class Professor extends Person implements Comparable<Professor>{

	private static final HashSet<Integer> UUIDLIST = new HashSet<>();
	public static final double MIN_CREDITS = 15.0;
	public static final double MAX_CREDITS = 19.0;
	private int id;
	private double credits;
	private GregorianCalendar dateOfHire;
	private SectionContainer sections;
	private Major major;
	private Hours prefTime;
	private ArrayList<Day> prefDays;
	
	public Professor(Name name, Hours prefTime, ArrayList<Day> prefDays, Major major, GregorianCalendar dateOfHire) {
		super(name);
		id = generateUUID();
		this.prefTime = prefTime;
		this.prefDays = prefDays;
		this.major = major;
		this.dateOfHire = dateOfHire;
		sections = new SectionContainer();
	}
	public void addSection(Section section) {
		credits += section.getCourse().getCredits();
		sections.addSection(section);
	}
	public int getId() {
		return id;
	}
	public double getCredits() {
		return credits;
	}
	public String getInfo() {
		return "Name: \t\t" + toString() + 
				"\nDate of Hire:\t" + new SimpleDateFormat("MM-dd-yyyy").format(dateOfHire.getTime()) +
				"\nID: \t\t\t" + id +
				"\nPreferred Hours: " + prefTime +
				"\nPreferred Days: " + prefDays +
				"\nMajor:\t\t" + major +
				"\nCredits\t\t" + credits +
				"\nClasses:\n" + sections.toString();
	}
	public Hours getPrefTime() {
		return prefTime;
	}
	public Major getMajor() {
		return major;
	}
	public ArrayList<Day> getPrefDays() {
		return prefDays;
	}
	public GregorianCalendar getDateOfHire() {
		return dateOfHire;
	}
	public SectionContainer getSections() {
		return sections;
	}
	public void removeSection(Section section) {
		credits -= section.getCourse().getCredits();
		sections.delete(section);
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
