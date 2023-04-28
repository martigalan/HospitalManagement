package hospital.jpa;

import java.util.List;

import javax.persistence.*;
import hospital.db.ifaces.DoctorManager;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Illness;

public class JPADoctorManager implements DoctorManager {

	EntityManager em;

	public JPADoctorManager() {
		em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate(); // activate the FK
		em.getTransaction().commit();
	}

	public void close() {
		em.close();
	}

	@Override
	public void updateDoctor(Doctor doctor) throws TransactionRequiredException {
		em.getTransaction().begin();
		doctor.setName(doctor.getName());
		doctor.setSurname(doctor.getSurname());
		em.getTransaction().commit();
	}

	@Override
	public List<Doctor> searchByName(String name, String surname) {
		List<Doctor> doctors;
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE name = ? AND surname = ?", Doctor.class);
		q1.setParameter(1, name);
		q1.setParameter(2, surname);
		doctors = (List<Doctor>) q1.getResultList();
		// Esto está bien? o debo separarlo en 2 queries
		return doctors;
	}

	@Override
	public void insertDoctor(Doctor doctor) {
		em.getTransaction().begin();
		em.persist(doctor);
		em.getTransaction().commit();
	}

	@Override
	public void deleteDoctor(Doctor doctor) {
		Query q2 = em.createNativeQuery("SELECT * FROM doctor WHERE id = ?", Doctor.class);
		q2.setParameter(1, doctor.getId());
		Doctor doctorDeleted = (Doctor) q2.getSingleResult();

		// Begin transaction
		em.getTransaction().begin();
		// Remove the object
		em.remove(doctorDeleted);
		// End transaction
		em.getTransaction().commit();
	}

	@Override
	public void assignHospital(Hospital h) {
		JPADoctorManager dM = new JPADoctorManager();
		em.getTransaction().begin();
		h.getDoctors().add(dM.getDoctor(h.getId()));
		// TODO dudo mucho de que esto esté bien, ask
		em.getTransaction().commit();
	}

	@Override
	public void assignIllness(Doctor d, Illness i) {
		em.getTransaction().begin();
		i.getDoctors().add(d);
		d.getTreatsIllness().add(i);
		// TODO ask if this is correct
		em.getTransaction().commit();
	}

	@Override
	public Doctor getDoctor(int id) {
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE id = ?", Doctor.class);
		q1.setParameter(1, id);
		Doctor doctor = (Doctor) q1.getSingleResult();
		return doctor;
	}

}
