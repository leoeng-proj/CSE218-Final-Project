package control;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import structs.CourseContainer;
import structs.Information;
import structs.ProfessorContainer;
import structs.Removal;
import structs.SectionContainer;
import structs.StudentContainer;

public class Main extends Application {

	private class ClickCounter {
		int count = 0;
		void click() {
			count++;
		}
		int getCount() {
			return count;
		}
		void reset() {
			count = 0;
		}
	}
	public static void main(String[] args) {
		Application.launch(args);
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
	public GridPane displayContainers(Pane parent) {
		GridPane root = new GridPane();
		root.getStyleClass().add("pane-style");

		TextArea info = new TextArea("Select An Item To View Information");
		info.setEditable(false);
		info.getStyleClass().add("listview-style"); //same size as other nodes in method
		root.add(info, 0, 2, 3, 1);
		
		ListView<Student> studentView = makeListView(root, info);
		studentView.setUserData(DataCenter.getInstance().getContainers().getStudentContainer());
		ListView<Section> sectionView = makeListView(root, info);
		sectionView.setUserData(DataCenter.getInstance().getContainers().getSectionContainer());
		ListView<Course> courseView = makeListView(root, info);
		courseView.setUserData(DataCenter.getInstance().getContainers().getCourseContainer());
		ListView<Professor> professorView = makeListView(root, info);
		professorView.setUserData(DataCenter.getInstance().getContainers().getProfessorContainer());
		
		Node[] views = {sectionView, studentView, courseView, professorView};
		ClickCounter counter = new ClickCounter();
		
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
			refresh(studentView, sectionView, courseView, professorView, remove, views, counter);
			info.clear();
		});
		root.add(remove, 0, 3);

		refresh(studentView, sectionView, courseView, professorView, remove, views, counter);
		parent.disableProperty().addListener(e -> {
			refresh(studentView, sectionView, courseView, professorView, remove, views, counter);
		});
		
		String[] titles = {"Sections", "Students", "Courses", "Professors"};
		Label titleOfView = new Label(titles[counter.getCount()]);
		titleOfView.getStyleClass().add("label-style");
		root.add(titleOfView, 0, 0);
		
		Button cycle = new Button("Cycle Views");
		cycle.getStyleClass().add("large-button-style");
		views[counter.getCount()].toFront();
		cycle.setOnAction(e -> {
			counter.click();
			if(counter.getCount() == views.length) {
				counter.reset();
			}
			views[counter.getCount()].toFront();
			refresh(studentView, sectionView, courseView, professorView, remove, views, counter);
			titleOfView.setText(titles[counter.getCount()]);
		});
		root.add(cycle, 1, 0);
		
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
				for(int i = 0; i < 3; i++) { //attempt random course 3 times
					Course c = Emitter.emitCourse();
					if(courseData.addCourse(c)) {
						break;
					}
				}
			}
			else if (data instanceof ProfessorContainer) {
				ProfessorContainer courseData = (ProfessorContainer) data;
				courseData.addProfessor(Emitter.emitProfessor());
			}
			refresh(studentView, sectionView, courseView, professorView, remove, views, counter);
		});
		root.add(emit, 1, 3);
		
		Button assign = new Button("Auto Assign");
		assign.getStyleClass().add("large-button-style");
		assign.setOnAction(e -> {
			DataCenter.getInstance().getContainers().autoAssign();
		});
		root.add(assign, 2, 3);
		return root;
	}
	public Pane homepage(CreationPage creator) throws IOException {
		HBox root = new HBox();
		root.getStyleClass().add("pane-style");
		root.disableProperty().bind(creator.getIsOpen());
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(10);
		
		GridPane controls = new GridPane();
		controls.getStyleClass().add("pane-style");
		controls.add(creationButtons(creator), 0, 0);
		controls.add(displayContainers(root), 0, 1);
		HBox.setHgrow(controls, Priority.ALWAYS);
		
		AnchorPane classroomManager = FXMLLoader.load(getClass().getResource("ClassroomManager.fxml"));
		HBox.setHgrow(classroomManager, Priority.ALWAYS);

		root.getChildren().addAll(controls, classroomManager);
		return root;
	}
	public void refresh(ListView<Student> studentView, ListView<Section> sectionView, ListView<Course> courseView, ListView<Professor> professorView, 
			Button remove, Node[] views, ClickCounter counter) {
		studentView.setItems(DataCenter.getInstance().getContainers().getStudentContainer().getObservableStudentContainer());
		sectionView.setItems(DataCenter.getInstance().getContainers().getSectionContainer().getObservableSectionContainer());
		courseView.setItems(DataCenter.getInstance().getContainers().getCourseContainer().getObservableCourseContainer());
		professorView.setItems(DataCenter.getInstance().getContainers().getProfessorContainer().getObservableProfessorContainer());
		if(((ListView<?>)(views[counter.getCount()])).getItems().isEmpty()) {
			remove.setDisable(true);
		}
		else {
			remove.setDisable(false);
		}
	}
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
		stage.setWidth(800);
		stage.setHeight(800);
		stage.setScene(scene);
		stage.setTitle("College Manager");
		stage.show();
	}
	private <T extends Information> ListView<T> makeListView(GridPane root, TextArea info){
		ListView<T> view = new ListView<>();
		view.getStyleClass().add("listview-style");
		view.setOnMouseClicked(e -> {
			Information i = view.getFocusModel().getFocusedItem();
			if(i == null) {
				return;
			}
			info.setText(i.getInfo());
		});
		root.add(view, 0, 1, 3, 1);
		return view;
	}
}