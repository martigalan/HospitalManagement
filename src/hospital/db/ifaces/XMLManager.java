package hospital.db.ifaces;

import java.io.File;

import hospital.db.pojos.Patient;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Illness;
import hospital.db.pojos.Machine;


public interface XMLManager {

	public void patient2Xml(Patient p);
	public void hospital2Xml(Hospital h);
	public void doctor2Xml(Doctor d);
	public void illness2Xml(Illness i);
	public void machine2Xml(Machine m);
	public Patient xml2Patient(File xml);
	public Hospital xml2Hospital(File xml);
	public Doctor xml2Doctor(File xml);
	public Illness xml2Illness(File xml);
	public Machine xml2Machine(File xml);
	public void xml2Html(String sourcePath, String xsltPath,String resultDir);
		
}
