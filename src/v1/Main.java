package v1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import v1.model.Course;
import v1.model.Professor;
import v1.model.Section;
import v1.model.Student;

public class Main extends Application {

	public void start(Stage arg0) throws Exception {
		Stage stage = new Stage();
		CreationPage creator = new CreationPage();
		Scene scene = new Scene(homepage(creator));
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		stage.setOnCloseRequest(e -> {
			DataCenter.getInstance().save();
			System.exit(1);
		});
		
		stage.setResizable(false);
		stage.setWidth(500);
		stage.setHeight(600);
		stage.setScene(scene);
		stage.setTitle("College Manager");
		stage.show();
	}
	public GridPane homepage(CreationPage creator) {
		GridPane root = new GridPane();
		root.getStyleClass().add("pane-style");
		root.disableProperty().bind(creator.getIsOpen());

		root.add(creationButtons(creator), 0, 0);
		root.add(displayContainers(), 0, 1);
		
		return root;
	}
	public GridPane displayContainers() {
		GridPane root = new GridPane();
		root.getStyleClass().add("pane-style");
		
		ListView<Student> studentView = new ListView<>();
		studentView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getStudentContainer().toArray()));
		root.add(studentView, 0, 0);
		ListView<Section> sectionView = new ListView<>();
		sectionView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getSectionContainer().toArray()));
		root.add(sectionView, 1, 0);
		ListView<Course> courseView = new ListView<>();
		courseView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getCourseContainer().toArray()));
		root.add(courseView, 2, 0);
//		ListView<Professor> professorView = new ListView<>();
		
		
		
		return root;
	}
	public GridPane creationButtons(CreationPage creator) {
		GridPane root = new GridPane();
		root.getStyleClass().add("pane-style");
		
		Button createStudent = new Button("Create Student");
		createStudent.setOnAction(e -> {
			creator.studentCreationPage();
		});
		createStudent.getStyleClass().add("large-button-style");
		root.add(createStudent, 0, 0);
		Button createProfessor = new Button("Create Professor");
		createProfessor.setOnAction(e -> {
			creator.professorCreationPage();
		});	
		createProfessor.getStyleClass().add("large-button-style");
		root.add(createProfessor, 1, 0);
		
		Button createSection = new Button("Create Section");
		createSection.setOnAction(e -> {
			creator.sectionCreationPage();
		});	
		createSection.getStyleClass().add("large-button-style");
		root.add(createSection, 0, 1);
		
		Button createCourse = new Button("Create Course");
		createCourse.setOnAction(e -> {
			creator.courseCreationPage();
		});		
		createCourse.getStyleClass().add("large-button-style");
		root.add(createCourse, 1, 1);
		
		root.setAlignment(Pos.CENTER);
		return root;
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
