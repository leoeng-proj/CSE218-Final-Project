package structs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Professor;
import model.Section;

public class ProfessorContainer implements Serializable, Removal<Professor>{
	private LinkedList<Professor> professors;
//	private ArrayList<LinkedList<Professor>> professors;
	public ProfessorContainer() {
		super();
		professors = new LinkedList<Professor>();
	}
	public ProfessorContainer(ProfessorContainer other) {
		super();
		professors = (LinkedList<Professor>)other.professors.clone();
	}
	public void addProfessor(Professor professor) {
		professors.add(professor);
	}
	public ObservableList<Professor> getObservableProfessorContainer(){
		return FXCollections.observableArrayList(toArray());
	}
	public boolean isEmpty() {
		return professors.isEmpty();
	}
	public boolean remove(Professor prof) {
		prof.getSections().unassignProfessor(prof);
		return professors.remove(prof);
	}
	public Professor[] toArray() {
		return professors.toArray(new Professor[0]);
	}
	public int size() {
		return professors.size();
	}
	public String toString() {
		return professors.toString();
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
	protected LinkedList<Professor> getProfessors(){
		return professors;
	}
}
