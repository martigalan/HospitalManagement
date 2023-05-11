package hospital.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.db.ifaces.SortingMedicManager;
import hospital.db.pojos.Doctor;

public class JPASortingMedicManager implements SortingMedicManager {
	
	EntityManager em;

	public JPASortingMedicManager() {
		em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate(); // activate the FK
		em.getTransaction().commit();
		
	}

	public JPASortingMedicManager(EntityManager em) {
		super();
		this.em = em;
	}

	public void close() {
		em.close();
	}
	
	@Override
	public boolean searchUser(String username, String password){
		boolean log = false;
		try {
			em.getTransaction().begin();	
			Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE username = ? AND password = ?", Doctor.class);
			q1.setParameter(1, username);
			q1.setParameter(2, password);
			Doctor doctor = (Doctor) q1.getSingleResult();	
			em.getTransaction().commit();
		}catch (Exception e){
			return log=true;
		} 
		return log;
	}
	

}
