package v1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import v1.model.Major;

public class Main extends Application {

	public void start(Stage arg0) throws Exception {
		Stage stage = new Stage();
		CreationPage creator = new CreationPage();
		Scene scene = new Scene(homepage(creator));
		stage.setOnCloseRequest(e -> {
			System.exit(1);
		});
		
		stage.setResizable(false);
		stage.setWidth(500);
		stage.setHeight(600);
		stage.setScene(scene);
		stage.show();
	}
	public GridPane homepage(CreationPage creator) {
		GridPane root = new GridPane();
		
		root.disableProperty().bind(creator.getIsOpen());
		
		Button createCourse = new Button("Create Course");
		createCourse.setOnAction(e -> {
			creator.courseCreationPage();
		});		
		root.add(createCourse, 0, 1);
		
		Button createStudent = new Button("Create Student");
		createStudent.setOnAction(e -> {
			creator.studentCreationPage();
		});
		root.add(createStudent, 0, 0);
		
		root.setAlignment(Pos.CENTER);
		return root;
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
