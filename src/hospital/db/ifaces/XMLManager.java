package hospital.db.ifaces;

import java.io.File;

import hospital.db.pojos.Patient;


public interface XMLManager {

	public void owner2Xml(Patient p);
	public Patient xml2Owner(File xml);
	public void owner2Html(Patient p);
}
