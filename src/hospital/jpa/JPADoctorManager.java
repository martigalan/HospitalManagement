package hospital.jpa;

import java.util.List;

import javax.persistence.*;
import hospital.db.ifaces.DoctorManager;
import hospital.db.pojos.Doctor;

public class JPADoctorManager implements DoctorManager {
	
	EntityManager em;
	
	public JPADoctorManager() {
		EntityManager em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate(); //activate the FK
		em.getTransaction().commit();
	}
	
	public void close() {
		em.close();
	}
	
	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Doctor> searchByName(String name, String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertDoctor(Doctor doctor) {
		em.getTransaction().begin();
		em.persist(doctor);
		em.getTransaction().commit();
	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignHospital(String hospitalName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignIllness(int illnessId, int doctorId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
