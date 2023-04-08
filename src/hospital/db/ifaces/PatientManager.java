package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Patient;

public interface PatientManager {

	public void updatePatient(String name, String surname);
	public List<Patient> searchByName(String name);
	public void insertPatient();
	public void assignHospital(String hospitalName);
	public void assignIllness(String illnessName);
}
