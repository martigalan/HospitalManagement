package hospital.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import hospital.db.ifaces.XMLManager;
import hospital.db.pojos.Patient;

public class XMLManagerImp implements XMLManager {

	@Override
	public void patient2Xml(Patient p) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Patient.xml");
			marshaller.marshal(p, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public Patient xml2Patient(File xml) {
		// TODO Auto-generated method stub
		
		

		return null;
	}

	@Override
	public void patient2Html(Patient p) {
		// TODO Auto-generated method stub
		

	}

}
