package hospital.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;


public class Doctor implements Serializable {


	private static final long serialVersionUID = 6814981867922225263L;
	
	private Integer Doctor;
	private String name;
	private String surname;
	private Date dob;
	private String speciality;
	private Double salary;
	private byte[] photo;


	

	public Doctor(String name, String surname, Date dob, String speciality, Double salary, byte[] photo) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.speciality = speciality;
		this.salary = salary;
		this.photo = photo;
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
				+ speciality + ", salary=" + salary + ", photo=" + Arrays.toString(photo) + "]";
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




	public byte[] getPhoto() {
		return photo;
	}




	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	

	
	
}