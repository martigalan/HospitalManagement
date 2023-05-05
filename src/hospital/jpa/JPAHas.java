package hospital.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.db.ifaces.hasManager;
import hospital.db.pojos.Has;
import hospital.db.pojos.Patient;

public class JPAHas implements hasManager{

EntityManager em;
	
	public JPAHas() {
		em  = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	public void close() {
		em.close();
	}
	
	@Override
	public Has getHas(int pId, int iId){
		try {
			Has h = null;
			em.getTransaction().begin();
			Query q = em.createNativeQuery("SELECT * FROM hasIllness WHERE patientId = ? AND illnessId = ?", Has.class);
			q.setParameter(1, pId);
			q.setParameter(2, iId);
			h = (Has) q.getSingleResult();
			return h;
		}catch(NoResultException e) {
			return null;
		}

	}
	
	@Override
	public List<Has> getHas(int pId){
		List<Has> h = null;
		em.getTransaction().begin();
		Query q = em.createNativeQuery("SELECT * FROM hasIllness WHERE patientId = ? AND illnessId = ?", Has.class);
		q.setParameter(1, pId);
		h = (List<Has>) q.getResultList();
		return h;
	}
}
