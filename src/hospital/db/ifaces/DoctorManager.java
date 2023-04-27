package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Doctor;

public interface DoctorManager {

		public void updateDoctor(Doctor doctor);
		public List<Doctor> searchByName(String name, String surname);
		public void insertDoctor(Doctor doctor);
		public void deleteDoctor(Doctor doctor);
		public void assignHospital(int hospitalId);
		public void assignIllness(int illnessId, int doctorId);
		public Doctor getDoctor(int id);
}
