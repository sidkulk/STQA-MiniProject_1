package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import alertBoxPack.AlertBoxClass;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;

public class StudentDashBoardFXMLController {
	@FXML
	private AnchorPane studDashRootPane;
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
	private Button applyForDriveBtn;
	@FXML
	private TableColumn<?, ?> yourDriveId;
	@FXML
	private TableColumn<?, ?> yourCompName;
	@FXML
	private TableColumn<?, ?> yourDateOfDrive;
	@FXML
	private TableColumn<?, ?> yourDriveCTC;
	@FXML
	private Button logoutbtn;

	// Event Listener on Button[#appiyForDriveBtn].onAction
	@FXML
	public void applyForSelectedDrive(ActionEvent event) {

	}

	// Event Listener on Button[#logoutbtn].onAction
	@FXML
	public void logOut(ActionEvent event) throws Exception {
		AlertBoxClass.logOutConfirmation(studDashRootPane);
	}
}
