package control;


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
import model.Classroom;
import model.Section;
import model.Student;

public class ClassroomManager {

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

	private Classroom selectedClassroom;
	
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
			selectedClassroom.getSections().addSection(sections.getFocusModel().getFocusedItem());
			listOfSections.setItems(FXCollections.observableArrayList(selectedClassroom.getSections().toArray()));
			if(sectionRemove.isDisabled()) {
				sectionRemove.setDisable(false);
			}
			stage.close();
		});
		root.add(sections, 0, 0);
	}
	public void removeSection() {
		selectedClassroom.getSections().remove(listOfSections.getFocusModel().getFocusedItem());
		listOfSections.setItems(FXCollections.observableArrayList(selectedClassroom.getSections().toArray()));
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
			updateSectionInfo(s);
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
		updateSectionInfo(section);
		if(sectionPeople.getItems().isEmpty()) {
			studentRemove.setDisable(true);
		}
	}
	public void showSections(ActionEvent e) {
		Button b = (Button)e.getSource();
		int idx = Integer.parseInt((String)b.getUserData());
		selectedClassroom = DataCenter.getInstance().getContainers().getClassroomContainer().toArray()[idx];
		sectionAdd.setDisable(false);
		if(!selectedClassroom.getSections().isEmpty()) {
			sectionRemove.setDisable(false);
		}
		listOfSections.setItems(FXCollections.observableArrayList(selectedClassroom.getSections().toArray()));
		sectionPeople.getItems().clear();
		studentAdd.setDisable(true);
		studentRemove.setDisable(true);
		sectionInfo.clear();
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
		updateSectionInfo(section);
	}
	private void updateSectionInfo(Section section) {
		sectionInfo.setText("Course: " + section.getCourse() +
				"\nInstructor: " + section.getInstructor() + 
				"\nDays Offered:\n" + Arrays.toString(section.getDaysOffered()) +
				"\nCapacity: " + sectionPeople.getItems().size() + "/" + selectedClassroom.getMaxCapacity() +
				"\nTime:\n" + section.getTime() + 
				"\nTextbooks:\n" + section.getTextbooks());
	
	}
}
