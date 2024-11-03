package structs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Section;

public class SectionContainer implements Serializable, Removal{
	private LinkedList<Section> sections;
	
	public SectionContainer() {
		super();
		sections = new LinkedList<>();
	}
	public SectionContainer(SectionContainer other) {
		super();
		sections = (LinkedList<Section>)other.sections.clone();
	}
	public void addSection(Section section) {
		sections.add(section);
	}
	public boolean checkTimeConflicts(Section sec) {
		Iterator<Section> iter = sections.iterator();
		while(iter.hasNext()) {
			Section s = iter.next();
			if(Arrays.equals(sec.getDaysOffered(), s.getDaysOffered()) 
					&& s.getTime().equals(sec.getTime())){
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
	public boolean remove(Object obj) {
		return sections.remove(obj);
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
}
