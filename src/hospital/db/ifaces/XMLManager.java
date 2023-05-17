package hospital.db.ifaces;

import java.io.File;

import hospital.db.pojos.Patient;
import hospital.db.pojos.Hospital;


public interface XMLManager {

	public void patient2Xml(Patient p);
	public Patient xml2Patient(File xml);
	public void patient2Html();
	public void xml2Html(String sourcePath, String xsltPath,String resultDir);
	public Hospital xml2Hospital(File xml);
	public void hospital2Html();
	public void hospital2Xml(Hospital h);
}
