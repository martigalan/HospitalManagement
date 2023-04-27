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
	public void updateDoctor(Doctor doctor) throws TransactionRequiredException {
		em.getTransaction().begin();
		doctor.setSpeciality(doctor.getSpeciality());
		doctor.setSalary(doctor.getSalary());
		/*Query hospitalId = em.createNativeQuery("UPDATE doctor SET hospitalId = ?", Doctor.class);
		int hId = doctor.getHospital().getId();
		int updateHospitalId = hospitalId.setParameter(hId, hospitalId).executeUpdate();*/
		doctor.setHospitalId(doctor.getHospitalId());
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
		// Store the object
		em.remove(doctorDeleted);
		// End transaction
		em.getTransaction().commit();
	}

	@Override
	public void assignHospital(int hospitalId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignIllness(int illnessId, int doctorId) {
		em.getTransaction().begin();
		// TODO doctorTreats, para poder asignarle una illness
		//Deberia crearme el objeto de esa clase, obtener los valores, y llamarle
		//pidiendo los id con una query
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
