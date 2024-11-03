package model;

import java.io.Serializable;

public class Name implements Serializable{
	
	private String firstName;
	private String lastName;
	
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int compareTo(Name other) {
		return this.lastName.compareTo(other.getLastName());
	}
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		if(!(other instanceof Name)) {
			return false;
		}
		Name o = (Name)other;
		return this.firstName.equals(o.getFirstName()) &&
				this.lastName.equals(o.getLastName());
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String toString() {
		return firstName + " " + lastName;
	}
}
