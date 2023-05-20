package hospital.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import hospital.db.ifaces.XMLManager;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Illness;
import hospital.db.pojos.Machine;
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
	}
	
	@Override
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
	public void doctor2Xml(Doctor d) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Doctor.xml");
			marshaller.marshal(d, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void illness2Xml(Illness i) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Illness.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Illness.xml");
			marshaller.marshal(i, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void machine2Xml(Machine m) {
		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Machine.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Machine.xml");
			marshaller.marshal(m, file);
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
	public Doctor xml2Doctor(File xml) {
		Doctor doctor = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/External-Report.xml");
			doctor = (Doctor) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}
	
	@Override
	public Illness xml2Illness(File xml) {
		Illness illness = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Illness.class);
			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/External-Report.xml");
			illness = (Illness) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return illness;
	}
	
	@Override
	public Machine xml2Machine(File xml) {
		Machine machine = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Machine.class);
			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/External-Report.xml");
			machine = (Machine) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return machine;
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
