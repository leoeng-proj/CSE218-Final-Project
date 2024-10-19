package v1.structs;

import java.io.Serializable;
import java.util.LinkedList;

import v1.model.Course;
import v1.model.Section;

public class SectionContainer implements Serializable, Removal{
	
	private LinkedList<Section> sections;
	
	public SectionContainer() {
		super();
		sections = new LinkedList<>();
	}
	public void addSection(Section section) {
		sections.add(section);
	}
	public Section[] toArray() {
		return sections.toArray(new Section[0]);
	}
	public boolean remove(Object obj) {
		return sections.remove(obj);
	}
}
