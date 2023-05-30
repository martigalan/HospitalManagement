package hospital.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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

import hospital.db.ifaces.hasManager;
import hospital.jpa.JPAEMManager;
import hospital.jpa.JPAHas;
import hospital.xml.SQLDateAdapter;

@Entity
@Table(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient")
@XmlType(propOrder = { "dob" }) //order of the elements
public class Patient implements Serializable{
	
	private static final long serialVersionUID = 2424327075260915600L;
	
	@Id
	@GeneratedValue(generator="patient")
	@TableGenerator(name="patient", table="sqlite_sequence",
	   	pkColumnName="name", valueColumnName="seq",
	   	pkColumnValue="patient")
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String surname;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "hospitalId")
	@XmlTransient
	private Hospital hospital;
	@XmlTransient
	private byte[] photo;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient", cascade = CascadeType.REMOVE)
	@XmlTransient
	private List<Has> illness;
	
	
	public Patient() {
		super();
		this.illness = new ArrayList<Has>();
	}

	public Patient(String name, String surname, Date dob, byte[] photo) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.photo = photo;
		this.illness = new ArrayList<Has>();

	}
	
	public Patient(Integer id, String name, String surname, Date dob, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.photo = photo;
		this.illness = new ArrayList<Has>();

	}

	public Patient(String name, String surname, Date dob, Hospital hospital, byte[] photo) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.hospital = hospital;
		this.photo = photo;
		this.illness = new ArrayList<Has>();

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
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital_id(Hospital hospital) {
		this.hospital = hospital;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Has> getIllness() { //TODO correct this to show in toString
		
		return illness;
	}

	public void setIllness(List<Has> illness) {
		this.illness = illness;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "\n id: " + id + "\n name: " + name + "\n surname: " + surname + "\n dob: " + dob + "\n HOSPITAL INFO. "
				+ hospital + " TREATED FOR: " + this.getIllness() + " \n ";
	}
	
	public String shortInfo() {
		return "\n id: " + id + "\n name: " + name + "\n surname: " + surname + "\n";
	}
}
