package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Illness;

public interface DoctorManager {

		public void updateDoctor(Doctor doctor);
		public List<Doctor> searchByName(String name, String surname);
		public void insertDoctor(Doctor doctor);
		public void deleteDoctor(Doctor doctor);
		public void assignHospital(Hospital h);
		public void assignIllness(Doctor d, Illness i);
		public Doctor getDoctor(int id);
}
