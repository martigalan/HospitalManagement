package HospitalManagement.ui;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Blob;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import hospital.db.ifaces.DoctorManager;
import hospital.db.ifaces.PatientManager;
import hospital.db.ifaces.HospitalManager;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Patient;
import hospital.jdbc.ConnectionManager;
import hospital.jdbc.JDBCDoctorManager;
import hospital.jdbc.JDBCHospitalManager;
import hospital.jdbc.JDBCPatientManager;
import hospital.jpa.JPAPatientManager;

public class Menu {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static List<Patient> patients; //List es un interface que declara métodos. Obliga a las clases que implementen el interface a implementar esos métodos
	private static List<Doctor> doctors;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static PatientManager PatientM; //es la interfaz q queda por añadir, hay q importarla una vez creada
	private static DoctorManager DoctorM;
	private static HospitalManager HospitalM;
	private static List<Patient> ListOfPatients; //List es un interface que declara métodos. Obliga a las clases que implementen el interface a implementar esos métodos
	private static ConnectionManager connectionManager;
	
	public static void main(String[] args) {
		try {
			connectionManager = new ConnectionManager();
			patientManager = new JPAPatientManager();
			doctorManager = new JDBCDoctorManager(connectionManager.getConnection());
			hospitalManager = new JDBCHospitalManager(connectionManager.getConnection());
			// TODO Auto-generated method stub
			
			System.out.println("HI");
			System.out.println("Choose an option, please:");
			System.out.println("-1. Register a new Patient");
			System.out.println("-2. Select a patient data"); ////////////////
			System.out.println("-3. Select doctor data"); /////////
			System.out.println("-4. Search for a hospital");						
			System.out.println("-0. Exit");
			
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
				case 1: {					
					registerPatient();
					break;
				}
				case 2: {
					SelectPatient();
					break;
				}
				case 3: {
					SelectDoctor();
					break;
				}
			
				case 4: {
					SearchHospital();
					break;
				}
				case 0: {
					return;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			
		} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			    // TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	public static void registerPatient() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the patient data");
		System.out.println("Name:");
		String name= sc.nextLine();
		System.out.println("Surname:");
		String surname= sc.nextLine();		 
		System.out.println("Date of birth (yyyy-MM-dd):");
		String dob = sc.nextLine();
		LocalDate dobLocalDate = LocalDate.parse(dob);		// java.time.LocalDate
		Date dobDate = Date.valueOf(dobLocalDate);	
		//System.out.println("Photo:");
		//byte[] photo = sc.nextByte();
		byte[]photo =null; //used for testing
		// TODO add photo in SQLinsert
		Hospital h = new Hospital(); //TODO search for MAIN hospital once the data is in the db
	
		Patient p= new Patient(name, surname, dobDate, h, photo); //falta el hospital -> borrar nuevo constructor
		PatientM.insertPatient(p);
	}
	
	public static void selectDoctor() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		String nameDoc = sc.nextLine();
		System.out.println("Introduce the surname:");
		String snDoc = sc.nextLine();
		List<Doctor> doctorList = doctorManager.searchByName(nameDoc, snDoc);
		System.out.println(doctorList);
		System.out.println("Please choose a doctor, type its Id:");
		Integer id = sc.nextInt();
		// Go to the Doctor's menu
		DoctorMenu(id); 
	}
	
	public static void selectPatient() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		String name = sc.nextLine();
		List<Patient> patientlist = patientManager.searchByName(name);
		Iterator it = patientlist.iterator();
		while(it.hasNext()) {
			System.out.println(((Patient) it.next()).shortInfo());
		}
		System.out.println("Please choose a patient, type its Id:");
		Integer id = sc.nextInt();
		// Go to the Patient's menu
		PatientMenu(id);
	}
	
	public static void SelectDoctor() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		String name = sc.nextLine();
		List<Doctor> doctorlist = PatientM.searchByName(name);
		System.out.println(doctorlist);
		System.out.println("Please choose a doctor, type its Id:");
		Integer id = sc.nextInt();
		// Go to the Patient's menu
		DoctorMenu(id);
	}
	
	public static void SearchHospital() throws IOException{
		//TODO search hospital
		
		 System.out.println("Introduce the name of the patient");
	        String namePatient=r.readLine();
	        Patient namePatientToSee=null;
	        for(Patient p : patients){ // listofpatients array list es una clase que importo de java
	            if(p.getName().equals(namePatient)){ 
	                namePatientToSee=p;
	                break;
	            }
	        }
	        if(namePatientToSee!=null){
	            System.out.println(namePatientToSee);
	        }
	        else{
	            System.out.println("We can't find a patient with that name");
	        }
		
		
	}
	
	
	
	
	
	public static void PatientMenu(int id) {
		while (true) {
			try {
				System.out.println("What do you want to do with the patient?:");
				System.out.println("1. Update data");
				System.out.println("2. Show data");
				System.out.println("0. Back to  principal menu");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					updatePatient(id);
					break;
				}
				case 2: {
					showPatient(id);
					//TODO show patient
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
	
	public static void updatePatient(int id) throws IOException {
		Scanner sc = new Scanner(System.in);
		Patient p = PatientM.getPatient(id);
		//TODO get patient
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
		
		//TODO update photo patient	
		PatientM.updatePatient(p);
	}
	
	public static void showPatient (int id) throws IOException {
		Patient p = patientManager.getPatient(id);
		System.out.println(p);
	}
	
	
	public static void DoctorMenu(int id) {
		while (true) {
			try {

				System.out.println("What do you want to do with the doctor?:");
				System.out.println("1. Update data");
				System.out.println("2. Show data");
				System.out.println("3. Delete doctor");
				System.out.println("3. Show illness"); //illness treated by doctor or what?
				System.out.println("0. Back to  principal menu");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					updateDoctor(id);
					break;
				}
				case 2: {
					showDoctor(id); //showInformationDoctor
					//TODO show patient
					break;
				}	
				case 3: {
					removeDoctor(id);
				}
				case 4: {
					//showIllness(id);
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
		Scanner sc = new Scanner(System.in);
		Doctor p = DoctorM.getDoctor(id);
		//TODO get patient
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
		
		//TODO update photo patient	
		DoctorM.updateDoctor(p);
	}
	
	public static void showDoctor(int id) throws IOException {
		Doctor p = DoctorM.getDoctor(id);
		System.out.println(p);
		
	}
	
	public static void removeDoctor(int id) throws IOException {
				DoctorM.deleteDoctor(id);
				System.out.println("The doctor has been removed. :(");
			}
			//TODO delete with jpa doctor
}
	
		
	
