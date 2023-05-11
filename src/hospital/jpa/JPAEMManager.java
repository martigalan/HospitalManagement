package hospital.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAEMManager {

	private EntityManager em;

	public JPAEMManager() {
		em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	public EntityManager getEm() {
		return em;
	}
	
	
}
