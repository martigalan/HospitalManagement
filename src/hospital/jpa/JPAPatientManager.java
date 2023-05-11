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
	}

	public JPAPatientManager(EntityManager em) {
		super();
		this.em = em;
	}

	public void close() {
		em.close();
	}
	
	@Override
	// TODO change to update()
	public void update(Patient patient) {
		em.getTransaction().begin();
		em.flush();
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
	public void assignHospital(Hospital h, Patient p) {
		em.getTransaction().begin();
		p.setHospital(h);
		em.getTransaction().commit();
	}

	@Override
	public void assignIllness(Patient p, Illness i, String severity) {
		JPAHas hM = new JPAHas();
		Has h= hM.getHas(p.getId(), i.getId());
		em.getTransaction().begin();
		em.flush();
		if (h != null) {
			h.setSeverity(severity);
		}
		else {
		    Has has = new Has();
		    has.setIllness(i);
		    has.setIllnessId(i.getId());
		    has.setPatient(p);
		    has.setPatientId(p.getId());
		    has.setSeverity(severity);
		    p.getIllness().add(has);
		    i.getPatients().add(has);
		    em.persist(has);
		}
		em.getTransaction().commit();
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
