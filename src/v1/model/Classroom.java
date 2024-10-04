package v1.model;

public class Classroom {
	
	private String roomID;
	private boolean hasProjector;
	
	public Classroom(String roomID, boolean hasProjector) {
		this.roomID = roomID;
		this.hasProjector = hasProjector;
	}
	public boolean hasProjector() {
		return hasProjector;
	}
	public void setHasProjector(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String toString() {
		return roomID + "\tProjector: " + hasProjector;
	}
}
