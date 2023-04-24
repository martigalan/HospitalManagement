package hospital.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class HasId implements Serializable{

	private Integer patientId;
	private Integer illnessId;
	@Override
	public int hashCode() {
		return Objects.hash(illnessId, patientId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HasId other = (HasId) obj;
		return Objects.equals(illnessId, other.illnessId) && Objects.equals(patientId, other.patientId);
	}
	
	
}
