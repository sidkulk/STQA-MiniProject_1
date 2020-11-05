package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.DatabaseOperations;
import model.Drive;
import model.DriveDataAccessClass;
import model.Student;
import model.StudentDataAccessClass;
import screenPack.ScreenPackClass;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import alertBoxPack.AlertBoxClass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AdminDashBoardFXMLController implements Initializable {
	@FXML
	private TabPane rootAdminDashPane;

	@FXML
	private AnchorPane campusDrivePane;

	@FXML
	private Button AddNewDriveBtn;

	@FXML
	private Button RemoveSelectedDriveBtn;

	@FXML
	private TableColumn<Drive, Integer> driveID;

	@FXML
	private TableColumn<Drive, String> compName;

	@FXML
	private TableColumn<Drive, String> driveDate;

	@FXML
	private TableColumn<Drive, String> driveCTC;

	@FXML
	private TableColumn<Drive, String> driveBranch;

	@FXML
	private TableColumn<Drive, String> xthPerc;

	@FXML
	private TableColumn<Drive, String> xiiPerc;

	@FXML
	private TableColumn<Drive, String> beAvgPerc;

	@FXML
	private TableColumn<Drive, String> maxDeadbacks;

	@FXML
	private TableColumn<Drive, String> maxLiveBacks;

	@FXML
	private TableView<Drive> driveTabView;

	@FXML
	private TableView<Student> studTabView;

	@FXML
	private TableColumn<Student, Integer> stuMSN;

	@FXML
	private TableColumn<Student, String> stuFname;

	@FXML
	private TableColumn<Student, String> stuLname;

	@FXML
	private TableColumn<Student, String> stuEmail;

	@FXML
	private TableColumn<Student, String> stuBranch;

	@FXML
	private TableColumn<Student, String> stuClg;

	@FXML
	private TableColumn<Student, String> studLogPass;

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
	private Button getDetailsBtn;

	ObservableList<Drive> driveList;
	ObservableList<Student> stuList;
	public static List<String> compList = new ArrayList<>();

	// public static Integer msn;
	@FXML
	public void addNewDrive(ActionEvent event) throws Exception {
		ScreenPackClass.showAddNewDriveScreen(campusDrivePane);
	}

	@FXML
	public void removeSelDrive(ActionEvent event) {
		Drive drive = driveTabView.getSelectionModel().getSelectedItem();
		boolean driveRemoved = DatabaseOperations.removeSelectedDrive(drive.getDID(), driveTabView);
		if (driveRemoved) {
			AlertBoxClass.Notify("SUCCESS", "Drive removed from database!");
		} else {
			AlertBoxClass.ErrBox("ERROR", "An error occured on our end! Contact your software vendor!");
		}
	}

	@FXML
	public void addNewStud(ActionEvent event) throws Exception {
		ScreenPackClass.showAddNewStudentScreen(studInfoPane);
	}

	@FXML
	public void removeSelectedStud(ActionEvent event) {
		Student stud = studTabView.getSelectionModel().getSelectedItem();
		boolean studRemoved = DatabaseOperations.removeSelectedStudent(stud.getMSN(), studTabView);
		if (studRemoved) {
			AlertBoxClass.Notify("SUCCESS", stud.getFname() + " removed from database!");
		} else {
			AlertBoxClass.ErrBox("ERROR", "An error occured on our end! Contact your software vendor!");
		}
	}

	@FXML
	void logOutAction(ActionEvent event) throws Exception {
		AlertBoxClass.logOutConfirmation(campusDrivePane);
	}

	@FXML
	void logOutActionTwo(ActionEvent event) throws Exception {
		logOutAction(event);
	}

	@FXML
	void getDetailsOfSelectedStudent(ActionEvent event) throws Exception {
		Student stud = studTabView.getSelectionModel().getSelectedItem();
		DatabaseOperations.getStudDetails(stud.getMSN());
		compList = DatabaseOperations.getStudCompNames(stud.getMSN());
		ScreenPackClass.showGetStudDetailsScreen(studInfoPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		driveID.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_ID));
		compName.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_NAME));
		driveDate.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_DATE));
		driveCTC.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_CTC));
		driveBranch.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_BRANCH));
		xthPerc.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_X_MIN));
		xiiPerc.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_XII_MIN));
		beAvgPerc.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_BE_MIN));
		maxDeadbacks.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_MAX_DEAD_BACK));
		maxLiveBacks.setCellValueFactory(new PropertyValueFactory<>(DriveDataAccessClass.Constants.COMP_MAX_LIVE_BACK));
		driveList = DatabaseOperations.getCompanyDetails();
		driveTabView.setItems(driveList);

		stuMSN.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_MSN));
		stuFname.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_FNAME));
		stuLname.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_LNAME));
		stuEmail.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_EMAIL));
		stuBranch.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_BRANCH));
		stuClg.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_COLLEGE));
		studLogPass.setCellValueFactory(new PropertyValueFactory<>(StudentDataAccessClass.Constants.STUD_PASS));
		stuList = DatabaseOperations.getStudentDetails();
		studTabView.setItems(stuList);
	}
}
