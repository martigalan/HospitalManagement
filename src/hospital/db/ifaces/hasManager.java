package hospital.db.ifaces;

import java.util.List;

import hospital.db.pojos.Has;

public interface hasManager {

	public List<Has> getHas(int pId);
	Has getHas(int pId, int iId);
}
