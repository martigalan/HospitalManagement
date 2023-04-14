package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Doctor;

public interface DoctorManager {

		public void updateDoctor(Doctor doctor);
		public List<Doctor> searchByName(String name);
		public void insertDoctor(Doctor doctor);
		public void deleteDoctor(int id);
		public void assignHospital(String hospitalName);
		public void assignIllness(String illnessName);
}
