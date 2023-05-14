package hospital.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.db.ifaces.HospitalManager;
import hospital.db.pojos.Hospital;

public class JPAHospitalManager implements HospitalManager {

	EntityManager em;
	
	public JPAHospitalManager() {
		em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}
	public JPAHospitalManager(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void insertHospital(Hospital h) {

	}

	@Override
	public void updateHospital(Hospital hospital) {

	}

	@Override
	public List<Hospital> searchByName(String name) {
		return null;
	}

	@Override
	public Hospital getHospital(Integer hospitalId) {
		return null;
	}

	@Override
	public List<Hospital> getHospitals() {
		return null;
	}

	@Override
	public Hospital search1ByName(String name) {
		Query q =em.createNativeQuery("SELECT * FROM hospital WHERE name LIKE ?", Hospital.class);
		q.setParameter(1, "%"+name+"%");
		Hospital h = (Hospital) q.getSingleResult();
		return h;
	}

}
