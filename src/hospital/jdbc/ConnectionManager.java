package hospital.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	Connection c;
	
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
			String table2 = "CREATE TABLE doctor (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " speciality TEXT NOT NULL," 
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id));";
			s.executeUpdate(table2);
			String table3 = "CREATE TABLE machine (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id));";
			s.executeUpdate(table3);
			String table4 = "CREATE TABLE vets (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " phone INTEGER," + " email TEXT NOT NULL," + " speciality TEXT);";
			s.executeUpdate(table4);
			String table5 = "CREATE TABLE illness (id INTEGER PRIMARY KEY AUTOINCREMENT," + " condition TEXT NOT NULL," 
					+ " doctorId INTEGER NOT NULL REFERENCES hospital(id));";
			s.executeUpdate(table5);
			String table6 = "CREATE TABLE patient (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " hospitalId INTEGER REFERENCES hospital(id),"
					+ " photo BLOB);";
			s.executeUpdate(table6);
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
