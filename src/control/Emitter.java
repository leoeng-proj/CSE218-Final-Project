package control;

import java.util.GregorianCalendar;

import model.Classroom;
import model.Course;
import model.Day;
import model.Hours;
import model.Major;
import model.Name;
import model.Professor;
import model.Section;
import model.Student;
import model.TimeRange;

public abstract class Emitter {
	private static String[] firstNameBank = {"Alexander", "Benjamin", "Charlotte", "Daniel", "Evelyn", 
		    "Finn", "Grace", "Henry", "Isabella", "Jackson", 
		    "Katherine", "Liam", "Mason", "Nora", "Olivia", 
		    "Parker", "Quinn", "Riley", "Sophia", "Theodore", 
		    "Uma", "Violet", "William", "Xander", "Yara", 
		    "Zoe", "Aria", "Brayden", "Chloe", "Dylan"};
	private static String[] lastNameBank = {"Anderson", "Bennett", "Carter", "Davidson", "Evans", 
		    "Fletcher", "Gibson", "Harrison", "Irwin", "Johnson", 
		    "King", "Lewis", "Mitchell", "Nelson", "Owens", 
		    "Patterson", "Quinn", "Reed", "Stevens", "Taylor", 
		    "Underwood", "Vance", "Walker", "Xavier", "Young", 
		    "Zimmerman", "Coleman", "Daniels", "Ellis", "Foster"};
	private static String[] courseNumBank = {
			"cse118", "cse148", "cse218", "cse222", "cse248", "mat141", "mat142"
	};
	public static Course emitCourse() {
//		Course(double credits, String name, String description, String courseNum, Major reqMajors) {
		return new Course(Math.random()*5, "Name", "", courseNumBank[(int)(Math.random()*courseNumBank.length)],
				Major.values()[(int)(Math.random()*Major.values().length)]);
	}
	public static Professor emitProfessor() {
		return new Professor(emitName(), emitHours(), emitDays(), emitDate());
	}
	public static Section emitSection(Classroom room, Course course) {
//	Section(int sectionNum, boolean isOnline, Classroom room, Course course, ListBag<String> textbooks, Day[] daysOffered, Hours time) {
		return new Section((int)(Math.random() * 90000) + 10000, 
				(Math.random()*10 > 5), room, course,
				null, emitDays(), emitTime());
	}
	public static Student emitStudent() {
		return new Student(emitName(), 
				Major.values()[(int)(Math.random()*Major.values().length)],
				Math.random() * 4);
	}
	private static TimeRange emitTime() {
		int hr = (int)(Math.random()*15+7);
		int min = (int)(Math.random()*45);
		return new TimeRange(hr, min, hr+1, min+15);
	}
	private static Day[] emitDays() {
		Day[] mw = {Day.Mon, Day.Wed};
		Day[] tth = {Day.Tue, Day.Thu};
		Day[][] combinations = {mw, tth};
		return combinations[(int)(Math.random()*combinations.length)];
	}
	private static Name emitName() {
		return new Name(firstNameBank[(int)(Math.random()*firstNameBank.length)],
				lastNameBank[(int)(Math.random()*lastNameBank.length)]);
	}
	private static Hours emitHours() {
		return Hours.values()[(int)(Math.random()*Hours.values().length)];
	}
	private static GregorianCalendar emitDate() {
		return new GregorianCalendar((int)(Math.random()*24)+2000,
				(int)(Math.random()*13),
				(int)(Math.random()*30));
	}
}
