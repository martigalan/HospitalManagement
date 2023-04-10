package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Doctor;

public interface DoctorManager {

		public void updateDoctor(String name, String surname);
		public List<Doctor> searchByName(String name);
		public void insertDoctor(Doctor doctor);
		public void assignHospital(String hospitalName);
		public void assignIllness(String illnessName);
}
