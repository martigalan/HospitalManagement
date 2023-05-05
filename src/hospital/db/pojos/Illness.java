package hospital.db.pojos;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "illness")
public class Illness implements Serializable {

	private static final long serialVersionUID = -7606688736830380305L;
	
	@Id
	@GeneratedValue(generator="illness")
	@TableGenerator(name="illness", table="sqlite_sequence",
	   	pkColumnName="name", valueColumnName="seq",
	   	pkColumnValue="illness")
	private Integer id;
	private String condition;
	
	@OneToMany(mappedBy = "illness")
	private List<Has> patients;
	
	@ManyToMany(mappedBy = "treatsIllness")
	private List<Doctor> doctors;
	
	@ManyToMany(mappedBy = "treats")
	private List<Machine> machines;
	
	public Illness() {
		super();
	}

	public Illness(String condition) {
		super();
		this.condition = condition;
		//this.machines = new ArrayList();
		//this.dosctors = new ArrayList();
		//this.machines = new ArrayList();
	}
	
	public Illness(Integer id, String condition) {
		super();
		this.id = id;
		this.condition = condition;
		//this.machines = new ArrayList();
		//this.dosctors = new ArrayList();
		//this.machines = new ArrayList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<Has> getPatients() {
		return patients;
	}

	public void setPatients(List<Has> patients) {
		this.patients = patients;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
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
		Illness other = (Illness) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Illness [id=" + id + ", condition=" + condition + "]";
	}
	
	
}
