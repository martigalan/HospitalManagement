package hospital.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class TreatsId implements Serializable{

	private static final long serialVersionUID = -6043747605285566598L;
	private Integer machineId;
	private Integer illnessId;
	
	@Override
	public int hashCode() {
		return Objects.hash(illnessId, machineId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreatsId other = (TreatsId) obj;
		return Objects.equals(illnessId, other.illnessId) && Objects.equals(machineId, other.machineId);
	}	

}
