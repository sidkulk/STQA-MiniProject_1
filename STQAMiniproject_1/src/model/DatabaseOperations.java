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
		} catch (Exception e) {
			AlertBoxClass.ErrBox("ERROR", "Falied to add Drive to Database!");
			e.printStackTrace();
		}
		return res;
	}

	public static boolean addStudentToDatabase(Integer msn, String fname, String lname, String email, String branch,
			String college) throws ClassNotFoundException, SQLException {
		try {
			String raw = "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)";
			String query = String.format(raw, Main.Constants.STUDENT_TABLE_NAME,
					StudentDataAccessClass.Constants.STUD_MSN, StudentDataAccessClass.Constants.STUD_FNAME,
					StudentDataAccessClass.Constants.STUD_LNAME, StudentDataAccessClass.Constants.STUD_EMAIL,
					StudentDataAccessClass.Constants.STUD_BRANCH, StudentDataAccessClass.Constants.STUD_COLLEGE);
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
			int i = ps.executeUpdate();
			if (i > 0)
				res = true;
			else
				res = false;
			ps.close();
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
						rs.getString(StudentDataAccessClass.Constants.STUD_COLLEGE)));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
