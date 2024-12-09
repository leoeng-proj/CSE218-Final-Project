package structs;

import java.io.Serializable;
import java.util.HashSet;

import model.Classroom;

public class ClassroomContainer implements Serializable, Removal{

	private static final Classroom[] rooms = {
		//Classroom(String roomID, boolean hasProjector, int maxCapacity) {
		new Classroom("CL01", 40),
		new Classroom("CL02", 25),
		new Classroom("CL03", 30),
		new Classroom("CL04", 30),
		new Classroom("CL05", 30),
		new Classroom("CL06", 30),
		new Classroom("CL07", 5),
	};
	private HashSet<Classroom> classrooms;
	
	public ClassroomContainer() {
		super();
		classrooms = new HashSet<>();
		for(Classroom c : rooms) {
			classrooms.add(c);
		}
	}
	public int size() {
		return classrooms.size();
	}
	public boolean remove(Object obj) {	
		return classrooms.remove(obj);
	}
	public Classroom[] toArray() {
		return classrooms.toArray(new Classroom[0]);
	}
}
