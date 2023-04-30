package hospital.db.ifaces;
import java.util.List;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Machine;

public interface HospitalManager {
	public void insertHospital(Hospital h);
	public void updateHospital(Hospital hospital);
	public List<Hospital> searchByName(String name);
	public Hospital getHospital(Integer hospitalId);
	public List<Hospital> getHospitals();
}
