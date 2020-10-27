package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import screenPack.ScreenPackClass;
import alertBoxPack.AlertBoxClass;
import javafx.event.ActionEvent;

public class AdminDashBoardFXMLController {
	@FXML
	private TabPane rootAdminDashPane;

	@FXML
	private AnchorPane campusDrivePane;

	@FXML
	private Button AddNewDriveBtn;

	@FXML
	private Button RemoveSelectedDriveBtn;

	@FXML
	private TableColumn<?, ?> driveID;

	@FXML
	private TableColumn<?, ?> compName;

	@FXML
	private TableColumn<?, ?> driveDate;

	@FXML
	private TableColumn<?, ?> driveCTC;

	@FXML
	private TableColumn<?, ?> driveBranch;

	@FXML
	private TableColumn<?, ?> xthPerc;

	@FXML
	private TableColumn<?, ?> xiiPerc;

	@FXML
	private TableColumn<?, ?> beAvgPerc;

	@FXML
	private TableColumn<?, ?> maxDeadbacks;

	@FXML
	private TableColumn<?, ?> maxLiveBacks;

	@FXML
	private Button logOutBtnOne;

	@FXML
	private AnchorPane studInfoPane;

	@FXML
	private Button addNewStudBtn;

	@FXML
	private Button removeSelStudBtn;

	@FXML
	private Button logOutBtnTwo;

	@FXML
	public void addNewDrive(ActionEvent event) throws Exception {
		ScreenPackClass.showAddNewDriveScreen(campusDrivePane);
	}

	@FXML
	public void removeSelDrive(ActionEvent event) {

	}

	@FXML
	public void addNewStud(ActionEvent event) throws Exception {
		ScreenPackClass.showAddNewStudentScreen(studInfoPane);
	}

	@FXML
	public void removeSelectedStud(ActionEvent event) {

	}

	@FXML
	void logOutAction(ActionEvent event) throws Exception {
		AlertBoxClass.logOutConfirmation(campusDrivePane);
	}

	@FXML
	void logOutActionTwo(ActionEvent event) throws Exception {
		logOutAction(event);
	}
}
