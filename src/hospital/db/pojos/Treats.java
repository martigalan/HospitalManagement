package hospital.db.pojos;
import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Treats implements Serializable{

	private static final long serialVersionUID = 3510089229536778364L;
	@XmlTransient
	private Machine machine;
	@XmlTransient
	private Illness illness;
	@XmlTransient
	private String success_rate;
	
	public Treats(Machine machine, Illness illness, String success_rate) {
		super();
		this.machine = machine;
		this.illness = illness;
		this.success_rate = success_rate;
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
