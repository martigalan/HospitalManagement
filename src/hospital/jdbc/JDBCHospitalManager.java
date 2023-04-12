package hospital.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.db.ifaces.HospitalManager;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Machine;

public class JDBCHospitalManager implements HospitalManager{
	Connection c;
	
	public JDBCHospitalManager() {
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
					+ " location TEXT NOT NULL)";
			s.executeUpdate(table);
			String table2 = "CREATE TABLE doctor (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " speciality TEXT NOT NULL," 
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table2);
			String table3 = "CREATE TABLE machine (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " hospitalId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table3);
			String table4 = "CREATE TABLE vets (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL,"
					+ " phone INTEGER," + " email TEXT NOT NULL," + " speciality TEXT)";
			s.executeUpdate(table4);
			String table5 = "CREATE TABLE illness (id INTEGER PRIMARY KEY AUTOINCREMENT," + " condition TEXT NOT NULL," 
					+ " doctorId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table5);
			String table6 = "CREATE TABLE patient (id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL," 
					+ " surname TEXT NOT NULL," + " dob DATE NOT NULL," + " hospitalId INTEGER NOT NULL REFERENCES hospital(id))";
			s.executeUpdate(table6);
			// TODO N-N tables
			s.close();
		} catch (SQLException e) {
			if (e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	@Override
	public void updateHospital(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Hospital> searchByName(String name) {
		List<Hospital> list = new ArrayList<Hospital>();
		
		try {
			String sql ="SELECT * FROM hospital WHERE name = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(0, "%" + name + "%");
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
				String name_hosp = rs.getString("name");
				String location = rs.getString("location");
				Hospital h = new Hospital(name_hosp, location);
				list.add(h);
			}
		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void assignMachine(Machine machine) {
		// TODO Auto-generated method stub
		
	}
	
	
}
