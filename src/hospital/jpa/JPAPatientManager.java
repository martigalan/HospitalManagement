package hospital.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Patient;

public class JPAPatientManager implements PatientManager {

	EntityManager em;
	
	public JPAPatientManager() {
		em  = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		//TODO finish this part
		/*if (this.getPatient.getHospital().isNull()) {
			Hospital h = new Hospital();
			this.getPatient().createHospital();
		}*/
	}

	public void close() {
		em.close();
	}
	
	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Patient> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPatient(Patient patient) {
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();

	}

	@Override
	public void assignHospital(String hospitalName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignIllness(int pId, int iId, String severity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Patient getPatient(Integer patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
