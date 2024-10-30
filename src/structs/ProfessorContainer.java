package structs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import model.Professor;
import model.Section;

public class ProfessorContainer implements Serializable, Removal{
	private LinkedList<Professor> professors;
	
	public ProfessorContainer() {
		super();
		professors = new LinkedList<>();
	}
	public ProfessorContainer(ProfessorContainer other) {
		super();
		professors = (LinkedList<Professor>)other.professors.clone();
	}
	public void addProfessor(Professor professor) {
		professors.add(professor);
	}
	public Professor[] toArray() {
		return professors.toArray(new Professor[0]);
	}
	public boolean remove(Object obj) {
		return professors.remove(obj);
	}
	public boolean isEmpty() {
		return professors.isEmpty();
	}
	public void trim(Predicate<Professor> pred) {
		Iterator<Professor> iter = professors.iterator();
		while(iter.hasNext()) {
			Professor s = iter.next();
			if(pred.test(s)) {
				iter.remove();
			}
		}
	}
	public String toString() {
		return professors.toString();
	}
}
