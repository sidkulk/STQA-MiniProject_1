package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import screenPack.ScreenPackClass;
import testCredpack.TestCredClass;

public class LoginPageFXMLController {
	@FXML
	private AnchorPane loginRootPane;
	@FXML
	private VBox adminRootPane;
	@FXML
	private TextField adminIDTxtField;
	@FXML
	private PasswordField adminPassTextField;
	@FXML
	private Button AdminloginBtn;
	@FXML
	private VBox studentRootPane;
	@FXML
	private TextField studIDTextField;
	@FXML
	private PasswordField studentPassTextField;
	@FXML
	private Button studLoginBtn;

	@FXML
	public void adminLoginRoutine(ActionEvent event) throws Exception {
		if (adminIDTxtField.getText().isEmpty() && adminPassTextField.getText().isEmpty()) {
			System.out.println("Empty Fields!");
		} else {
			if (adminIDTxtField.getText().equals(TestCredClass.username)
					&& adminPassTextField.getText().equals(TestCredClass.passwd)) {
				ScreenPackClass.showAdminLoginScreen(loginRootPane);
			} else {
				System.out.println("Incorrect Credentials!");
			}
		}
	}

	@FXML
	public void studLoginRoutine(ActionEvent event) {
		System.out.println("Student Login Method Called");
	}
}
