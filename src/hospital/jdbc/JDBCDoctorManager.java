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
import hospital.db.pojos.Hospital;

public class JDBCDoctorManager implements DoctorManager {
	
	private Connection c;

	public JDBCDoctorManager(Connection c) {
		this.c = c;
	}
	
	@Override
	public void updateDoctor(Doctor doctor) {
		try {
			String sql = "UPDATE doctor SET" + " speciality = ?, " + " salary = ?, " + " hospitalId = ? " + " WHERE id = ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setString(1, doctor.getSpeciality());
			p.setDouble(2, doctor.getSalary());
			p.setInt(3, doctor.getHospital().getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}

	@Override
	public List<Doctor> searchByName(String name, String surname) {
		List<Doctor> listDoctors = new ArrayList<Doctor>();
		JDBCHospitalManager hm = new JDBCHospitalManager(c);
		try {
			String sql = "SELECT * FROM doctor WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			p.setString(2, "%" + surname + "%");
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				// Create a new Doctor
				String n = rs.getString("name");
				String sn = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String speciality = rs.getString("speciality");
				Double salary = rs.getDouble("salary");
				Integer hospitalId = rs.getInt("hospitalId");
				Hospital hospital = hm.getHospital(hospitalId);
				Doctor d = new Doctor(n, sn, dob, speciality, salary, hospital);
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
					+ doctor.getSalary() + "', '" + doctor.getHospital().getId() + ")";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteDoctor(int id) {
		try {
			String sql = "DELETE FROM doctor WHERE id = ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setInt(1, id);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void assignHospital(String hospitalName) {
		// TODO Auto-generated method stub
		// aquí solo le metemos el hospitalId directamente al Doctor, no?

	}

	@Override
	public void assignIllness(int illnessId, int doctorId) {
		try {
			String sql = "INSERT INTO doctorTreats (illnessId, doctorId) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, illnessId);
			p.setInt(2, doctorId);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	@Override
	public Doctor getDoctor(int id) {
		JDBCHospitalManager hm = new JDBCHospitalManager(c);
		try {
			String sql = "SELECT * FROM doctor WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String speciality = rs.getString("speciality");
			Date dob = rs.getDate("dob");
			Double salary = rs.getDouble("salary");
			Integer hospitalId = rs.getInt("hospitalId");
			Hospital hospital = hm.getHospital(hospitalId);
			Doctor d = new Doctor(name, surname, dob, speciality, salary, hospital);
			rs.close();
			p.close();
			return d;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
}
