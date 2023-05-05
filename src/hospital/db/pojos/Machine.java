package hospital.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlElement;


@Entity
@Table(name = "machine")
public class Machine implements Serializable {

	private static final long serialVersionUID = -8134369376393308505L;
	
	@Id
	@GeneratedValue(generator="machine")
	@TableGenerator(name="machine", table="sqlite_sequence",
	   	pkColumnName="name", valueColumnName="seq",
	   	pkColumnValue="machine")
	private Integer id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "hospitalId")
	@XmlElement
	private Hospital hospital;
	
	@ManyToMany 
	@JoinTable(name = "treats",
	        joinColumns={@JoinColumn(name="machineId", referencedColumnName="id")},
	   		inverseJoinColumns={@JoinColumn(name="illnessId", referencedColumnName="id")})
	private List<Illness> treats;
	
	public Machine(String name, Hospital hospital) {
		super();
		this.name = name;
		this.hospital = hospital;
	}
	
	public Machine() {
		super();
	}
	
	public Machine(Integer id, String name, Hospital hospital) {
		super();
		this.id = id;
		this.name = name;
		this.hospital = hospital;
		this.treats = new ArrayList();
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
