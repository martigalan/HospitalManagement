package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

public class Illness implements Serializable {

	private static final long serialVersionUID = -7606688736830380305L;
	private Integer id;
	private String condition;
	
	public Illness(String condition, Doctor doctor) {
		super();
		this.condition = condition;
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
