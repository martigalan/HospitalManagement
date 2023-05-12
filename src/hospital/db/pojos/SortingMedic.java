package hospital.db.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "sortingMedic")
@XmlAccessorType(XmlAccessType.FIELD)
public class SortingMedic implements Serializable{

	private static final long serialVersionUID = 4995516544007640498L;
	@Id
	@GeneratedValue(generator = "sortingMedic")
	@TableGenerator(name = "sortingMedic", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "sortingMedic")
	private Integer id;
	@Column(unique = true, name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	public SortingMedic() {
		super();
	}

	public SortingMedic(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		SortingMedic other = (SortingMedic) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SortingMedic [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
		
}
