package hospital.db.ifaces;

import java.io.File;

import hospital.db.pojos.Patient;


public interface XMLManager {

	public void patient2Xml(Patient p);
	public Patient xml2Patient(File xml);
	public void patient2Html(Patient p);
}
