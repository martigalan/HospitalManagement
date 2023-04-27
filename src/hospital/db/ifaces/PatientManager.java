package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.*;

public interface PatientManager {

	public void updatePatient(Patient patient);
	public List<Patient> searchByName(String name);
	public void insertPatient(Patient patient);
	public void assignHospital(Hospital h);
	public void assignIllness(Patient p, Illness i, String severity);
	public Patient getPatient(int patientId);
}
