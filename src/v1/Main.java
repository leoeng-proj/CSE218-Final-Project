package v1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import v1.model.Course;
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
		stage.setWidth(700);
		stage.setHeight(800);
		stage.setScene(scene);
		stage.setTitle("College Manager");
		stage.show();
	}
	public Pane homepage(CreationPage creator) {
		BorderPane root = new BorderPane();
//		root.getStyleClass().add("pane-style");
		root.setPadding(new Insets(10, 10, 10, 10));
		root.disableProperty().bind(creator.getIsOpen());
		root.setLeft(displayContainers(root));
		root.setRight(creationButtons(creator));
//		root.add(creationButtons(creator), 0, 0);
//		root.add(displayContainers(root), 0, 1);
		
		
		return root;
	}
	public GridPane displayContainers(Pane parent) {
		GridPane root = new GridPane();
		root.getStyleClass().add("pane-style");
		
		ListView<Student> studentView = new ListView<>();
		studentView.getStyleClass().add("listview-style");
		studentView.setUserData("Students");
		root.add(studentView, 0, 1, 2, 1);
		ListView<Section> sectionView = new ListView<>();
		sectionView.setUserData("Sections");
		sectionView.getStyleClass().add("listview-style");
		root.add(sectionView, 0, 1, 2, 1);
		ListView<Course> courseView = new ListView<>();
		courseView.setUserData("Courses");
		courseView.getStyleClass().add("listview-style");
		root.add(courseView, 0, 1, 2, 1);
//		ListView<Professor> professorView = new ListView<>();
		
		Label titleOfView = new Label(courseView.getUserData().toString());
		titleOfView.getStyleClass().add("label-style");
		root.add(titleOfView, 0, 0, 1, 1);
		
		Node[] views = {studentView, sectionView, courseView};
		Button cycle = new Button("Cycle Views");
		cycle.getStyleClass().add("large-button-style");
		ClickCounter counter = new ClickCounter();
		cycle.setOnAction(e -> {
			views[counter.getCount()].toFront();
			titleOfView.setText(views[counter.getCount()].getUserData().toString());
			counter.click();
			if(counter.getCount() == views.length) {
				counter.reset();
			}
		});
		root.add(cycle, 1, 0, 1, 1);
		refreshViews(studentView, sectionView, courseView);
		parent.disableProperty().addListener(e -> {
			refreshViews(studentView, sectionView, courseView);
		});
		return root;
	}
	public void refreshViews(ListView<Student> studentView, ListView<Section> sectionView, ListView<Course> courseView) {
		studentView.setItems(DataCenter.getInstance().getObservableStudentContainer());
		sectionView.setItems(DataCenter.getInstance().getObservableSectionContainer());
		courseView.setItems(DataCenter.getInstance().getObservableCourseContainer());
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
	private class ClickCounter {
		int count = 0;
		void click() {
			count++;
		}
		void reset() {
			count = 0;
		}
		int getCount() {
			return count;
		}
	}
}
