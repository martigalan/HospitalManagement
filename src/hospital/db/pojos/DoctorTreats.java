package hospital.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class DoctorTreats implements Serializable{

	private static final long serialVersionUID = 8223755379942826288L;
	private Illness illness;
	private Doctor doctor;
	
	public DoctorTreats(Illness illness, Doctor doctor) {
		super();
		this.illness = illness;
		this.doctor = doctor;
	}

	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctor, illness);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoctorTreats other = (DoctorTreats) obj;
		return Objects.equals(doctor, other.doctor) && Objects.equals(illness, other.illness);
	}
	
	
	
}
