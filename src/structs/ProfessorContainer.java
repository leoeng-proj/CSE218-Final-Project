package structs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Professor;

public class ProfessorContainer implements Serializable, Removal{
//	private LinkedList<Professor> professors;
	private ArrayList<LinkedList<Professor>> professors;
	public ProfessorContainer() {
		super();
		professors = new ArrayList<LinkedList<Professor>>();
	}
	public ProfessorContainer(ProfessorContainer other) {
		super();
		professors = (ArrayList<LinkedList<Professor>>)other.professors.clone();
	}
	public void addProfessor(Professor professor) {
		int idx = professor.getSeniority();
		if(idx == professors.size()) {
			professors.add(new LinkedList<Professor>());
		}
		professors.get(idx).add(professor);
//		professors.add(professor);
	}
	public int getAvailableSeniority() {
		int seniority = 0;
		for(LinkedList<Professor> q : professors) {
			if(q.size() <= 5) {
				break; 
			}
			seniority++;
		}
		return seniority;
	}
	public ObservableList<Professor> getObservableProfessorContainer(){
		return FXCollections.observableArrayList(toArray());
	}
	public boolean isEmpty() {
		return professors.isEmpty();
	}
	public boolean remove(Object obj) {
		return professors.remove(obj);
	}
	public Professor[] toArray() {
		if(professors.size() == 0) {
			return new Professor[0];
		}
		int size = (professors.size() - 2)*5 + professors.get(professors.size()-1).size();
		Professor[] arr = new Professor[size];
		int i = 0;
		for(LinkedList<Professor> list : professors) {
			for(Professor prof : list) {
				arr[i++] = prof;
			}
		}
		return arr;
	}
	public String toString() {
		return professors.toString();
	}
//	public void trim(Predicate<Professor> pred) {
//		Iterator<Professor> iter = professors.iterator();
//		while(iter.hasNext()) {
//			Professor s = iter.next();
//			if(pred.test(s)) {
//				iter.remove();
//			}
//		}
//	}
}
