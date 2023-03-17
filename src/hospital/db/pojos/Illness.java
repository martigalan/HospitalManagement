package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

public class Illness implements Serializable {

	private static final long serialVersionUID = -7606688736830380305L;
	private Integer id;
	private String condition;
	private Doctor doctor;
	
	public Illness(String condition, Doctor doctor) {
		super();
		this.condition = condition;
		this.doctor = doctor;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
		return "Illness [id=" + id + ", condition=" + condition + ", doctor=" + doctor + "]";
	}
	
	
}
