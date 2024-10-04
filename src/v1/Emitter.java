package v1;

import v1.model.Course;
import v1.model.Major;
import v1.model.Name;
import v1.model.Section;
import v1.model.Student;

public abstract class Emitter {
	
	public static Student emitStudent() {
		String[] firstNameBank = {"Alexander", "Benjamin", "Charlotte", "Daniel", "Evelyn", 
			    "Finn", "Grace", "Henry", "Isabella", "Jackson", 
			    "Katherine", "Liam", "Mason", "Nora", "Olivia", 
			    "Parker", "Quinn", "Riley", "Sophia", "Theodore", 
			    "Uma", "Violet", "William", "Xander", "Yara", 
			    "Zoe", "Aria", "Brayden", "Chloe", "Dylan"};
		String[] lastNameBank = {"Anderson", "Bennett", "Carter", "Davidson", "Evans", 
			    "Fletcher", "Gibson", "Harrison", "Irwin", "Johnson", 
			    "King", "Lewis", "Mitchell", "Nelson", "Owens", 
			    "Patterson", "Quinn", "Reed", "Stevens", "Taylor", 
			    "Underwood", "Vance", "Walker", "Xavier", "Young", 
			    "Zimmerman", "Coleman", "Daniels", "Ellis", "Foster"};
		return new Student(new Name(firstNameBank[(int)(Math.random()*firstNameBank.length)], lastNameBank[(int)(Math.random()*lastNameBank.length)]), 
				Major.values()[(int)(Math.random()*Major.values().length)],
				Math.random() * 4);
	}

//	public static Professor emitProfessor() {
//		
//	}
	
//	public static Section emitSection(Course course) {
//		return new Section();
//	}
}
