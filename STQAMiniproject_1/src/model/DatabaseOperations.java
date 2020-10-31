package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alertBoxPack.AlertBoxClass;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DatabaseOperations {
	static boolean res = false;

	public static boolean addDriveToDatabase(String cname, String cdate, String xthMin, String xIIthMin, String BEMin,
			String deadBack, String liveBack, String branch, String ctc) {
		res = false;
		try {
			String raw = "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String query = String.format(raw, Main.Constants.DRIVE_TABLE_NAME, DriveDataAccessClass.Constants.COMP_NAME,
					DriveDataAccessClass.Constants.COMP_DATE, DriveDataAccessClass.Constants.COMP_X_MIN,
					DriveDataAccessClass.Constants.COMP_XII_MIN, DriveDataAccessClass.Constants.COMP_BE_MIN,
					DriveDataAccessClass.Constants.COMP_MAX_DEAD_BACK,
					DriveDataAccessClass.Constants.COMP_MAX_LIVE_BACK, DriveDataAccessClass.Constants.COMP_BRANCH,
					DriveDataAccessClass.Constants.COMP_CTC);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cname);
			ps.setString(2, cdate);
			ps.setString(3, xthMin);
			ps.setString(4, xIIthMin);
			ps.setString(5, BEMin);
			ps.setString(6, deadBack);
			ps.setString(7, liveBack);
			ps.setString(8, branch);
			ps.setString(9, ctc);
			int i = ps.executeUpdate();

			if (i > 0)
				res = true;
			else
				res = false;
			ps.close();
			conn.close();
		} catch (Exception e) {
			AlertBoxClass.ErrBox("ERROR", "Falied to add Drive to Database!");
			e.printStackTrace();
		}
		return res;
	}

	public static boolean addStudentToDatabase(Integer msn, String fname, String lname, String email, String branch,
			String college, String studPass) throws ClassNotFoundException, SQLException {
		try {
			String raw = "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)";
			String query = String.format(raw, Main.Constants.STUDENT_TABLE_NAME,
					StudentDataAccessClass.Constants.STUD_MSN, StudentDataAccessClass.Constants.STUD_FNAME,
					StudentDataAccessClass.Constants.STUD_LNAME, StudentDataAccessClass.Constants.STUD_EMAIL,
					StudentDataAccessClass.Constants.STUD_BRANCH, StudentDataAccessClass.Constants.STUD_COLLEGE,
					StudentDataAccessClass.Constants.STUD_PASS);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, msn);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, email);
			ps.setString(5, branch);
			ps.setString(6, college);
			ps.setString(7, studPass);
			int i = ps.executeUpdate();
			if (i > 0)
				res = true;
			else
				res = false;
			ps.close();
			conn.close();
		} catch (Exception e) {
			AlertBoxClass.ErrBox("ERROR", "Master Serial Number Conflict OR Email conflict!");
			System.out.println("Failed to insert data!");
		}
		return res;
	}

	public static ObservableList<Drive> getCompanyDetails() {
		ObservableList<Drive> list = FXCollections.observableArrayList();
		try {
			String raw = "SELECT * FROM %s";
			String query = String.format(raw, Main.Constants.DRIVE_TABLE_NAME);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Drive(rs.getInt(DriveDataAccessClass.Constants.COMP_ID),
						rs.getString(DriveDataAccessClass.Constants.COMP_NAME),
						rs.getString(DriveDataAccessClass.Constants.COMP_DATE),
						rs.getString(DriveDataAccessClass.Constants.COMP_X_MIN),
						rs.getString(DriveDataAccessClass.Constants.COMP_XII_MIN),
						rs.getString(DriveDataAccessClass.Constants.COMP_BE_MIN),
						rs.getString(DriveDataAccessClass.Constants.COMP_MAX_LIVE_BACK),
						rs.getString(DriveDataAccessClass.Constants.COMP_MAX_DEAD_BACK),
						rs.getString(DriveDataAccessClass.Constants.COMP_BRANCH),
						rs.getString(DriveDataAccessClass.Constants.COMP_CTC)));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ObservableList<Student> getStudentDetails() {
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			String raw = "SELECT * FROM %s";
			String query = String.format(raw, Main.Constants.STUDENT_TABLE_NAME);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Student(rs.getInt(StudentDataAccessClass.Constants.STUD_MSN),
						rs.getString(StudentDataAccessClass.Constants.STUD_FNAME),
						rs.getString(StudentDataAccessClass.Constants.STUD_LNAME),
						rs.getString(StudentDataAccessClass.Constants.STUD_EMAIL),
						rs.getString(StudentDataAccessClass.Constants.STUD_BRANCH),
						rs.getString(StudentDataAccessClass.Constants.STUD_COLLEGE),
						rs.getString(StudentDataAccessClass.Constants.STUD_PASS)));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ObservableList<Drive> getYourDriveDetails(Integer msn) {
		ObservableList<Drive> list = FXCollections.observableArrayList();
		try {
			String raw = "SELECT %s, %s, %s FROM %s INNER JOIN %s WHERE %s.%s = %s.%s AND %s.%s = ?";
			String query = String.format(raw, DriveDataAccessClass.Constants.COMP_NAME,
					DriveDataAccessClass.Constants.COMP_DATE, DriveDataAccessClass.Constants.COMP_CTC,
					Main.Constants.DRIVE_TABLE_NAME, Main.Constants.STUD_DRIVE_APPLY_TAB,
					Main.Constants.DRIVE_TABLE_NAME, DriveDataAccessClass.Constants.COMP_ID,
					Main.Constants.STUD_DRIVE_APPLY_TAB, StudentDriveDataAccessClass.Constants.DRIVE_ID,
					Main.Constants.STUD_DRIVE_APPLY_TAB, StudentDriveDataAccessClass.Constants.STUD_ID);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, msn);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Drive(rs.getString(DriveDataAccessClass.Constants.COMP_NAME),
						rs.getString(DriveDataAccessClass.Constants.COMP_DATE),
						rs.getString(DriveDataAccessClass.Constants.COMP_CTC)));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean removeSelectedDrive(int DriveID, TableView<Drive> tabview) {
		try {
			String raw = "DELETE FROM %s WHERE %s = ?";
			String query = String.format(raw, Main.Constants.DRIVE_TABLE_NAME, DriveDataAccessClass.Constants.COMP_ID);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, DriveID);
			int i = ps.executeUpdate();
			if (i > 0) {
				res = true;
				tabview.getItems().removeAll(tabview.getSelectionModel().getSelectedItem());
			} else {
				res = false;
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean removeSelectedStudent(Integer msn, TableView<Student> tabview) {
		try {
			String raw = "DELETE FROM %s WHERE %s = ?";
			String query = String.format(raw, Main.Constants.STUDENT_TABLE_NAME, StudentDataAccessClass.Constants.STUD_MSN);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, msn);
			int i = ps.executeUpdate();
			if (i > 0) {
				res = true;
				tabview.getItems().removeAll(tabview.getSelectionModel().getSelectedItem());
			} else {
				res = false;
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static boolean applyForDrive(int driveid, int studid, String dname) {
		try {
			String raw = "INSERT INTO %s (%s, %s) VALUES (?, ?)";
			String query = String.format(raw, Main.Constants.STUD_DRIVE_APPLY_TAB,
					StudentDriveDataAccessClass.Constants.DRIVE_ID, StudentDriveDataAccessClass.Constants.STUD_ID);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, driveid);
			ps.setInt(2, studid);
			int i = ps.executeUpdate();

			if (i > 0) {
				res = true;
				AlertBoxClass.Notify("SUCCESS", "Applied for " + dname + " drive");
			} else
				res = false;
			ps.close();
			conn.close();
		} catch (Exception e) {
			AlertBoxClass.Amber("ALERT", "Already applied to the drive!");
			e.printStackTrace();
		}
		return res;
	}

	public static boolean checkLoginCred(String uname, String pwd) {
		try {
			String raw = "SELECT * FROM %s WHERE %s = ? and %s = ?";
			String query = String.format(raw, Main.Constants.ADMIN_TABLE_NAME,
					AdminDataAccessClass.Constants.ADMIN_UNAME, AdminDataAccessClass.Constants.ADMIN_PASSWD);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
			} else {
				res = false;
			}
			ps.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static boolean checkLoginCred(Integer msn, String pwd) {
		try {
			String raw = "SELECT * FROM %s WHERE %s = ? and %s = ?";
			String query = String.format(raw, Main.Constants.STUDENT_TABLE_NAME,
					StudentDataAccessClass.Constants.STUD_MSN, StudentDataAccessClass.Constants.STUD_PASS);
			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, msn);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
			} else {
				res = false;
			}
			ps.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static String getFname(Integer msn) {
		String fname = "NAN";
		try {
			String raw = "SELECT %s FROM %s WHERE %s = ?";
			String query = String.format(raw, StudentDataAccessClass.Constants.STUD_FNAME,
					Main.Constants.STUDENT_TABLE_NAME, StudentDataAccessClass.Constants.STUD_MSN);

			String ConnURL = Main.Constants.CONNECTION_URL;
			Class.forName(Main.Constants.CLASS_FOR_NAME);
			Connection conn = DriverManager.getConnection(ConnURL);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, msn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fname = rs.getString(StudentDataAccessClass.Constants.STUD_FNAME);
			} else {
				fname = "NAN";
			}
			ps.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fname;
	}
}
