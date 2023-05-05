package hospital.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import hospital.db.ifaces.DoctorManager;
import hospital.db.ifaces.HospitalManager;
import hospital.db.ifaces.IllnessManager;
import hospital.db.ifaces.MachineManager;
import hospital.db.ifaces.PatientManager;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Illness;
import hospital.db.pojos.Machine;
import hospital.jpa.JPADoctorManager;
import hospital.jpa.JPAPatientManager;

public class ConnectionManager {
	
	private Connection c;
	private static PatientManager patientM;
	private static DoctorManager doctorM;
	private static HospitalManager hospitalM;
	private static IllnessManager illnessM;
	private static MachineManager machineM;
	
	public ConnectionManager() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/HospitalManagement.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();
		} catch (Exception e) {
			System.out.println("Database access error");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		try {
			
			
			Statement s = c.createStatement();
			String table = "CREATE TABLE hospital (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " location TEXT NOT NULL);";
			s.executeUpdate(table);
			
			hospitalM = new JDBCHospitalManager(this.getConnection());

			
			Hospital mainH = new Hospital(1, "main", "main");
			Hospital hosp2 = new Hospital("Hospital Universitario Fundacion Jimenez Diaz",
					"Av. Reyes Catolicos 2, Madrid");
			Hospital hosp3 = new Hospital("Centro Medico Teknon",
					"Calle Vilana 12, Barcelona");
			Hospital hosp4 = new Hospital("HM Universitario Sanchinarro",
					"Calle Oña 10, Madrid");
			Hospital hosp5 = new Hospital("Hospital Universitari i Politecnic la Fe",
					"Av. de Fernando Abril Martorell 106, Valencia");
			Hospital hosp6 = new Hospital("Hospital Infantil Universitario Nino Jesus",
					"Av. Menendez Pelayo 65, Madrid");
			hospitalM.insertHospital(mainH);
			hospitalM.insertHospital(hosp2);
			hospitalM.insertHospital(hosp3);
			hospitalM.insertHospital(hosp4);
			hospitalM.insertHospital(hosp5);
			hospitalM.insertHospital(hosp6);
			
			String table2 = "CREATE TABLE doctor (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " speciality TEXT NOT NULL," + " salary INTEGER, password TEXT, username TEXT,"
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id));";
			s.executeUpdate(table2);
			
			Doctor doc1 = new Doctor("John", "Beckett", new Date(1975-06-05), 
					"Endocrininologist", 55000.00, hosp4); 
			Doctor doc2 = new Doctor("Adele", "Brown", new Date(1975-06-05), 
					"Nephrologist", 55000.00, hosp3); 
			Doctor doc3 = new Doctor("Agnes", "Simpson", new Date(1975-06-05), 
					"Neurologist", 55000.00, hosp6); 
			Doctor doc4 = new Doctor("Michael", "Howland", new Date(1975-06-05), 
					"Endocrininologist", 55000.00, hosp5); 
			Doctor doc5 = new Doctor("Luke", "Donaldson", new Date(1975-06-05), 
					"Neurologist", 55000.00, hosp2); 
			Doctor doc6 = new Doctor("Samantha", "Wilson", new Date(1975-06-05), 
					"Haematologist", 55000.00, hosp4); 
			
			doctorM = new JPADoctorManager();
			
			doctorM.insertDoctor(doc1);
			doctorM.insertDoctor(doc2);
			doctorM.insertDoctor(doc3);
			doctorM.insertDoctor(doc4);
			doctorM.insertDoctor(doc5);
			doctorM.insertDoctor(doc6);
			
			String table3 = "CREATE TABLE machine (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id));";
			s.executeUpdate(table3);
			
			Machine m1 = new Machine("Microsurgery", hosp3);
			Machine m2 = new Machine("SGL2 Inhibitors/Budesonide", hosp5);
			Machine m3 = new Machine("Tirzepatide", hosp4);
			Machine m4 = new Machine("Abecma", hosp6);
			Machine m5 = new Machine("Enzyme replacement therapy", hosp2);
			
			machineM = new JDBCMachineManager(this.getConnection());
			
			machineM.insertMachine(m1);
			machineM.insertMachine(m2);
			machineM.insertMachine(m3);
			machineM.insertMachine(m4);
			machineM.insertMachine(m5);

			String table5 = "CREATE TABLE illness (id INTEGER PRIMARY KEY AUTOINCREMENT," + " condition TEXT NOT NULL," 
					+ " doctorId INTEGER NOT NULL REFERENCES hospital(id));";
			s.executeUpdate(table5);
			
			Illness il1 = new Illness("Acoustic Neuroma");
			Illness il2 = new Illness("IgA Nephropahy");
			Illness il3 = new Illness("Wolfram Syndrome");
			Illness il4 = new Illness("Multiple Myeloma");
			Illness il5 = new Illness("Sanfilippo Syndrome");
			
			illnessM = new JDBCIllnessManager(this.getConnection());
			
			illnessM.insertIllness(il1);
			illnessM.insertIllness(il2);
			illnessM.insertIllness(il3);
			illnessM.insertIllness(il4);
			illnessM.insertIllness(il5);
			
			String table6 = "CREATE TABLE patient (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " hospitalId NOT NULL INTEGER REFERENCES hospital(id),"
					+ " photo BLOB);";
			s.executeUpdate(table6);
			patientM = new JPAPatientManager();
			
			String table7 = "CREATE TABLE treats (machineId INTEGER NOT NULL REFERENCES machine(id), " + "illnessId INTEGER NOT NULL REFERENCES illness(id), " 
			        + "successRate TEXT NOT NULL, " + "PRIMARY KEY (machineId, illnessId));";
			s.executeUpdate(table7);
			
			String table8 = "CREATE TABLE hasIllness (illnessId INTEGER NOT NULL REFERENCES illness(id), " + "patientId INTEGER NOT NULL REFERENCES patient(id), " 
			        + "severity TEXT NOT NULL, " + "PRIMARY KEY (illnessId, patientId));";
			s.executeUpdate(table8);
			
			String table9 = "CREATE TABLE doctorTreats (illnessId INTEGER NOT NULL REFERENCES illness(id), " + "doctorId INTEGER NOT NULL REFERENCES doctor(id)," +
			        "PRIMARY KEY (illnessId, doctorId));";
			s.executeUpdate(table9);
			s.close();
		} catch (SQLException e) {
			// Check if the exception is because the tables already exist
			if (e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return c;
	}
	
	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
}
