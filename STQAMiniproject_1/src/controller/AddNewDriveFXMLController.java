package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import screenPack.ScreenPackClass;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;

public class AddNewDriveFXMLController {
	@FXML
	private TextField companyName;
	@FXML
	private TextField branchTxtBox;
	@FXML
	private AnchorPane newDriveRootPane;
	@FXML
	private TextField TenthMinPerc;
	@FXML
	private TextField TwelthMinPerc;
	@FXML
	private TextField BEMinPerc;
	@FXML
	private TextField MaxActiveBacks;
	@FXML
	private TextField MaxDeadBacks;
	@FXML
	private TextField ctcOfCompany;
	@FXML
	private DatePicker dateOfDrive;
	@FXML
	private Button addDriveBtn;
	@FXML
	private Button cancelDriveBtn;

	// Event Listener on Button[#addDriveBtn].onAction
	@FXML
	public void addDriveToDatabase(ActionEvent event) {

	}

	// Event Listener on Button[#cancelDriveBtn].onAction
	@FXML
	public void backToAdminDash(ActionEvent event) throws Exception {
		ScreenPackClass.showAdminDashScreen(newDriveRootPane);
	}
}
