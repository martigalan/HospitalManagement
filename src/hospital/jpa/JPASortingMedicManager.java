package hospital.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospital.db.ifaces.SortingMedicManager;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.SortingMedic;

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
			Query q1 = em.createNativeQuery("SELECT * FROM sortingMedic WHERE username = ? AND password = ?", SortingMedic.class);
			q1.setParameter(1, username);
			q1.setParameter(2, password);
			SortingMedic sM = (SortingMedic) q1.getSingleResult();
		}catch (Exception e){
			System.out.println("      INCORRECT USERNAME / PASSWORD \n ");
			return log=true;
		} 
		return log;
	}
	

}
