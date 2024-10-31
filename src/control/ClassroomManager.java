package control;


import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Classroom;
import model.Professor;
import model.Section;
import model.Student;
import structs.ProfessorContainer;
import structs.SectionContainer;
import structs.StudentContainer;

public class ClassroomManager {

	@FXML
	private Button assignProf;
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
	
	public void assignProfessor() {
		Stage stage = new Stage();
		stage.setTitle("Select a Professor");
		stage.setHeight(300);
		stage.setWidth(240);
		stage.setResizable(false);
		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		ListView<Professor> profs = new ListView<>();
		profs.getStyleClass().add("listview-style");
		ProfessorContainer validProfessors = new ProfessorContainer(DataCenter.getInstance().getContainers().getProfessorContainer());
		validProfessors.trim(new Predicate<Professor>() {
			public boolean test(Professor prof) {
				return prof.getSections().checkTimeConflicts(selectedSection) ||
						!prof.getPrefTime().equals(selectedSection.getTime());
			}
		});
		profs.setItems(FXCollections.observableArrayList(validProfessors.toArray()));
		profs.setOnMouseClicked(e -> {
			Professor p = profs.getFocusModel().getFocusedItem();
			if(p == null) {
				return;
			}
			p.getSections().addSection(selectedSection);
			if(selectedSection.getInstructor() != null) {
				selectedSection.getInstructor().removeSection(selectedSection);
			}
			selectedSection.setInstructor(p);
			updateSectionInfo();
			stage.close();
		});
		root.add(profs, 0, 0);
	}
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
		validSections.trim(new Predicate<Section>() {
			public boolean test(Section sec) {
				return sec.getRoom() != null;
			}
		});
		sections.setItems(FXCollections.observableArrayList(validSections.toArray()));
		sections.setOnMouseClicked(e -> {
			Section s = sections.getFocusModel().getFocusedItem();
			if(s == null) {
				return;
			}
			if(selectedClassroom.getSections().checkTimeConflicts(s)) {
				Alert timeConfliction = new Alert(AlertType.WARNING);
				timeConfliction.setHeaderText("Time Confliction");
				timeConfliction.show();
				return;
			}
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
		if(selectedSection == null) {
			Alert noSections = new Alert(AlertType.WARNING);
			noSections.setHeaderText("Select a Section");
			noSections.show();
			return;
		}
		selectedSection.setRoom(null);
		selectedClassroom.getSections().remove(selectedSection);
		listOfSections.setItems(FXCollections.observableArrayList(selectedClassroom.getSections().toArray()));
		sectionPeople.getItems().clear();
		sectionInfo.clear();
		selectedSection.clear();
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
		StudentContainer validStudents = new StudentContainer(DataCenter.getInstance().getContainers().getStudentContainer());
		validStudents.trim(new Predicate<Student>() {
			public boolean test(Student student) {
				return student.getSections().checkTimeConflicts(selectedSection) ||
						!student.getMajor().equals(selectedSection.getCourse().getReqMajors());
			}
		});
		students.setItems(FXCollections.observableArrayList(validStudents.toArray()));
		students.setOnMouseClicked(e -> {
			Student student = students.getFocusModel().getFocusedItem();
			if(student == null) {
				return;
			}
			student.addSection(selectedSection);
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
		Student stu = sectionPeople.getFocusModel().getFocusedItem();
		selectedSection.getStudents().remove(stu);
		stu.unenroll(selectedSection);
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
		assignProf.setDisable(true);
		studentAdd.setDisable(true);
		studentRemove.setDisable(true);
		sectionInfo.clear();
	}
	public void sectionSelected(MouseEvent e) {
		selectedSection = listOfSections.getFocusModel().getFocusedItem();
		if(selectedSection == null) {
			return;
		}
		assignProf.setDisable(false);
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