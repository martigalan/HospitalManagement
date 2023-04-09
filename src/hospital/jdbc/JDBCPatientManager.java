package hospital.jdbc;
import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JDBCPatientManager implements PatientManager{

	Connection c;
	public JDBCPatientManager() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/HospitalManagement.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePatient(String name, String surname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPatient() {
		// TODO Auto-generated method stub
		
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
