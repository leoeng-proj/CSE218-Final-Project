package control;

import java.util.ArrayList;
import java.util.Arrays;
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
			"118", "148", "218", "222", "248"
	};
	public static Course emitCourse() {
//		Course(double credits, String name, String description, String courseNum, Major reqMajors) {
		Major mjr = emitMajor();
		double[] creditValues = {1.0, 2.0, 3.0, 4.0, 5.0};
		return new Course(creditValues[(int)(Math.random()*creditValues.length)], "Name", "", mjr +courseNumBank[(int)(Math.random()*courseNumBank.length)], mjr);
	}
	public static Professor emitProfessor() {
		return new Professor(emitName(), emitHours(), emitDays(), emitMajor(), emitDate());
	}
	public static Section emitSection(Classroom room, Course course) {
//	Section(int sectionNum, boolean isOnline, Classroom room, Course course, ListBag<String> textbooks, Day[] daysOffered, Hours time) {
		Section s = new Section((int)(Math.random() * 90000) + 10000, 
				(Math.random()*10 > 5), room, course,
				emitDays(), emitTime());
		course.addSection(s);
		return s;
	}
	public static Student emitStudent() {
		return new Student(emitName(), 
				emitMajor(),
				Math.random() * 4);
	}
	private static TimeRange emitTime() {
		int hr = (int)(Math.random()*12+8);
		int[] mins = {0, 15, 30, 45};
		int min = mins[(int)(Math.random()*mins.length)];
		return new TimeRange(hr, min, hr+1, min+14);
	}
	private static ArrayList<Day> emitDays() {
//		ArrayList<Day> mw = new ArrayList<>(Arrays.asList(Day.Mon, Day.Wed));
//        ArrayList<Day> tth = new ArrayList<>(Arrays.asList(Day.Tue, Day.Thu));
//        ArrayList<Day>[] combinations = new ArrayList[]{mw, tth};
//	    return combinations[(int) (Math.random() * combinations.length)];
		return new ArrayList<>(Arrays.asList(Day.Mon, Day.Tue, Day.Wed, Day.Thu));
	}
	private static Name emitName() {
		return new Name(firstNameBank[(int)(Math.random()*firstNameBank.length)],
				lastNameBank[(int)(Math.random()*lastNameBank.length)]);
	}
	private static Major emitMajor() {
		return Major.values()[(int)(Math.random()*Major.values().length)];
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
