package control;


import java.util.function.Predicate;

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
import structs.SectionContainer;

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
	private Section selectedSection;
	
	public void addSection() {
		Stage stage = new Stage();
		stage.setTitle("Select a Section");
		stage.setHeight(300);
		stage.setWidth(240);
		stage.setResizable(false);
		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		ListView<Section> sections = new ListView<>();
		sections.getStyleClass().add("listview-style");
		SectionContainer validSections = new SectionContainer(DataCenter.getInstance().getContainers().getSectionContainer());
		validSections.trim(new Predicate<>() {
			public boolean test(Section sec) {
				return sec.getRoom() != null;
			}
		});
		sections.setItems(FXCollections.observableArrayList(validSections.toArray()));
		sections.setOnMouseClicked(e -> {
			Section s = sections.getFocusModel().getFocusedItem();
			s.setRoom(selectedClassroom);
			selectedClassroom.getSections().addSection(s);
			listOfSections.setItems(FXCollections.observableArrayList(selectedClassroom.getSections().toArray()));
			if(sectionRemove.isDisabled()) {
				sectionRemove.setDisable(false);
			}
			stage.close();
		});
		root.add(sections, 0, 0);
	}
	public void removeSection() {
		Section s = listOfSections.getFocusModel().getFocusedItem();
		s.setRoom(null);
		selectedClassroom.getSections().remove(s);
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
		stage.setHeight(300);
		stage.setWidth(240);
		stage.setResizable(false);
		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		ListView<Student> students = new ListView<>();
		students.getStyleClass().add("listview-style");
		students.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getStudentContainer().toArray()));
		students.setOnMouseClicked(e -> {
			Student student = students.getFocusModel().getFocusedItem();
			selectedSection.getStudents().addStudent(student);
			updateSectionPeople();
			updateSectionInfo();
			if(studentRemove.isDisabled()) {
				studentRemove.setDisable(false);
			}
			stage.close();
		});
		root.add(students, 0, 0);
	}
	public void removeStudent(ActionEvent e) {
		selectedSection.getStudents().remove(sectionPeople.getFocusModel().getFocusedItem());
		updateSectionPeople();
		updateSectionInfo();
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
		selectedSection = listOfSections.getFocusModel().getFocusedItem();
		if(selectedSection == null) {
			return;
		}
		studentAdd.setDisable(false);
		updateSectionPeople();
		if(!sectionPeople.getItems().isEmpty()) {
			studentRemove.setDisable(false);
		}
		updateSectionInfo();
	}
	private void updateSectionInfo() {
		sectionInfo.setText(selectedSection.getInfo() +
				"\nCapacity:\t\t" + sectionPeople.getItems().size() + "/" + selectedClassroom.getMaxCapacity());
	}
	private void updateSectionPeople() {
		sectionPeople.setItems(FXCollections.observableArrayList(selectedSection.getStudents().toArray()));
	}
}
