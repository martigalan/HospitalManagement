package HospitalManagement.ui;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;

import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Patient;

public class Menu {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static PatientManager patientManager; //es la interfaz q queda por añadir, hay q importarla una vez creada
	private static List<Patient> patients; //List es un interface que declara métodos. Obliga a las clases que implementen el interface a implementar esos métodos
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			
			System.out.println("HI");
			System.out.println("Choose an option, please:");
			System.out.println("-1. Register a new Patient");
			System.out.println("-2. Select a patient data"); ////////////////
			System.out.println("-2. Select doctor data"); /////////
			System.out.println("-3. Search for a hospital");
			System.out.println("-4. Update doctor");
			System.out.println("-0. Exit");
			
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
				case 1: {					
					RegisterPatient();
					break;
				}
				case 2: {
					UpdatePatient();
					break;
				}
				case 3: {
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
	public static void RegisterPatient() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the patient data");
		System.out.println("Name:");
		String name= sc.nextLine();
		System.out.println("Surname:");
		String surname= sc.nextLine();
		System.out.println("Date of birth: (yyyy/MM/dd):");
		String dob = sc.nextLine();
		LocalDate dobLocalDate = LocalDate.parse(dob, formatter);		// java.time.LocalDate
		Date dobDate = Date.valueOf(dobLocalDate); // java.sql.Date   
		System.out.println("Photo:");
		byte[] photo = rs.getBytes("photo");
		Patient p= new Patient(name, surname, dobDate, photo); //falta el hospital -> borrar nuevo constructor
		// TODO insert patient in the database	
		patientManager.insertPatient(p);
	}
	
	public static void UpdatePatient() throws IOException{
		
        System.out.println("Introduce the name of the patient");
        String namePatient=r.readLine();
        Patient namePatientToSee=null;
        for(Patient p : patients){ //// listofpatients array list es una clase que importo de java
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
        
        //TODO change stuff
		
        /*
		System.out.println("Input the patient data");
		System.out.println("Name:");
		String name= r.readLine();
		System.out.println("Surname:");
		String surname= r.readLine();
		//TODO shows the patient (select)
		System.out.println("Date of birth:");	
		Date dob= r.readLine(); //Queda definir date
		System.out.println("Photo:");
		Blob photo= r.readLine(); //Sería blob????		
		Patient p= new Patient(name, surname, dob, photo);
		//TODO inster patient in the database	
		*/
	}
	
	public static void SearchHospital() throws IOException{
		
		 System.out.println("Introduce the name of the patient");
	        String namePatient=r.readLine();
	        Patient namePatientToSee=null;
	        for(Patient p : patients){ //// listofpatients array list es una clase que importo de java
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
		
		
		/*
		System.out.println("Input the patient data");
		System.out.println("Name:");
		String name= r.readLine();
		System.out.println("Surname:");
		String surname= r.readLine();
		//TODO shows the patient (select)
		System.out.println("Date of birth:");
		Date dob= r.readLine(); //Queda definir date	
		Patient p= new Patient(name, surname, dob, photo);
		//TODO inster patient in the database	
		*/
	}
	
}
			
		
	
