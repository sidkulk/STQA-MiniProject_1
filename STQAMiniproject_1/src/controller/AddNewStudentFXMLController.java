package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import screenPack.ScreenPackClass;
import javafx.scene.control.ComboBox;

public class AddNewStudentFXMLController implements Initializable {
	@FXML
	private AnchorPane rootAddNewStudPane;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField masterSerialNum;
	@FXML
	private TextField emailAddress;
	@FXML
	private ComboBox<String> studentBranch;
	@FXML
	private ComboBox<String> studentCollege;
	@FXML
	private Button addStudentBtn;

	@FXML
	private Button cancelBtn;

	ObservableList<String> collegeName = FXCollections.observableArrayList("PCCoE", "PCCoER", "NMIT");
	ObservableList<String> branchName = FXCollections.observableArrayList("Computer", "IT", "ENTC", "Civil",
			"Mechanical");

	@FXML
	void addStudentDatabase(ActionEvent event) {

	}

	@FXML
	void goBackToAdminDash(ActionEvent event) throws Exception {
		ScreenPackClass.showAdminDashScreen(rootAddNewStudPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studentBranch.setItems(branchName);
		studentCollege.setItems(collegeName);
	}
}
