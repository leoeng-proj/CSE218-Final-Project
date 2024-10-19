package v1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import v1.model.Classroom;
import v1.model.Section;

public class ClassroomManager {

	private static final Classroom[] rooms = {
		//Classroom(String roomID, boolean hasProjector, int maxCapacity) {
		new Classroom("CL01", 40),
		new Classroom("CL02", 25),
		new Classroom("CL03", 30),
		new Classroom("CL04", 30),
		new Classroom("CL05", 30),
		new Classroom("CL06", 30),
		new Classroom("CL07", 20),
	};
	@FXML
	Button clOne;
	@FXML
	Button clTwo;
	@FXML
	Button clThree;
	@FXML
	Button clFour;
	@FXML
	Button clFive;
	@FXML
	Button clSix;
	@FXML
	Button clSeven;
	@FXML
	ListView<Section> listOfSections;
	@FXML
	ListView<Section> sectionPeople;
	@FXML
	TextArea sectionInfo;
}
