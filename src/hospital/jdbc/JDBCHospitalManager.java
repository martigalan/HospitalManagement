package hospital.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hospital.db.ifaces.HospitalManager;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Machine;

public class JDBCHospitalManager implements HospitalManager{
	
	private Connection c;

	public JDBCHospitalManager(Connection c) {
		this.c = c;

	}

	@Override
	public void updateHospital(Hospital hospital) {
		try {
			String sql = "UPDATE hospital SET" + " name = ?, " + " location = ?, " + " WHERE id = ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setString(1, hospital.getName());
			p.setString(2, hospital.getLocation());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		
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

	@Override
	public Hospital getHospital(Integer hosp_id) {
		Hospital hosp = null;
		
		try {
			String sql = "SELECT * FROM hospital WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setInt(1, hosp_id);
			ResultSet result = statement.executeQuery();
			result.next();
			String name = result.getString("name");
			String location = result.getString("location");
			
			hosp = new Hospital(name, location);
			
		}catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		
		return hosp;
	}
	
	
}
