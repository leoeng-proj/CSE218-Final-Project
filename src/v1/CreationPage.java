package v1;

import java.util.Arrays;
import java.util.Collection;

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
		
		TextField courseName = defaultTextField("Course Name", root, 0, 0, 2, 1);
		TextField description = defaultTextField("Description", root, 0, 1, 2, 1);
		TextField courseNum = defaultTextField("Course Number", root, 0, 2, 2, 1);		

		ComboBox<Integer> credits = defaultComboBox(Arrays.asList(new Integer[] {1, 2, 3, 4, 5}), "Select Credit Value", root, 0, 3, 2, 1);
		ComboBox<Major> majors = defaultComboBox(Arrays.asList(Major.values()), "Select Major", root, 0, 4, 2, 1);
		
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		root.add(submit, 0, 5, 1, 1);
		
		root.add(close, 1, 5, 1, 1);
	}
	public void studentCreationPage() {
		// Student(Name name, Major major, double gpa)
		GridPane root = initPage(300, 400, "Student Creation");
		Button close = defaultClose(root);
		root.add(close, 1, 3);
		
		TextField firstname = defaultTextField("First Name", root, 0, 0, 2, 1);
		TextField lastname = defaultTextField("Last Name", root, 0, 1, 2, 1);
		
		ComboBox<Major> majors = defaultComboBox(Arrays.asList(Major.values()), "Select Major", root, 0, 2, 2, 1);
		
		Button submit = new Button("Submit");
		submit.getStyleClass().add("button-style");
		root.add(submit, 0, 3);
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
	private <T> ComboBox<T> defaultComboBox(Collection<T> col, String promptText, GridPane root, int x, int y, int w, int h){
		ComboBox<T> cmb = new ComboBox<>();
		cmb.setPromptText(promptText);
		cmb.getStyleClass().add("textfield-style");
		cmb.getItems().addAll(col);
		root.add(cmb, x, y, w, h);
		return cmb;
	}
	private TextField defaultTextField(String promptText, GridPane root, int x, int y, int w, int h) {
		TextField tf = new TextField();
		tf.setPromptText(promptText);
		tf.getStyleClass().add("textfield-style");
		root.add(tf, x, y, w, h);
		return tf;
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
