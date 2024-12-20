package model;

import java.io.Serializable;

import structs.Information;

public abstract class Person implements Serializable, Information{
	
	private Name name;
	
	public Person(Name name) {
		this.name = name;
	}
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		if(!(other instanceof Person)) {
			return false;
		}
		Person o = (Person)other;
		return name.equals(o.getName());
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String toString() {
		return name.toString();
	}
}
