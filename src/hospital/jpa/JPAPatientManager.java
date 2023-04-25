package hospital.jpa;

import java.util.List;

import javax.persistence.*;

import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.*;

public class JPAPatientManager implements PatientManager {

	EntityManager em;
	
	public JPAPatientManager() {
		em  = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		/*if (this.getPatient.getHospital().isNull()) {
			Hospital h = new Hospital();
			this.getPatient().createHospital();
		}*/
		//TODO ASK ABOUT THIS!
	}

	public void close() {
		em.close();
	}
	
	@Override
	public void updatePatient(Patient patient) {
		em.getTransaction().begin();
		patient.setName(patient.getName());
		patient.setSurname(patient.getSurname());
		em.getTransaction().commit();
	}

	@Override
	public List<Patient> searchByName(String name) {
		List<Patient> patients;
		Query q = em.createNativeQuery("SELECT * FROM patient WHERE name = ?", Patient.class);
		q.setParameter(1, name);
		patients = (List<Patient>) q.getResultList();
		return patients;
	}

	@Override
	public void insertPatient(Patient patient) {
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
	}

	@Override
	public void assignHospital(String hospitalName) {
		// TODO check factors

	}

	@Override
	public void assignIllness(Patient p, Illness i, String severity) {
		JPAHas hM = new JPAHas();
		Has h= hM.getHas(p.getId(), i.getId());
		if (h != null) {
			em.getTransaction().begin();
			h.setSeverity(severity);
			em.getTransaction().commit();
		}
		else {
		    em.getTransaction().begin();
		    Has has = new Has();
		    em.persist(has);
		    has.setIllness(i);
		    has.setPatient(p);
		    has.setSeverity(severity);
		    p.getIllness().add(has);
		    i.getPatients().add(has);
		    em.getTransaction().commit();
		}
	}

	@Override
	public Patient getPatient(int patientId) {
		Patient patient;
		Query q = em.createNativeQuery("SELECT * FROM patient WHERE id = ?",Patient.class);
		q.setParameter(1, patientId);
		patient = (Patient) q.getSingleResult();
		return patient;
	}

}
