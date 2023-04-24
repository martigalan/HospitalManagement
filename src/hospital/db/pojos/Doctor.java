package hospital.db.pojos;

import java.io.Serializable;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

	private static final long serialVersionUID = 6814981867922225263L;
	
	@Id
	@GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor", table="sqlite_sequence",
	   	pkColumnName="name", valueColumnName="seq",
	   	pkColumnValue="doctor")
	private Integer Doctor;
	private String name;
	private String surname;
	private Date dob;
	private String speciality;
	private Double salary;
	private Hospital hospital;
	
	@ManyToMany //no entiendo lo de fetch
	@JoinTable(name = "DoctorTreats",
	        joinColumns={@JoinColumn(name="doctorId", referencedColumnName="id")},
	   		inverseJoinColumns={@JoinColumn(name="illnessId", referencedColumnName="id")})
	private List<Illness> treatsIllness;

	public Doctor(String name, String surname, Date dob, String speciality, Double salary, Hospital hospital) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.speciality = speciality;
		this.salary = salary;
		this.hospital = hospital;
	}

	public Doctor() {
		super();
	}

	public Integer getDoctor() {
		return Doctor;
	}

	public void setDoctor(Integer doctor) {
		Doctor = doctor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
		
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Doctor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(Doctor, other.Doctor);
	}

	@Override
	public String toString() {
		return "Doctor [Doctor=" + Doctor + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", speciality="
				+ speciality + ", salary=" + salary + ", hospital=" + hospital + "]";
	}		
}
