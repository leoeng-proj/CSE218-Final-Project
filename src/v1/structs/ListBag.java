package v1.structs;

import java.util.LinkedList;

public class ListBag<T> implements Comparable<T> {

	private LinkedList<T> list;
	
	public ListBag() {
		list = null;
	}
	public ListBag(T item) {
		list = new LinkedList<T>();
		list.add(item);
	}
	public ListBag(LinkedList<T> list) {
		this.list = list;
	}
	public void add(T item) {
		list.add(item);
	}
	public boolean contains(T item) {
		return list.contains(item);
	}
	public int compareTo(T o) {
		return 0;
	}
}
