package hospital.db.ifaces;

import java.util.List;

import hospital.db.pojos.Illness;

public interface IllnessManager {
	public void insertIllness(Illness i);
	public Illness getIllness(int id);
	public List<Illness> getIllnesses();
	public List<Illness> relationDoctorIllness(int id);
}
