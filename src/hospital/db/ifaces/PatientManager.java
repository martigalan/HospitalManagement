package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Patient;

public interface PatientManager {

	public void updatePatient(Patient patient);
	public List<Patient> searchByName(String name);
	public void insertPatient(Patient patient);
	public void assignHospital(String hospitalName);
	public void assignIllness(String illnessName);
	public Patient getPatient(Integer patientId);
}
