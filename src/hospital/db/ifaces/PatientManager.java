package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.*;

public interface PatientManager {

	public void update(Patient patient);
	public List<Patient> searchByName(String name);
	public void insertPatient(Patient patient);
	public void assignHospital(Hospital h, Patient p);
	public void assignIllness(Patient p, Illness i, String severity);
	public Patient getPatient(Integer patientId);
}
