package control;


import java.util.LinkedList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Course;
import model.Day;
import model.Hours;
import model.Major;
import model.Name;
import model.Professor;
import model.Section;
import model.Student;

public class CreationPage {
	
	private BooleanProperty isOpen;
	
	public CreationPage() {
		this.isOpen = new SimpleBooleanProperty(false);
	}
	public void courseCreationPage() {
		// Course(double credits, String name, String description, String courseNum, Major[] reqMajors)
		GridPane root = initPage(300, 400, "Course Creation");
		Button close = defaultClose(root);
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		
		TextField courseName = defaultTextField("Course Name", root, 0, 0, 2, 1);
		TextField description = defaultTextField("Description", root, 0, 1, 2, 1);
		TextField courseNum = defaultTextField("Course Number", root, 0, 2, 2, 1);	

		ComboBox<Double> credits = defaultComboBox(new Double[] {1.0, 2.0, 3.0, 4.0, 5.0}, 
				"Select Credit Value", root, 0, 3, 2, 1);
		ComboBox<Major> majors = defaultComboBox(Major.values(), "Select Major", root, 0, 4, 2, 1);
		
		ChangeListener<Object> listener = new ChangeListener<>() {
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				if(courseName.getLength() > 0 && description.getLength() > 0 && courseNum.getLength() > 0 &&
						credits.getValue() != null && majors.getValue() != null) {
					submit.setDisable(false);
				}
				else { 
					submit.setDisable(true);
				}
			}
		};
		courseName.textProperty().addListener(listener);
		description.textProperty().addListener(listener);
		courseNum.textProperty().addListener(listener);
		credits.valueProperty().addListener(listener);
		majors.valueProperty().addListener(listener);
		
		submit.setDisable(true);
		submit.setOnAction(e -> {
			Course course = new Course(credits.getValue(), courseName.getText(),
					description.getText(), courseNum.getText(), majors.getValue());
			DataCenter.getInstance().getContainers().getCourseContainer().addCourse(course);
			closeWindow(root);
		});
		
		root.add(submit, 0, 5, 1, 1);
		root.add(close, 1, 5, 1, 1);
	}
	public BooleanProperty getIsOpen() {
		return isOpen;
	}
	public void professorCreationPage() {
		//Professor(Name name)
		GridPane root = initPage(300, 400, "Professor Creation");
		Button close = defaultClose(root);
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-syle");
		
		TextField firstname = defaultTextField("First Name", root, 0, 0, 2, 1);
		TextField lastname = defaultTextField("Last Name", root, 0, 1, 2, 1);
		ComboBox<Hours> prefTimeSelect = defaultComboBox(Hours.values(), "Preferred Time", root, 0, 2, 2, 1);
		
		submit.setOnAction(e -> {
			Professor prof = new Professor(new Name(firstname.getText(), lastname.getText()), prefTimeSelect.getValue());
			DataCenter.getInstance().getContainers().getProfessorContainer().addProfessor(prof);
			closeWindow(root);
		});
		submit.setDisable(true);
		root.add(submit, 0, 3, 1, 1);
		root.add(close, 1, 3, 1, 1);
		
		ChangeListener<Object> listener = new ChangeListener<>() {
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				if(firstname.getLength() > 0 && lastname.getLength() > 0 && prefTimeSelect.getValue() != null) {
					submit.setDisable(false);
				}
				else {
					submit.setDisable(true);
				}
			}
		};
		firstname.textProperty().addListener(listener);
		lastname.textProperty().addListener(listener);
		prefTimeSelect.valueProperty().addListener(listener);
	}
	public void sectionCreationPage() {
		//Section(int sectionNum, boolean isOnline, Classroom room, Course course,
					//ListBag<String> textbooks, Day[] daysOffered, Hours time)
		GridPane root = initPage(400, 500, "Section Creation");
		Button close = defaultClose(root);
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		
		TextField sectionNum = defaultTextField("Section Number (5 digits)", root, 0, 0, 2, 1);
		Button randomSectionNum = new Button("Random");
		randomSectionNum.setOnAction(e -> {
			sectionNum.setText((int)(Math.random() * 90000) + 10000 + "");
		});
		randomSectionNum.getStyleClass().add("button-style");
		root.add(randomSectionNum, 3, 0, 1, 1);
		
		ComboBox<Course> courses = defaultComboBox((Course[])DataCenter.getInstance().getContainers().getCourseContainer().toArray(), 
				"Select Course", root, 0, 1, 2, 1);
		
		CheckBox isOnline = new CheckBox("Online?");
		root.add(isOnline, 0, 2);
		
		Label lbl = new Label("Select the Day(s) For This Section:");
		lbl.getStyleClass().add("textfield-style");
		root.add(lbl, 0, 3, 2, 1);
		
		int count = 4;
		LinkedList<Day> daysSelected = new LinkedList<>(); 
		ChangeListener<Object> listener = new ChangeListener<>() {
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				if(sectionNum.getLength() == 5 && courses.getValue() != null && !daysSelected.isEmpty()) {
			        submit.setDisable(false);
			    } 
				else {
			        submit.setDisable(true);
			    }
			}
		};
		for(Day d : Day.values()) {
			CheckBox dayCheckBox = new CheckBox(d + "");
			root.add(dayCheckBox, 0, count++, 1, 1);
			dayCheckBox.setUserData(d);
		    dayCheckBox.setOnAction(e -> {
		        CheckBox cb = (CheckBox) e.getSource();
		        Day day = (Day) cb.getUserData();  
		        if(cb.isSelected()) {
		            daysSelected.add(day);
		        } 
		        else {
		            daysSelected.remove(day);
		        }
		        listener.changed(null, null, null);
		    });
		}
		sectionNum.textProperty().addListener(listener);
		courses.valueProperty().addListener(listener);
		
		root.add(submit, 0, count, 1, 1);
		submit.setDisable(true);
		submit.setOnAction(e -> {
			Section section = new Section(Integer.parseInt(sectionNum.getText()), isOnline.isSelected(), null, 
					courses.getValue(), null, daysSelected.toArray(new Day[0]), null);
			DataCenter.getInstance().getContainers().getSectionContainer().addSection(section);
			closeWindow(root);
		});
		root.add(close, 1, count, 1, 1);
	}
	public void studentCreationPage() {
		// Student(Name name, Major major, double gpa)
		GridPane root = initPage(300, 400, "Student Creation");
		Button close = defaultClose(root);
		root.add(close, 1, 3);
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		root.add(submit, 0, 3);
		
		TextField firstname = defaultTextField("First Name", root, 0, 0, 2, 1);
		TextField lastname = defaultTextField("Last Name", root, 0, 1, 2, 1);
		
		ComboBox<Major> majors = defaultComboBox(Major.values(), "Select Major", root, 0, 2, 2, 1);
		
		ChangeListener<Object> listener = new ChangeListener<>() {
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				if(firstname.getLength() > 0 && lastname.getLength() > 0 &&  majors.getValue() != null) {
					submit.setDisable(false);
				}
				else { 
					submit.setDisable(true);
				}
			}
		};
		firstname.textProperty().addListener(listener);
		lastname.textProperty().addListener(listener);
		majors.valueProperty().addListener(listener);
		submit.setDisable(true);
		submit.setOnAction(e -> {
			Student student = new Student(new Name(firstname.getText(), lastname.getText()), majors.getValue(), 0.0);
			DataCenter.getInstance().getContainers().getStudentContainer().addStudent(student);
			closeWindow(root);
		});
	}
	private void closeWindow(GridPane root) {
		isOpen.set(false);
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}
	private Button defaultClose(GridPane root) {
		Button close = new Button("Close");
		close.getStyleClass().add("button-style");
		close.setOnAction(e -> {
			closeWindow(root);
		});
		return close;
	}
	private <T> ComboBox<T> defaultComboBox(T[] col, String promptText, GridPane root, int x, int y, int w, int h){
		ComboBox<T> cmb = new ComboBox<>();
		cmb.setPromptText(promptText);
		cmb.getStyleClass().add("textfield-style");
		cmb.getItems().addAll(col);
		root.add(cmb, x, y, w, h);
		return cmb;
	}
	private GridPane defaultGridPane() {
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.getStyleClass().add("pane-style");
		return root;
	}
	private Scene defaultScene(GridPane root) {
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		return scene;
	}
	private Stage defaultStage(String title) {
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setOnCloseRequest(e -> {		
			isOpen.set(false);
		});
		return stage;
	}
	private TextField defaultTextField(String promptText, GridPane root, int x, int y, int w, int h) {
		TextField tf = new TextField();
		tf.setPromptText(promptText);
		tf.getStyleClass().add("textfield-style");
		root.add(tf, x, y, w, h);
		return tf;
	}
	private GridPane initPage(int width, int height, String title) {
		isOpen.set(true);
		Stage stage = defaultStage(title);
		GridPane root = defaultGridPane();
		Scene scene = defaultScene(root);
		stage.setResizable(false);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setScene(scene);
		stage.show();
		return root;
	}
}
