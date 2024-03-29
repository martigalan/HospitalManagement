package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "hasIllness")
@IdClass(HasId.class)
public class Has implements Serializable{
	
	private static final long serialVersionUID = 18985703331804986L;
	
	@Id
	private Integer patientId;
	@Id
	private Integer illnessId;
	@Column(name = "severity")
	private String severity;
	
	public Has() {
		super();
	}



	public Integer getPatientId() {
		return patientId;
	}



	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}



	public Integer getIllnessId() {
		return illnessId;
	}



	public void setIllnessId(Integer illnessId) {
		this.illnessId = illnessId;
	}



	@ManyToOne(cascade = CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "patientId", referencedColumnName = "id")
	private Patient patient;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "illnessId", referencedColumnName = "id")
	private Illness illness;
	
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
		return Objects.hash(illness, patient);
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
		return Objects.equals(illness, other.illness) && Objects.equals(patient, other.patient);
	}

	@Override
	public String toString() {
		return " \n illness: " + illness + ", severity: " + severity;
	}
	
}
