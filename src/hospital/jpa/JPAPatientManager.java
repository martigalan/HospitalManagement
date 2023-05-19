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
		em.setFlushMode(FlushModeType.COMMIT);

	}

	public void close() {
		em.close();
	}
	
	@Override
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
		h.getPatients().add(p);
		em.getTransaction().commit();
	}

	@Override
	public void assignIllness(Patient p, Illness i, String severity) {
		JPAHas hM = new JPAHas();
		Has has= hM.getHas(p.getId(), i.getId());
		if (has != null) {
			em.getTransaction().begin();
			has.setSeverity(severity);
			em.getTransaction().commit();
		}
		else {
			em.getTransaction().begin();
			em.refresh(p);
			em.merge(i);
			em.flush();
			em.getTransaction().commit();
			em.getTransaction().begin();
		    has = new Has();
		    has.setIllnessId(i.getId());
		    has.setPatientId(p.getId());
		    has.setSeverity(severity);
		    has.setIllness(i);
		    List l1 = p.getIllness();
		    l1.add(has);
			List l2 = i.getPatients();
		    l2.add(has);
		    has.setPatient(p);
			em.persist(has);
		    em.getTransaction().commit();
		}
	}

	@Override
	public Patient getPatient(Integer patientId) {
		Patient patient;
		Query q = em.createNativeQuery("SELECT * FROM patient WHERE id = ?",Patient.class);
		q.setParameter(1, patientId);
		patient = (Patient) q.getSingleResult();
		return patient;
	}

}
