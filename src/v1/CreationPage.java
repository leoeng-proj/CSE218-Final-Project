package v1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import v1.model.Major;
import v1.model.Name;

public class CreationPage {
	
	private BooleanProperty isOpen;
	
	public CreationPage() {
			this.isOpen = new SimpleBooleanProperty(false);
	}
	public BooleanProperty getIsOpen() {
		return isOpen;
	}
	public void courseCreationPage() {
		// Course(double credits, String name, String description, String courseNum, Major[] reqMajors)
		GridPane root = initPage(300, 400, "Course Creation");
		Button close = defaultClose(root);
		
		TextField courseName = new TextField();
		courseName.setPromptText("Course Name");
		courseName.getStyleClass().add("textfield-stlye");
		root.add(courseName, 0, 0);
		
		TextField description = new TextField();
		description.setPromptText("Description");
		description.getStyleClass().add("textfield-stlye");
		root.add(description, 0, 1);

		TextField courseNum = new TextField();
		courseNum.setPromptText("Course Number");
		courseNum.getStyleClass().add("textfield-stlye");
		root.add(courseNum, 0, 2);
		

		ComboBox<Integer> credits = new ComboBox<>();
		credits.setPromptText("Select Credit Value");
		credits.getStyleClass().add("textfield-style");		
		credits.getItems().addAll(new Integer[] {1, 2, 3, 4, 5});
		root.add(credits, 0, 3, 3, 1);
		
		ComboBox<Major> majors = new ComboBox<>();
		majors.setPromptText("Select Major");
		majors.getStyleClass().add("textfield-style");		
		majors.getItems().addAll(Major.values());
		root.add(majors, 0, 4, 3, 1);
		
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		root.add(submit, 0, 5);
		
		root.add(close, 1, 5);
	}
	public void studentCreationPage() {
		// Student(Name name, Major major, double gpa)
		GridPane root = initPage(300, 400, "Student Creation");
		Button close = defaultClose(root);
		
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		root.add(submit, 0, 3);
		
		root.add(close, 1, 3);
		
		TextField firstname = new TextField();
		firstname.setPromptText("First Name");
		firstname.getStyleClass().add("textfield-style");
		root.add(firstname, 0, 0, 3, 1);
		
		TextField lastname = new TextField();
		lastname.setPromptText("Last Name");
		lastname.getStyleClass().add("textfield-style");
		root.add(lastname, 0, 1, 3, 1);
		
		ComboBox<Major> majors = new ComboBox<>();
		majors.setPromptText("Select Major");
		majors.getStyleClass().add("textfield-style");		
		majors.getItems().addAll(Major.values());
		root.add(majors, 0, 2, 3, 1);
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
	private Stage defaultStage(String title) {
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setOnCloseRequest(e -> {		
			isOpen.set(false);
		});
		return stage;
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
	private Button defaultClose(GridPane root) {
		Button close = new Button("Close");
		close.getStyleClass().add("button-style");
		close.setOnAction(e -> {
			isOpen.set(false);
			Stage stage = (Stage)root.getScene().getWindow();
			stage.close();
		});
		return close;
	}
}
