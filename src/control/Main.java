package control;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Course;
import model.Section;
import model.Student;
import structs.CourseContainer;
import structs.Removal;
import structs.SectionContainer;
import structs.StudentContainer;

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
	public Pane homepage(CreationPage creator) throws IOException {
		BorderPane root = new BorderPane();
//		root.getStyleClass().add("pane-style");
		root.setPadding(new Insets(10, 10, 10, 10));
		root.disableProperty().bind(creator.getIsOpen());

		GridPane controls = new GridPane();
		controls.getStyleClass().add("pane-style");
		controls.add(creationButtons(creator), 0, 0);
		controls.add(displayContainers(root), 0, 1);
		root.setLeft(controls);
		
		AnchorPane classroomManager = FXMLLoader.load(getClass().getResource("ClassroomManager.fxml"));
		root.setRight(classroomManager);
		
		return root;
}
	public GridPane displayContainers(Pane parent) {
		GridPane root = new GridPane();
		root.getStyleClass().add("pane-style");

		ListView<Student> studentView = new ListView<>();
		studentView.setUserData(DataCenter.getInstance().getContainers().getStudentContainer());
		studentView.getStyleClass().add("listview-style");
		root.add(studentView, 0, 1, 2, 1);
		ListView<Section> sectionView = new ListView<>();
		sectionView.setUserData(DataCenter.getInstance().getContainers().getSectionContainer());
		sectionView.getStyleClass().add("listview-style");
		root.add(sectionView, 0, 1, 2, 1);
		ListView<Course> courseView = new ListView<>();
		courseView.setUserData(DataCenter.getInstance().getContainers().getCourseContainer());
		courseView.getStyleClass().add("listview-style");
		root.add(courseView, 0, 1, 2, 1);
//		ListView<Professor> professorView = new ListView<>();
		
		Node[] views = {sectionView, studentView, courseView};
		ClickCounter counter = new ClickCounter();
		
		String[] titles = {"Sections", "Students", "Courses"};
		Label titleOfView = new Label(titles[counter.getCount()]);
		titleOfView.getStyleClass().add("label-style");
		root.add(titleOfView, 0, 0, 1, 1);
		
		Button remove = new Button("Remove");
		remove.getStyleClass().add("large-button-style");
		remove.setOnAction(e -> {
			ListView<?> lv = (ListView<?>)views[counter.getCount()];
			Object obj = lv.getFocusModel().getFocusedItem();
			if(obj == null) {
				return;
			} 
			Removal container = (Removal)lv.getUserData();
			container.remove(obj);
			refresh(studentView, sectionView, courseView, remove, views, counter);
		});
		root.add(remove, 0, 2);

		refresh(studentView, sectionView, courseView, remove, views, counter);
		parent.disableProperty().addListener(e -> {
			refresh(studentView, sectionView, courseView, remove, views, counter);
		});
		
		Button cycle = new Button("Cycle Views");
		cycle.getStyleClass().add("large-button-style");
		views[counter.getCount()].toFront();
		cycle.setOnAction(e -> {
			counter.click();
			if(counter.getCount() == views.length) {
				counter.reset();
			}
			views[counter.getCount()].toFront();
			refresh(studentView, sectionView, courseView, remove, views, counter);
			titleOfView.setText(titles[counter.getCount()]);
		});
		root.add(cycle, 1, 0, 1, 1);
		
		Button emit = new Button("Emit");
		emit.getStyleClass().add("large-button-style");
		emit.setOnAction(e -> {
			Object data = views[counter.getCount()].getUserData();
			if (data instanceof StudentContainer) {
				StudentContainer studentData = (StudentContainer) data;
				studentData.addStudent(Emitter.emitStudent());
			} 
			else if (data instanceof SectionContainer) {
				SectionContainer sectionData = (SectionContainer) data;
				if(((CourseContainer)(courseView.getUserData())).isEmpty()) {
					Alert noCourses = new Alert(AlertType.WARNING);
					noCourses.setHeaderText("No Courses");
					noCourses.show();
					return;
				}
				sectionData.addSection(Emitter.emitSection(null, ((CourseContainer)(courseView.getUserData())).getRandomCourse()));
			} 
			else if (data instanceof CourseContainer) {
				CourseContainer courseData = (CourseContainer) data;
				courseData.addCourse(Emitter.emitCourse());
			}
			refresh(studentView, sectionView, courseView, remove, views, counter);
		});
		root.add(emit, 1, 2);
		return root;
	}
	public void refresh(ListView<Student> studentView, ListView<Section> sectionView, ListView<Course> courseView, Button remove, Node[] views, ClickCounter counter) {
		studentView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getStudentContainer().toArray()));
		sectionView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getSectionContainer().toArray()));
		courseView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getCourseContainer().toArray()));
		if(((ListView<?>)(views[counter.getCount()])).getItems().isEmpty()) {
			System.out.println();
			remove.setDisable(true);
		}
		else {
			remove.setDisable(false);
		}
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