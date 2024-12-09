package structs;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Predicate;

import control.DataCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Professor;
import model.Section;

public class SectionContainer implements Serializable, Removal<Section>{
	private HashSet<Section> sections;
	
	public SectionContainer() {
		this(0);
	}
	public SectionContainer(int size) {
		super();
		sections = new HashSet<>();
	}
	public SectionContainer(SectionContainer other) {
		super();
		sections = (HashSet<Section>)other.sections.clone();
	}
	public void addSection(Section section) {
		sections.add(section);
	}
	public boolean checkTimeConflicts(Section sec) {
		Iterator<Section> iter = sections.iterator();
		while(iter.hasNext()) {
			Section s = iter.next();
			if(sec.getDaysOffered().equals(s.getDaysOffered()) 
					&& s.getTime().contains(sec.getTime())){
				return true;
			}
		}
		return false;
	}
	public ObservableList<Section> getObservableSectionContainer(){
		return FXCollections.observableArrayList(toArray());
	}
	public boolean isEmpty() {
		return sections.isEmpty();
	}
	public int size() {
		return sections.size();
	}
	public boolean remove(Section sec) {
		sec.clear();
		return sections.remove(sec);
	}
	public boolean delete(Section sec) {
		return sections.remove(sec);		
	}
	public void clear() {
		DataCenter.getInstance().getContainers().getSectionContainer().sections.removeAll(sections); //Set A - Set B
		sections.clear();
	}
	public void unassignProfessor(Professor prof) {
		for(Section s : sections) {
			s.setInstructor(null);
		}
	}
	public Section[] toArray() {
		return sections.toArray(new Section[0]);
	}
	public String toString() {
		return sections.toString();
	}
	public void trim(Predicate<Section> pred) {
		Iterator<Section> iter = sections.iterator();
		while(iter.hasNext()) {
			Section s = iter.next();
			if(pred.test(s)) {
				iter.remove();
			}
		}
	}
	public Section getBestSection(Professor p) {
		Section best = null;
		for(Section s : sections) {
			if(p.getPrefTime().contains(s.getTime()) && s.getDaysOffered().equals(p.getPrefDays())) {
				best = s;
				break;
			}
			else if(p.getPrefDays().containsAll(s.getDaysOffered())) {
				int prefStartHr = p.getPrefTime().getTimeRange().getStartTime().getHour();
				int startHr = s.getTime().getStartTime().getHour();
				if(Math.abs(prefStartHr - startHr) == 3) {
					best = s;
					break;
				}
			}
		}
		return best;
	}
	public boolean allHaveProfessors() {
		for(Section s : sections) {
			if(s.getInstructor() == null) {
				return false;
			}
		}
		return true;
	}
}