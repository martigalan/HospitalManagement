package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

public class Machine implements Serializable {

	private static final long serialVersionUID = -8134369376393308505L;
	private Integer id;
	private String name;
	private Hospital hospital;
	
	public Machine(String name, Hospital hospital) {
		super();
		this.name = name;
		this.hospital = hospital;
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

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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
		Machine other = (Machine) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", name=" + name + ", hospital=" + hospital + "]";
	}
	
	
	
}
