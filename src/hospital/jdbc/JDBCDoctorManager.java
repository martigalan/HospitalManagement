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
	
	private Connection c;

	public JDBCDoctorManager(Connection c) {
		this.c = c;

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
		return listDoctors;
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
