package hospital.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import hospital.db.ifaces.SortingMedicManager;

public class JPASortingMedicManager implements SortingMedicManager {
	
	EntityManager em;

	public JPASortingMedicManager() {
		em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate(); // activate the FK
		em.getTransaction().commit();
		
	}

	public void close() {
		em.close();
	}
	
	@Override
	public Boolean searchUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
