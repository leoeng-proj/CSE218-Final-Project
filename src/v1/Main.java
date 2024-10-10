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
		Scene scene = new Scene(homepage());
		
		stage.setResizable(false);
		stage.setWidth(500);
		stage.setHeight(600);
		stage.setScene(scene);
		stage.show();
	}
	public GridPane homepage() {
		GridPane root = new GridPane();
		
		Button createStudent = new Button("Create Student");
		createStudent.setOnAction(e -> {
			studentCreationPage(root);
		});
		root.add(createStudent, 0, 0);
		
		root.setAlignment(Pos.CENTER);
		return root;
	}
	public void studentCreationPage(GridPane parent) {
		parent.setDisable(true);
		Stage stage = new Stage();
		stage.setOnCloseRequest(e -> {
			parent.setDisable(false);
		});
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setWidth(300);
		stage.setHeight(400);
		stage.setScene(scene);
		stage.show();
	
		Button close = new Button("Close");
		close.setOnAction(e -> {
			parent.setDisable(false);
			stage.close();
		});
		root.add(close, 1, 2);
		
		TextField firstname = new TextField();
		firstname.setPromptText("First Name");
		root.add(firstname, 0, 0);
		
		TextField lastname = new TextField();
		lastname.setPromptText("Last Name");
		root.add(lastname, 0, 1);
		
		ComboBox<Major> majors = new ComboBox<>();
		majors.setPromptText("Select Major");
		majors.getItems().addAll(Major.values());
		root.add(majors, 0, 2);
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
