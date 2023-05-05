package hospital.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name = "hospital")
public class Hospital implements Serializable{

	private static final long serialVersionUID = 6590616788083928968L;
	
	@Id
	@GeneratedValue(generator="hospital")
	@TableGenerator(name="hospital", table="sqlite_sequence",
	   	pkColumnName="name", valueColumnName="seq",
	   	pkColumnValue="hospital")
	private Integer id;
	private String name;
	private String location;
	private static final Integer AV = 10;

	@OneToMany(mappedBy = "hospital")
	private List<Patient> patients;	
	@OneToMany(mappedBy= "hospital")
	private List<Doctor> doctors;
	@OneToMany (mappedBy="hospital")
	private List<Machine> machines;
	
	public List<Machine> getMachines() {
		return machines;
	}
	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}
	public Hospital() { //default hospital
		super();
		this.patients = new ArrayList<Patient>();
		this.doctors = new ArrayList<Doctor>();
		this.machines = new ArrayList<Machine>();
		this.id=0;
		this.name = "main";
		this.location = "main";
	}
	public Hospital(String name, String location) {
		super();
		this.patients = new ArrayList<Patient>();
		this.doctors = new ArrayList<Doctor>();
		this.machines = new ArrayList<Machine>();
		this.name = name;
		this.location = location;
	}

	public Hospital(int id, String name, String location) {
		super();
		this.patients = new ArrayList<Patient>();
		this.doctors = new ArrayList<Doctor>();
		this.machines = new ArrayList<Machine>();
		this.id = id;
		this.name = name;
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
		
	}
	
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
		
	}
	public Integer getAv() {
		return AV;
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
		Hospital other = (Hospital) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
	
	
}
