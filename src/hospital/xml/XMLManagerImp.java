package hospital.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import hospital.db.ifaces.XMLManager;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Patient;

public class XMLManagerImp implements XMLManager {

	@Override
	//Needs a patient
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
	}
	
	@Override
	//Needs a patient
	public void hospital2Xml(Hospital h) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Hospital.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Hospital.xml");
			marshaller.marshal(h, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Patient xml2Patient(File xml) {
		Patient patient = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/External-Report.xml");
			patient = (Patient) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public void patient2Html() {
		xml2Html("./xmls/External-Report.xml", "./xmls/Report-Style.xslt", "./xmls/External-Report.html");
		// TODO method to create xml file
	}
	
	@Override
	public Hospital xml2Hospital(File xml) {
		Hospital hospital = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/External-Report.xml");
			hospital = (Hospital) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospital;
	}

	@Override
	public void hospital2Html() {
		xml2Html("./xmls/External-Report.xml", "./xmls/Report-Style.xslt", "./xmls/External-Report.html");
		// TODO method to create xml file
	}
	
	@Override
	public void xml2Html(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
