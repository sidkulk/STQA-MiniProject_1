package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import alertBoxPack.AlertBoxClass;
import javafx.event.ActionEvent;

public class AdminDashBoardFXMLController {
	@FXML
	private Button AddNewDriveBtn;

	@FXML
    private AnchorPane accountRootPane;	
	
	@FXML
	private Button RemoveSelectedDriveBtn;

	@FXML
	private Button addNewStudBtn;

	@FXML
	private Button removeSelStudBtn;

	@FXML
	private Button logOutBtn;

	@FXML
	public void addNewDrive(ActionEvent event) {

	}

	@FXML
	public void removeSelDrive(ActionEvent event) {

	}

	@FXML
	public void addNewStud(ActionEvent event) {

	}

	@FXML
	public void removeSelectedStud(ActionEvent event) {

	}

	@FXML
	void logOutAction(ActionEvent event) throws Exception {
		AlertBoxClass.logOutConfirmation(accountRootPane);
	}
}
