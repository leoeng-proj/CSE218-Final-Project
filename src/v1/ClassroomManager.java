package v1;


import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import v1.model.Classroom;
import v1.model.Section;
import v1.model.Student;

public class ClassroomManager {

	private static final Classroom[] rooms = {
		//Classroom(String roomID, boolean hasProjector, int maxCapacity) {
		new Classroom("CL01", 40),
		new Classroom("CL02", 25),
		new Classroom("CL03", 30),
		new Classroom("CL04", 30),
		new Classroom("CL05", 30),
		new Classroom("CL06", 30),
		new Classroom("CL07", 20),
	};
	@FXML
	private Button clOne;
	@FXML
	private Button clTwo;
	@FXML
	private Button clThree;
	@FXML
	private Button clFour;
	@FXML
	private Button clFive;
	@FXML
	private Button clSix;
	@FXML
	private Button clSeven;
	@FXML
	private ListView<Section> listOfSections;
	@FXML
	private ListView<Student> sectionPeople;
	@FXML
	private TextArea sectionInfo;
	
	public void showSections(ActionEvent e) {
		Button b = (Button)e.getSource();
		int idx = Integer.parseInt((String)b.getUserData());
		rooms[idx].getSections().addSection(Emitter.emitSection(rooms[0], null));
		listOfSections.setItems(FXCollections.observableArrayList(rooms[idx].getSections().toArray()));
	}
	public void sectionSelected(MouseEvent e) {
		ListView<Section> list = (ListView<Section>) e.getSource();
		Section section = list.getFocusModel().getFocusedItem();
		
		section.getStudents().addStudent(Emitter.emitStudent());
		sectionPeople.setItems(FXCollections.observableArrayList(section.getStudents().toArray()));
		
		sectionInfo.setText("Course: " + section.getCourse() +
				"\nInstructor: " + section.getInstructor() + 
				"\nDays Offered:\n" + Arrays.toString(section.getDaysOffered()) +
				"\nTime:\n" + section.getTime() + 
				"\nTextbooks:\n" + section.getTextbooks());
	}
}
