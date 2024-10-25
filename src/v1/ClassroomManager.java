package v1;


import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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
	private Button sectionAdd;
	@FXML
	private Button sectionRemove;
	@FXML
	private Button studentAdd;
	@FXML
	private Button studentRemove;
	@FXML
	private ListView<Section> listOfSections;
	@FXML
	private ListView<Student> sectionPeople;
	@FXML
	private TextArea sectionInfo;
	private int idx = 0;
	
	public void addSection() {
		Stage stage = new Stage();
		stage.setTitle("Select a Section");
		stage.setHeight(400);
		stage.setWidth(300);
		stage.setResizable(false);
		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		ListView<Section> sections = new ListView<>();
		sections.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getSectionContainer().toArray()));
		sections.setOnMouseClicked(e -> {
			rooms[idx].getSections().addSection(sections.getFocusModel().getFocusedItem());
			listOfSections.setItems(FXCollections.observableArrayList(rooms[idx].getSections().toArray()));
			if(sectionRemove.isDisabled()) {
				sectionRemove.setDisable(false);
			}
			stage.close();
		});
		root.add(sections, 0, 0);
	}
	public void removeSection() {
		rooms[idx].getSections().remove(listOfSections.getFocusModel().getFocusedItem());
		listOfSections.setItems(FXCollections.observableArrayList(rooms[idx].getSections().toArray()));
		sectionPeople.getItems().clear();
		sectionInfo.clear();
		studentAdd.setDisable(true);
		studentRemove.setDisable(true);
		if(listOfSections.getItems().isEmpty()) {
			sectionRemove.setDisable(true);
		}
	}
	public void addStudent() {
		Stage stage = new Stage();
		stage.setTitle("Select a Student");
		stage.setHeight(400);
		stage.setWidth(300);
		stage.setResizable(false);
		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		ListView<Student> students = new ListView<>();
		students.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getStudentContainer().toArray()));
		students.setOnMouseClicked(e -> {
			Student student = students.getFocusModel().getFocusedItem();
			Section s = listOfSections.getFocusModel().getFocusedItem();
			s.getStudents().addStudent(student);
			sectionPeople.setItems(FXCollections.observableArrayList(s.getStudents().toArray()));
			if(studentRemove.isDisabled()) {
				studentRemove.setDisable(false);
			}
			stage.close();
		});
		root.add(students, 0, 0);
	}
	public void removeStudent(ActionEvent e) {
		Section section = listOfSections.getFocusModel().getFocusedItem();
		section.getStudents().remove(sectionPeople.getFocusModel().getFocusedItem());
		sectionPeople.setItems(FXCollections.observableArrayList(section.getStudents().toArray()));
		if(sectionPeople.getItems().isEmpty()) {
			studentRemove.setDisable(true);
		}
	}
	public void showSections(ActionEvent e) {
		Button b = (Button)e.getSource();
		idx = Integer.parseInt((String)b.getUserData());
		sectionAdd.setDisable(false);
		listOfSections.setItems(FXCollections.observableArrayList(rooms[idx].getSections().toArray()));
	}
	public void sectionSelected(MouseEvent e) {
		Section section = listOfSections.getFocusModel().getFocusedItem();
		if(section == null) {
			return;
		}
		studentAdd.setDisable(false);
		sectionPeople.setItems(FXCollections.observableArrayList(section.getStudents().toArray()));
		if(!sectionPeople.getItems().isEmpty()) {
			studentRemove.setDisable(false);
		}
		sectionInfo.setText("Course: " + section.getCourse() +
				"\nInstructor: " + section.getInstructor() + 
				"\nDays Offered:\n" + Arrays.toString(section.getDaysOffered()) +
				"\nTime:\n" + section.getTime() + 
				"\nTextbooks:\n" + section.getTextbooks());
	}
}
