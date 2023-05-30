package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "treats")
@IdClass(TreatsId.class)
public class Treats implements Serializable{

	private static final long serialVersionUID = 3510089229536778364L;
	@XmlTransient
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "machineId", referencedColumnName = "id")
	private Machine machine;
	@Id
	private Integer machineId;
	
	@XmlTransient
	@ManyToOne(cascade = CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "illnessId", referencedColumnName = "id")
	private Illness illness;
	@Id
	private Integer illnessId;
	
	@XmlTransient
	@Column(name = "successRate")
	private String success_rate;
	
	public Treats(Machine machine, Illness illness, String success_rate) {
		super();
		this.machine = machine;
		this.illness = illness;
		this.success_rate = success_rate;
	}

	public Treats() {
		super();
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public String getSuccess_rate() {
		return success_rate;
	}

	public void setSuccess_rate(String success_rate) {
		this.success_rate = success_rate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(illness, machine, success_rate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Treats other = (Treats) obj;
		return Objects.equals(illness, other.illness) && Objects.equals(machine, other.machine)
				&& Objects.equals(success_rate, other.success_rate);
	}

	@Override
	public String toString() {
		return "\n machine: " + machine + "\n illness: " + illness + " success rate: " + success_rate + "\n";
	}
	
	
}
