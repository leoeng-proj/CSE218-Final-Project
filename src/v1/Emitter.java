package v1;

import v1.model.Classroom;
import v1.model.Course;
import v1.model.Day;
import v1.model.Hours;
import v1.model.Major;
import v1.model.Name;
import v1.model.Professor;
import v1.model.Section;
import v1.model.Student;

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
	public static Student emitStudent() {
		return new Student(emitName(), 
				Major.values()[(int)(Math.random()*Major.values().length)],
				Math.random() * 4);
	}
	public static Professor emitProfessor() {
		return new Professor(emitName());
	}
	public static Section emitSection(Classroom room, Course course) {
//	Section(int sectionNum, boolean isOnline, Classroom room, Course course, ListBag<String> textbooks, Day[] daysOffered, Hours time) {
		return new Section((int)(Math.random() * 90000) + 10000, 
				(Math.random()*10 > 5), room, course,
				null, emitDays(), emitTime());
	}
	private static Name emitName() {
		return new Name(firstNameBank[(int)(Math.random()*firstNameBank.length)],
				lastNameBank[(int)(Math.random()*lastNameBank.length)]);
	}
	private static Hours emitTime() {
		return Hours.values()[(int)(Math.random()*Hours.values().length)];
	}
	private static Day[] emitDays() {
		Day[] mw = {Day.Mon, Day.Wed};
		Day[] tth = {Day.Tue, Day.Thu};
		Day[][] combinations = {mw, tth};
		return combinations[(int)(Math.random()*combinations.length)];
	}
}
