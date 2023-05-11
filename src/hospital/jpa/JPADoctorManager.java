package hospital.jpa;

import java.util.ArrayList;
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
	
	public List<Doctor> getDoctors(){
		List<Doctor> doctors;
		Query q = em.createNativeQuery("SELECT * FROM doctor ", Doctor.class);
		doctors = q.getResultList();
		return doctors;
	}

	@Override
	public List<Doctor> searchByName(String name, String surname) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE name = ? AND surname = ?", Doctor.class);
		q1.setParameter(1, name);
		q1.setParameter(2, surname);
		doctors = (List<Doctor>) q1.getResultList();
		return doctors;
	}
	
	

	@Override
	public void insertDoctor(Doctor doctor) {
		em.getTransaction().begin();
		em.merge(doctor);
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
		em.getTransaction().commit();
	}
	
	@Override
	public void assignIllness(Doctor d, Illness i) {
		em.getTransaction().begin();
		i.getDoctors().add(d);
		d.getTreatsIllness().add(i);
		em.getTransaction().commit();
	}
	
	@Override
	public Doctor getDoctor(int id) {
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE id = ?", Doctor.class);
		q1.setParameter(1, id);
		Doctor doctor = (Doctor) q1.getSingleResult();
		return doctor;
	}
	
	@Override
	public List<Doctor> docTreatsIllness(Illness i){
		Query q = em.createNativeQuery("SELECT d.id FROM doctor AS d JOIN doctorTreats AS dt ON d.id = dt.doctorId"+
	                                   " WHERE dt.illnessId = ?");
		q.setParameter(1, i.getId());
		List<Integer> docIds = (List<Integer>) q.getResultList();
		List<Doctor> docList = new ArrayList();
		Doctor d = new Doctor();
		JPADoctorManager dM = new JPADoctorManager();
		for (Integer id: docIds) {
			d = dM.getDoctor(id);
			docList.add(d);
		}
		return docList;
	}
	
	
	
}
