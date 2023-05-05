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
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "dob")
	private Date dob;
	@Column(name = "speciality")
	private String speciality;
	@Column(name = "salary")
	private Double salary;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;
	
	@ManyToMany 
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
	
	public Doctor(Integer id, String name, String surname, Date dob, String speciality, Double salary, Hospital hospital) {
		super();
		this.id = id;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public List<Illness> getTreatsIllness() {
		return treatsIllness;
	}

	public void setTreatsIllness(List<Illness> treatsIllness) {
		this.treatsIllness = treatsIllness;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", speciality="
				+ speciality + ", salary=" + salary + ", hospital=" + hospital
				+ ", treatsIllness=" + treatsIllness + "]";
	}
	
	public String shortInfo() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

}
