package hospital.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import hospital.db.ifaces.IllnessManager;
import hospital.jdbc.ConnectionManager;
import hospital.jdbc.JDBCIllnessManager;
import hospital.xml.SQLDateAdapter;

@Entity
@Table(name = "doctor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = { "dob", "speciality", "salary" }) //order of the elements
public class Doctor implements Serializable {

	private static final long serialVersionUID = 6814981867922225263L;
	
	@Id
	@GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor", table="sqlite_sequence",
	   	pkColumnName="name", valueColumnName="seq",
	   	pkColumnValue="doctor")
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String surname;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlElement
	private String speciality;
	@XmlElement
	private Integer salary;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "hospitalId")
	@XmlTransient
	private Hospital hospital;
	
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "DoctorTreats",
	        joinColumns={@JoinColumn(name="doctorId", referencedColumnName="id")},
	   		inverseJoinColumns={@JoinColumn(name="illnessId", referencedColumnName="id")})
	@XmlTransient
	private List<Illness> treatsIllness;

	public Doctor(String name, String surname, Date dob, String speciality, Integer salary, Hospital hospital) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.speciality = speciality;
		this.salary = salary;
		this.hospital = hospital;
		this.treatsIllness = new ArrayList();
	}
	
	public Doctor(Integer id, String name, String surname, Date dob, String speciality, Integer salary, Hospital hospital) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.speciality = speciality;
		this.salary = salary;
		this.hospital = hospital;
		this.treatsIllness = new ArrayList();
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

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Illness> getTreatsIllness() {
		IllnessManager iM;
		ConnectionManager c =new ConnectionManager();
		iM = new JDBCIllnessManager(c.getConnection());
		treatsIllness = iM.relationDoctorIllness(this.getId());
		return treatsIllness;
	}

	public void setTreatsIllness(List<Illness> treatsIllness) {
		this.treatsIllness = treatsIllness;
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
		return "\n DOCTOR INFO. \n id: " + id + "\n name: " + name + "\n surname: " + surname + "\n dob: " + dob + "\n speciality: "
		+ speciality + "\n salary: " + salary + " \n illness that " + name + " treats: " + this.getTreatsIllness() + " \n\n HOSPITAL INFO. "
		+ hospital + "\n";
	}
	
	public String shortInfo() {
		return "\n id: " + id + "\n name: " + name + "\n surname: " + surname + "\n";
	}

}
