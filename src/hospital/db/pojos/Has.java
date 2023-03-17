package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

public class Has implements Serializable{
	
	private static final long serialVersionUID = 18985703331804986L;
	
	private Patient patient;
	private Illness illness;
	private String severity;
	
	public Has(Patient patient, Illness illness, String severity) {
		super();
		this.patient = patient;
		this.illness = illness;
		this.severity = severity;
	}

	
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}



	@Override
	public int hashCode() {
		return Objects.hash(illness, patient, severity);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Has other = (Has) obj;
		return Objects.equals(illness, other.illness) && Objects.equals(patient, other.patient)
				&& Objects.equals(severity, other.severity);
	}



	@Override
	public String toString() {
		return "Has [patient=" + patient + ", illness=" + illness + ", severity=" + severity + "]";
	}
	
	
	
	
}
