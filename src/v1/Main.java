package v1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import v1.structs.Removal;

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

		GridPane controls = new GridPane();
		controls.getStyleClass().add("pane-style");
		controls.add(creationButtons(creator), 0, 0);
		controls.add(displayContainers(root), 0, 1);
		
		root.setLeft(controls);
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
		
		Label titleOfView = new Label(views[counter.getCount()].getUserData().toString());
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
			refreshViews(studentView, sectionView, courseView);
			checkRemoveButton(remove, views, counter);
		});
		checkRemoveButton(remove, views, counter);
		root.add(remove, 0, 2);
		
		Button cycle = new Button("Cycle Views");
		cycle.getStyleClass().add("large-button-style");
		views[counter.getCount()].toFront();
		cycle.setOnAction(e -> {
			counter.click();
			if(counter.getCount() == views.length) {
				counter.reset();
			}
			views[counter.getCount()].toFront();
			checkRemoveButton(remove, views, counter);
			titleOfView.setText(views[counter.getCount()].getUserData().toString());
		});
		root.add(cycle, 1, 0, 1, 1);
		
		refreshViews(studentView, sectionView, courseView);
		parent.disableProperty().addListener(e -> {
			refreshViews(studentView, sectionView, courseView);
			checkRemoveButton(remove, views, counter);
		});
		return root;
	}
	public void refreshViews(ListView<Student> studentView, ListView<Section> sectionView, ListView<Course> courseView) {
		studentView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getStudentContainer().toArray()));
		sectionView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getSectionContainer().toArray()));
		courseView.setItems(FXCollections.observableArrayList(DataCenter.getInstance().getContainers().getCourseContainer().toArray()));
	}
	public void checkRemoveButton(Button remove, Node[] views, ClickCounter counter) {
		if(((ListView<?>)(views[counter.getCount()])).getItems().isEmpty()) {
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
