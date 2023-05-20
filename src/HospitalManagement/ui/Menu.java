package HospitalManagement.ui;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.persistence.NoResultException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.sql.Blob;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import hospital.db.ifaces.*;
import hospital.db.pojos.*;
import hospital.jdbc.*;
import hospital.jpa.*;
import hospital.xml.XMLManagerImp;
import hospital.db.graphics.ImageWindow;

public class Menu {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static PatientManager patientM;
	private static DoctorManager doctorM;
	private static HospitalManager hospitalM;
	private static HospitalManager hospitalMJPA;
	private static ConnectionManager connectionManager;
	private static IllnessManager illnessM;
	private static MachineManager machineM;
	private static SortingMedicManager sortingMedicM;
	private static hasManager hasM;
	private static boolean showImage = true;
	private static Scanner sc = new Scanner(System.in);
	private static XMLManagerImp xmlMI = new XMLManagerImp();
	private static boolean control;
	
	public static boolean logIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Type the user name:");
		String username = sc.nextLine();
		System.out.println("Type the password:");
		String password = sc.nextLine();
		boolean d = sortingMedicM.searchUser(username, password);
		return d;

	}

	public static void main(String[] args) {
		try {
			connectionManager = new ConnectionManager();
			JPAEMManager emMan = new JPAEMManager();
			hospitalM = new JDBCHospitalManager(connectionManager.getConnection());
			illnessM = new JDBCIllnessManager(connectionManager.getConnection());
			machineM = new JDBCMachineManager(connectionManager.getConnection());
			patientM = new JPAPatientManager(emMan.getEm());
			doctorM = new JPADoctorManager(emMan.getEm());
			hasM = new JPAHas(emMan.getEm());
			hospitalMJPA = new JPAHospitalManager(emMan.getEm());
			sortingMedicM = new JPASortingMedicManager(emMan.getEm());

			boolean log = true;
			while (log) {
				log = logIn();
			}

			boolean control = true;
			while (control) {
				System.out.println("Choose an option, please:");
				System.out.println("-1. Register a new Patient");
				System.out.println("-2. Select a patient data");
				System.out.println("-3. Select doctor data");
				System.out.println("-4. Show hospitals");
				System.out.println("-5. Create XMLs");
				System.out.println("-0. Exit");

				Scanner sc = new Scanner(System.in);
				Integer choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
					case 1: {
						registerPatient();
						break;
					}
					case 2: {
						selectPatient();
						break;
					}
					case 3: {
						selectDoctor();
						break;
					}
	
					case 4: {
						showHospitals();
						break;
					}
					case 5: {
						xmlMenu();
					}
					case 0: {
						control = false;
						break;
					}
					default:
						System.out.println("  NOT AN OPTION \n");
						break;
					}
			}

		} catch (NumberFormatException e) {
			System.out.println("  NOT A NUMBER. Closing application... \n");
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

	private static void xmlMenu() {
		while (true) {
			try {
				System.out.println("What do you want to turn into an XML? :");
				System.out.println("1. Hospital");
				System.out.println("2. Illness");
				System.out.println("3. Machine");
				System.out.println("0. Back to  principal menu");
				
				Scanner sc = new Scanner(System.in);
				int choice = Integer.parseInt(sc.nextLine()); 

				switch (choice) {
					case 1: {
						createHospitalXML();
						System.out.println("Do you want to create and HTML? Y/N");
						String answer = sc.nextLine();
						if (answer.equals("Y")) {
							xmlMI.xml2Html("./xmls/Hospital.xml", "./xmls/Hospital-Style.xslt", "./xmls/Hospital.html");
							break;
						}
						else {
							break;
						}
					}
					case 2: {
						createIllnessXML();
						System.out.println("Do you want to create and HTML? Y/N");
						String answer = sc.nextLine();
						if (answer.equals("Y")) {
							xmlMI.xml2Html("./xmls/Illness.xml", "./xmls/Illness-Style.xslt", "./xmls/Illness.html");
							break;
						}
						else {
							break;
						}
					}
					case 3: {
						createMachineXML();
						System.out.println("Do you want to create and HTML? Y/N");
						String answer = sc.nextLine();
						if (answer.equals("Y")) {
							xmlMI.xml2Html("./xmls/Machine.xml", "./xmls/Machine-Style.xslt", "./xmls/Machine.html");
							break;
						}
						else {
							break;
						}
					}
					case 0: {
						return;
					}
				}

			} catch (NumberFormatException e) {
				System.out.println("Please select a number");
				e.printStackTrace();
				return;}
		}
		
	}

	private static void createMachineXML() {
		System.out.println("Which machine do you want to create an XML out of? \n");
		List<Machine> listM = machineM.getMachines();
		System.out.println(listM);
		System.out.println("\n Choose an id: ");
		Integer mId = Integer.parseInt(sc.nextLine());
		Machine m = machineM.getMachine(mId);
		while(m == null) {
			System.out.println("\n Choose an id: ");
			mId = Integer.parseInt(sc.nextLine());
			m = machineM.getMachine(mId);
		}
		xmlMI.machine2Xml(m);
	}

	private static void createIllnessXML() {
		System.out.println("Which illness do you want to create an XML out of? \n");
		List<Illness> listI = illnessM.getIllnesses();
		System.out.println(listI);
		System.out.println("\n Choose an id: ");
		Integer iId = Integer.parseInt(sc.nextLine());
		Illness i = illnessM.getIllness(iId);
		while(i == null) {
			System.out.println("\n Choose an id: ");
			iId = Integer.parseInt(sc.nextLine());
			i = illnessM.getIllness(iId);
		}
		xmlMI.illness2Xml(i);
	}

	private static void showHospitals() {
		List<Hospital> allHospitals = hospitalM.getHospitals();
		for (Hospital h : allHospitals) {
			System.out.println(h);
		}

	}
	
	public static void createHospitalXML() {
		System.out.println("Which hospital do you want to create an XML out of? \n");
		List<Hospital> listH = hospitalM.getHospitals();
		System.out.println(listH);
		System.out.println("\n Choose an id: ");
		Integer hId = Integer.parseInt(sc.nextLine());
		Hospital h = hospitalM.getHospital(hId);
		List<Machine> m = machineM.machinesInHospital(h);
		h.setMachines(m);
		while(h == null) {
			System.out.println("\n Choose an id: ");
			hId = Integer.parseInt(sc.nextLine());
			h = hospitalM.getHospital(hId);
		}
		xmlMI.hospital2Xml(h);
	}

	public static void registerPatient() throws IOException {
		System.out.println("Type the patient data");
		System.out.println("Name:");
		String name = sc.nextLine();
		System.out.println("Surname:");
		String surname = sc.nextLine();
		Date dobDate = null;
		try {
			System.out.println("Date of birth (yyyy-MM-dd):");
			String dob = sc.nextLine();
			LocalDate dobLocalDate = LocalDate.parse(dob); // java.time.LocalDate
			dobDate = Date.valueOf(dobLocalDate);
		} catch (DateTimeParseException d) {
			System.out.println("Put the date of birth in the correct format: (yyyy-MM-dd)");
			String dob = sc.nextLine();
			LocalDate dobLocalDate = LocalDate.parse(dob); // java.time.LocalDate
			dobDate = Date.valueOf(dobLocalDate);
		}
		System.out.println("Photo:");

		byte[] photo;
		try {
			System.out.print("Type the file name as it appears in folder /photos, including extension: ");
			String fileName = sc.nextLine();
			File photos = new File("./photos/" + fileName);
			InputStream streamBlob = new FileInputStream(photos);
			photo = new byte[streamBlob.available()];
			streamBlob.read(photo);
			streamBlob.close();
		}catch(FileNotFoundException ex) {
			System.out.println("   Sorry, the file wasn't found. NO PHOTO HAS BEEN ASSIGNED\n");
			photo = null;
		}

		Hospital mainHospital = hospitalMJPA.search1ByName("Fundacion Jimenez Diaz");

		Patient p = new Patient(name, surname, dobDate, mainHospital, photo);
		patientM.insertPatient(p);

		lookForIllness(p.getId());

	}

	public static void selectDoctor() throws IOException {
		try {
		System.out.println("Type the name:");
		String nameDoc = sc.nextLine();
		System.out.println("Type the surname:");
		String snDoc = sc.nextLine();
		List<Doctor> doctorList = doctorM.searchByName(nameDoc, snDoc);
		for (Doctor d : doctorList) {
			System.out.println(d.shortInfo());
		}
		if (doctorList.isEmpty()) {
			System.out.println("There is no one with that name.");
		} else {
			System.out.println("Please choose a doctor, type the Id:");
			Integer id = Integer.parseInt(sc.nextLine());
			Doctor d = doctorM.getDoctor(id);
			// Go to the Doctor's menu
			DoctorMenu(id);
		}
		}catch(NoResultException ex) {
			System.out.println("  NO DOCTOR WITH THAT ID\n");
		}
	}

	public static void selectPatient() throws IOException {
		try {
		System.out.println("Type the name:");
		String name = sc.nextLine();
		List<Patient> patientlist = patientM.searchByName(name);
		Iterator it = patientlist.iterator();
		while (it.hasNext()) {
			System.out.println(((Patient) it.next()).shortInfo());
		}
		if (patientlist.isEmpty()) {
			System.out.println("There is no one with that name.");
		} else {
			System.out.println("Please choose a patient, type the Id:");
			Integer id = Integer.parseInt(sc.nextLine());
			Patient p = patientM.getPatient(id);
			// Go to the Patient's menu
			PatientMenu(id);
		}
		}catch(NoResultException ex) {
			System.out.println("  NO PATIENT WITH THAT ID\n");
		}

	}

	public static void PatientMenu(int id) {
		while (true) {
			try {
				System.out.println("What do you want to do with the patient?:");
				System.out.println("1. Update data");
				System.out.println("2. Show data");
				System.out.println("3. Search hospital");
				System.out.println("4. Assign illness");
				System.out.println("5. Update state of an illness");
				System.out.println("6. Create patient XML");
				System.out.println("0. Back to  principal menu");
				
				Scanner sc = new Scanner(System.in);
				int choice = Integer.parseInt(sc.nextLine()); 

				switch (choice) {
					case 1: {
						updatePatient(id);
						System.out.println("Do you want to update an illness severity? Y/N");
						String answer = sc.nextLine();
						if (answer.equals("Y")) {
							updateIllnessSeverity(id);
							break;
						}
						else {
							break;
						}
					}
					case 2: {
						showPatient(id); 
						break;
					}
					case 3: {
						searchHospital(id);
						break;
					}
					case 4: {
						lookForIllness(id);
						break;
					}
					case 5: {
						updateIllnessSeverity(id);
						break;
					}
					case 6: {
						Patient p = patientM.getPatient(id);
						xmlMI.patient2Xml(p);
						System.out.println("Do you want to create and HTML? Y/N");
						String answer = sc.nextLine();
						if (answer.equals("Y")) {
							xmlMI.xml2Html("./xmls/Patient.xml", "./xmls/Patient-Style.xslt", "./xmls/Patient.html");
							break;
						}
						else {
							break;
						}
					}
					case 0: {
						return;
					}
				}

			} catch (NumberFormatException e) {
				System.out.println("Please select a number");
				e.printStackTrace();
				PatientMenu(id);
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}
	}

	private static void updateIllnessSeverity(Integer id) {
		// look for Has that has the p and the i needed and only setSeverity()
		Patient p = patientM.getPatient(id);
		System.out.println("This patient has the following illnesses: ");
		List<Has> illnessesPHas = hasM.getListHas(id);
		for (Has hIllness : illnessesPHas) {
			System.out.println(hIllness.toString());
		}
		System.out.println("Enter the illness that need to be updated: ");
		Integer illnessId = Integer.parseInt(sc.nextLine());
		Has hasIllness = hasM.getHas(id, illnessId);
		System.out.println("Enter the new severity: ");
		String sev = sc.nextLine();
		hasIllness.setSeverity(sev);
		String severity = hasIllness.getSeverity();
		patientM.update(p);
	}

	private static void lookForIllness(Integer id) {
		// this method shows a list of illnesses and the patient chooses
		List<Illness> possibleIllnesses = illnessM.getIllnesses();
		Patient p = patientM.getPatient(id);
		System.out.println("Please, choose the ID of the illness the patient has: ");
		for (Illness i : possibleIllnesses) {
			System.out.println(i);
		}
		Scanner sc = new Scanner(System.in);
		Integer iId = Integer.parseInt(sc.nextLine());
		Illness illness = illnessM.getIllness(iId);
		if (illness == null) {
			PatientMenu(id);
		}
		System.out.println("Please, enter the severity of the illness: ");
		String severity = sc.nextLine();
		patientM.assignIllness(p, illness, severity);
	}

	private static void searchHospital(int id) {
		Patient p = patientM.getPatient(id);
		List<Has> illnesses = p.getIllness();
		for (Has hI : illnesses) {
			System.out.println(hI.toString());
		}
		System.out.println("What illness do you want to treat?\n Please enter illness id: ");
		int illnessId = sc.nextInt();
		Illness illnessTreated = illnessM.getIllness(illnessId);
		// now i have the illness that needs treatment
		List<Doctor> docList = doctorM.docTreatsIllness(illnessTreated);
		// docList is a list of ALL the doctors that treat the specified illness
		List<Machine> machineList = machineM.machineTreatsIllness(illnessTreated);
		// machineList is a list of ALL the machines that treat the specified illness
		// right now i have a list of doctors and a list of machines, now i have to
		// compare hospitals they're at to select one
		// I'm going to go through the machineList seeing if there's a doctor that works
		// at that hospital, if there's a doctor AND the hospital
		// is available then i choose that hospital
		for (Machine m : machineList) {
			Hospital hMachine = m.getHospital();
			for (Doctor d : docList) {
				Hospital hDoctor = d.getHospital();
				Hospital realh = hospitalMJPA.search1ByName(hMachine.getName());
				if ((hMachine.equals(hDoctor)) & (hMachine.getPatients().size() <= hMachine.getAv())) {
					patientM.assignHospital(realh, p);
					break;
				}
				System.out.println("Sorry, we can't attend you at this moment.");
			}
		}
		System.out.println("You have been assigned to : " + p.getHospital());
	}

	public static void updatePatient(int id) throws IOException {
		Patient p = patientM.getPatient(id);
		System.out.println("Type the new data, or press enter to keep actual data");
		System.out.println("Name (" + p.getName() + "):");
		String name = sc.nextLine();
		if (!name.equals(" ")) {
			p.setName(name);
		}
		System.out.println("\nSurname (" + p.getSurname() + "):");
		String surname = sc.nextLine();
		if (!surname.equals(" ")) {
			p.setSurname(surname);
		}

		System.out.print("Type the file name as it appears in folder /photos, including extension: ");
		String fileName = sc.nextLine();
		if (!fileName.equals("")) {
			File photos = new File("./photos/" + fileName);
			InputStream streamBlob = new FileInputStream(photos);
			byte[] photo = new byte[streamBlob.available()];
			streamBlob.read(photo);
			streamBlob.close();

			p.setPhoto(photo);
		}
	}

	public static void showPatient(int id) throws IOException {
		Patient p = patientM.getPatient(id);
		System.out.println(p); 
		// If patient.getPhoto != null
		// open window and show it
		if (p.getPhoto() != null) {
			ByteArrayInputStream blobIn = new ByteArrayInputStream(p.getPhoto());
			// Show the photo
			if (showImage) {
				ImageWindow window = new ImageWindow();
				window.showBlob(blobIn);
			}
			// Write the photo in a file
			else {
				File outFile = new File("./photos/Output.png");
				OutputStream blobOut = new FileOutputStream(outFile);
				byte[] buffer = new byte[blobIn.available()];
				blobIn.read(buffer);
				blobOut.write(buffer);
				blobOut.close();
			}
		}
	}

	public static void DoctorMenu(int id) {
		while (true) {
			try {

				System.out.println("What do you want to do with the doctor?:");
				System.out.println("1. Update data");
				System.out.println("2. Show data");
				System.out.println("3. Delete doctor");
				System.out.println("4. Create doctor XML");
				System.out.println("0. Back to  principal menu");
				
				Scanner sc = new Scanner(System.in);
				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
					case 1: {
						updateDoctor(id);
						break;
					}
					case 2: {
						showDoctor(id); 
						break;
					}
					case 3: {
						removeDoctor(id);
						return;
					}
					case 4: {
						Doctor d = doctorM.getDoctor(id);
						xmlMI.doctor2Xml(d);
						System.out.println("Do you want to create and HTML? Y/N");
						String answer = sc.nextLine();
						if (answer.equals("Y")) {
							xmlMI.xml2Html("./xmls/Doctor.xml", "./xmls/Doctor-Style.xslt", "./xmls/Doctor.html");
							break;
						}
						else {
							break;
						}
					}
					case 0: {
						return;
					}
				}

			} catch (NumberFormatException e) {
				System.out.println("Please select a number");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}
	}

	public static void updateDoctor(int id) throws IOException {
		Doctor p = doctorM.getDoctor(id);
		System.out.println("Type the new data, or press enter to keep actual data");
		System.out.println("Name (" + p.getName() + "):");
		String name = sc.nextLine();
		if (!name.equals("")) {
			p.setName(name);
		}
		System.out.println("Surname (" + p.getSurname() + "):");
		String surname = sc.nextLine();
		if (!surname.equals("")) {
			p.setSurname(surname);
		}
		doctorM.updateDoctor(p);
	}

	public static void showDoctor(int id) throws IOException {
		Doctor p = doctorM.getDoctor(id);
		System.out.println(p);

	}

	public static void removeDoctor(int id) throws IOException {
		Doctor d = doctorM.getDoctor(id);
		doctorM.deleteDoctor(d);
		System.out.println("The doctor has been removed. :(");
	}
}