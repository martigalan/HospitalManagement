package hospital.jdbc;
import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCPatientManager implements PatientManager{

	private Connection c;

	public JDBCPatientManager(Connection c) {
		this.c = c;

	}
	
	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> searchByName(String name) {
		List<Patient> list = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patient WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				// Create a new Patient
				String n = rs.getString("name");
				String sn = rs.getString("surname");
				byte[] photo = rs.getBytes("photo");
				Integer h_id = rs.getInt("hospital_id");
				Date dob = rs.getDate("dob");
				//Patient patient = new Patient(n, sn, dob, h_id, photo);
				//TODO search for name, location of hospital in order to be able to create a Hospital for hospital id 
				//using hospital iface
				//list.add(patient);
			}
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertPatient(Patient patient) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO patient (name, surname, dob, hospital, photo) VALUES ('" + patient.getName() + "', "
					+ patient.getSurname() + ", '" 
					+ patient.getDob() + "', "
					+ patient.getHospital() + "')";
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
