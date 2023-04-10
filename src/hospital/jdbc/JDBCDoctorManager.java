package hospital.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.db.ifaces.DoctorManager;
import hospital.db.pojos.Doctor;

public class JDBCDoctorManager implements DoctorManager {
	
	Connection c;
	
	public JDBCDoctorManager() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/HospitalManagement.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		try {
			Statement s = c.createStatement();
			String table = "CREATE TABLE hospital (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " location TEXT NOT NULL)";
			s.executeUpdate(table);
			String table2 = "CREATE TABLE doctor (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " speciality TEXT NOT NULL," 
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table2);
			String table3 = "CREATE TABLE machine (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table3);
			String table4 = "CREATE TABLE vets (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " phone INTEGER," + " email TEXT NOT NULL," + " speciality TEXT)";
			s.executeUpdate(table4);
			String table5 = "CREATE TABLE illness (id INTEGER PRIMARY KEY AUTOINCREMENT," + " condition TEXT NOT NULL," 
					+ " doctorId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table5);
			String table6 = "CREATE TABLE patient (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " hospitalId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table6);
			// TODO table treats and has_illness
			s.close();
		} catch (SQLException e) {
			// Check if the exception is because the tables already exist
			if (e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateDoctor(String name, String surname) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Doctor> searchByName(String name) {
		List<Doctor> listDoctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctor WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				// Create a new Doctor
				String n = rs.getString("name");
				String sn = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String speciality = rs.getString("speciality");
				Double salary = rs.getDouble("salary");
				byte[] photo = rs.getBytes("photo");
				Doctor d = new Doctor(n, sn, dob, speciality, salary, photo);
				// Add the doctor to the list
				listDoctors.add(d);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertDoctor(Doctor doctor) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO doctor (name, surname, DoB, speciality, salary, photo) "
					+ "VALUES ('" + doctor.getName() + "', '" + doctor.getSurname() + "', '"
					+ doctor.getDob() + "', '" + doctor.getSpeciality() + "', '" 
					+ doctor.getSalary() + "', '" + doctor.getPhoto() + ")";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}

	}

	@Override
	public void assignHospital(String hospitalName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignIllness(String illnessName) {
		// TODO Auto-generated method stub

	}

}
