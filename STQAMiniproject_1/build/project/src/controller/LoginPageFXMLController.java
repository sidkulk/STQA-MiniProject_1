package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

public class LoginPageFXMLController {
	@FXML
	private AnchorPane loginRootPane;
	@FXML
	private VBox adminRootPane;
	@FXML
	private TextField adminIDTxtField;
	@FXML
	private TextField adminPassTextField;
	@FXML
	private Button AdminloginBtn;
	@FXML
	private VBox studentRootPane;
	@FXML
	private TextField studIDTextField;
	@FXML
	private TextField studentPassTextField;
	@FXML
	private Button studLoginBtn;

	
	@FXML
	public void adminLoginRoutine(ActionEvent event) {
		System.out.println("Admin Login Method Called");
	}
	
	@FXML
	public void studLoginRoutine(ActionEvent event) {
		System.out.println("Student Login Method Called");
	}
}
