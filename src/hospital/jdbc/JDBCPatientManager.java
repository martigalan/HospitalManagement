package hospital.jdbc;
import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Illness;
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
		// TODO Doing this w/ JPA
		
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
				Date dob = rs.getDate("dob");
				Integer hospitalId = rs.getInt("hospitalId");
				byte[] photo = rs.getBytes("photo");
				//Patient patient = new Patient(n, sn, dob, h_id, photo);
				//Hospital hospital = getHospital(hospitalId) //TODO in JDBCHospitalManager-----ELENA'S DOING IT
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
			String sql = "INSERT INTO patient (name, surname, dob, hospitalId, photo) " +
		           "VALUES (?, ?, ?, ?, ?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, patient.getName());
			st.setString(2, patient.getSurname());
			st.setDate(3, patient.getDob());
			st.setInt(4, patient.getHospital().getId());
			st.setBytes(5, patient.getPhoto());
			st.executeUpdate();
			st.close();
		}catch(SQLException e) {
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

	@Override
	public Patient getPatient(Integer patientId) {
		Patient patient;
		try {
			String sql = "SELECT * FROM patient WHERE id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, patientId);
			ResultSet rs = st.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Date dob = rs.getDate("dob");
			Integer hospitalId = rs.getInt("hospitalId");
			//TODO use getHospital(hospitalId) to create a Hospital--- ELENA'S DOING IT
			byte[] photo = rs.getBytes("photo");
			
			//patient = new Patient(name, surname, dob, hospital, photo);
			
		}catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		return patient;
	}

}
