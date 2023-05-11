package HospitalManagement.ui;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
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

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
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

	private static Scanner sc;
	private static XMLManagerImp xmlMI = new XMLManagerImp();
	
	public static boolean logIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the user name:");
		String username = sc.nextLine();
		System.out.println("Input the password:");
		String password = sc.nextLine();
		boolean d = sortingMedicM.searchUser(username, password);
		return d;

	}

	private static boolean control;

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
			sc = new Scanner(System.in);
			/*
			 * while (control) { boolean log = true; while (log = true) { log= logIn(); }
			 */
			boolean control = true;
			while (control) {
				System.out.println("Choose an option, please:");
				System.out.println("-1. Register a new Patient");
				System.out.println("-2. Select a patient data"); ////////////////
				System.out.println("-3. Select doctor data"); /////////
				System.out.println("-4. Search for a hospital"); // TODO ONLY GIVES INFO ABOUT HOSPITALS
				System.out.println("-5. Create XML");
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
					createXML();
					break;
				}
				case 0: {
					control = false;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
				
			}

			// }
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void showHospitals() {
		List<Hospital> allHospitals = hospitalM.getHospitals();
		for (Hospital h : allHospitals) {
			System.out.println(h);
		}

	}

	public static void createXML() {
		xmlMI.generateXml();
	}
	
	public static void registerPatient() throws IOException {
		System.out.println("Input the patient data");
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

		System.out.print("Type the file name as it appears in folder /photos, including extension: ");
		String fileName = sc.nextLine();
		File photos = new File("./photos/" + fileName);
		InputStream streamBlob = new FileInputStream(photos);
		byte[] photo = new byte[streamBlob.available()];
		streamBlob.read(photo);
		streamBlob.close();

		Hospital mainHospital = hospitalMJPA.search1ByName("Fundacion Jimenez Diaz");

		Patient p = new Patient(name, surname, dobDate, mainHospital, photo);
		patientM.insertPatient(p);

		lookForIllness(p.getId());

	}

	public static void selectDoctor() throws IOException {
		System.out.println("Introduce the name:");
		String nameDoc = sc.nextLine();
		System.out.println("Introduce the surname:");
		String snDoc = sc.nextLine();
		List<Doctor> doctorList = doctorM.searchByName(nameDoc, snDoc);
		for (Doctor d : doctorList) {
			System.out.println(d.shortInfo());
		}
		System.out.println("Please choose a doctor, type its Id:");
		Integer id = sc.nextInt();
		// Go to the Doctor's menu
		DoctorMenu(id);
	}

	public static void selectPatient() throws IOException {
		System.out.println("Introduce the name:");
		String name = sc.nextLine();
		List<Patient> patientlist = patientM.searchByName(name);
		Iterator it = patientlist.iterator();
		while (it.hasNext()) {
			System.out.println(((Patient) it.next()).shortInfo());
		}
		System.out.println("Please choose a patient, type its Id:");
		Integer id = sc.nextInt();
		// Go to the Patient's menu
		PatientMenu(id);
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
				System.out.println("0. Back to  principal menu");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					updatePatient(id);
					updateIllnessSeverity(id);
					break;
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
				}
				case 0: {
					main(null);
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

	private static void updateIllnessSeverity(Integer id) {
		// look for Has that has the p and the i needed and only setSeverity()
		Patient p = patientM.getPatient(id);
		System.out.println("This patient has the following illnesses: ");
		List<Has> illnessesPHas = hasM.getListHas(id);
		for (Has hIllness : illnessesPHas) {
			System.out.println(hIllness.infoIllness());
		}
		System.out.println("Enter the illness that need to be updated: ");
		Integer illnessId = Integer.parseInt(sc.nextLine());
		Has hasIllness = hasM.getHas(id, illnessId);
		System.out.println("Enter the new severity: ");
		String sev = sc.nextLine();
		hasIllness.setSeverity(sev);		
		String severity = hasIllness.getSeverity();
		//patientM.update(p);
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
		System.out.println("Please, enter the severity of the illness: ");
		String severity = sc.nextLine();
		patientM.assignIllness(p, illness, severity);
	}

	private static void searchHospital(int id) {
		Patient p = patientM.getPatient(id);
		List<Has> illnesses = p.getIllness();
		for (Has hI : illnesses) {
			System.out.println(hI.infoIllness());
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
				if ((hMachine.equals(hDoctor)) & (hMachine.getPatients().size() <= hMachine.getAv())) {
					patientM.assignHospital(hMachine, p);
					break;
				}
				System.out.println("Lo sentimos, no le podemos tratar en estos momentos");
			}
		}
		System.out.println("Se le ha asignado: " + p.getHospital());
	}

	public static void updatePatient(int id) throws IOException {
		Patient p = patientM.getPatient(id);
		System.out.println("Type the new data, or press enter to keep actual data");
		System.out.println("Name (" + p.getName() + "):");
		String name = sc.nextLine();
		if (!name.equals("")) {
			p.setName(name);
		}
		System.out.println("Surame (" + p.getSurname() + "):");
		String surname = sc.nextLine();
		if (!surname.equals("")) {
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
		//patientM.update(p);
		sc.close();
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
				System.out.println("0. Back to  principal menu");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					updateDoctor(id);
					break;
				}
				case 2: {
					showDoctor(id); // showInformationDoctor
					break;
				}
				case 3: {
					removeDoctor(id);
					break;
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