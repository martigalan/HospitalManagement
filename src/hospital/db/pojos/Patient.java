package hospital.db.pojos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = 2424327075260915600L;
	private Integer id;
	private String name;
	private String surname;
	private Date dob;
	private Hospital hospital;
	private byte[] photo;
	private Integer hospitalId;
	
	public Patient(String name, String surname, Date dob, byte[] photo) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.photo = photo;
	}

	public Patient(String name, String surname, Date dob, Hospital hospital, byte[] photo) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.hospital = hospital;
		this.photo = photo;
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
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", hospital="
				+ hospital + ", photo=" + Arrays.toString(photo) + "]";
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	

}
