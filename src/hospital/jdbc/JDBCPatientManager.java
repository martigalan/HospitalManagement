package hospital.jdbc;
import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Patient;

import java.sql.Connection;
import java.util.List;

public class JDBCPatientManager implements PatientManager{

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
