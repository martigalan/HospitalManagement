package hospital.db.ifaces;

import java.util.List;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Machine;

public interface MachineManager {
	public List<Machine> searchByName(String name);
	public void assignHospital (Hospital hospital);
	public void insertMachine(Machine machine);
}
