package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;

public interface DoctorManager {

		public void updateDoctor(Doctor doctor);
		public List<Doctor> searchByName(String name, String surname);
		public void insertDoctor(Doctor doctor);
		public void deleteDoctor(int id);
		public void assignHospital(String hospitalName);
		public void assignIllness(int illnessId, int doctorId);
		public Doctor getDoctor(int id);
}
