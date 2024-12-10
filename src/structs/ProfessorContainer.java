package structs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Professor;
import model.Student;

public class ProfessorContainer implements Serializable, Removal<Professor>{
	
	private TreeMap<Integer, Professor> professors;
	public ProfessorContainer() {
		super();
		professors = new TreeMap<Integer, Professor>();
	}
	public ProfessorContainer(ProfessorContainer other) {
		super();
		professors = (TreeMap<Integer, Professor>)other.professors.clone();
	}
	public void addProfessor(Professor professor) {
		professors.put(professor.getId(), professor);
	}
	public ObservableList<Professor> getObservableProfessorContainer(){
		Collection<Professor> col = professors.values();
		return FXCollections.observableArrayList(col.toArray(new Professor[0]));
	}
	public boolean isEmpty() {
		return professors.isEmpty();
	}
	public boolean remove(Professor prof) {
		prof.getSections().unassignProfessor(prof);
		return professors.remove(prof.getId()) != null;
	}
	public Professor[] toArray() {
		return professors.values().toArray(new Professor[0]);
	}
	public int size() {
		return professors.size();
	}
	public String toString() {
		return professors.toString();
	}
	public ProfessorContainer trim(Predicate<Professor> pred) {
		ProfessorContainer trimmed = new ProfessorContainer();
		professors.forEach((id, prof) -> {
			if(!pred.test(prof)) {
				trimmed.addProfessor(prof);
			}
		});
		return trimmed;
	}
	protected TreeMap<Integer, Professor> getProfessors(){
		return professors;
	}
}
