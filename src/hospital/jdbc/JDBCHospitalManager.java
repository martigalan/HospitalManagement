package hospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hospital.db.ifaces.HospitalManager;
import hospital.db.pojos.Hospital;

public class JDBCHospitalManager implements HospitalManager {

	private Connection c;

	public JDBCHospitalManager(Connection c) {
		this.c = c;
	}

	public void insertHospital(Hospital h) {
		try {
			String sql = "INSERT INTO hospital (name, location)" + "VALUES (?, ?);";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, h.getName());
			p.setString(2, h.getLocation());
			p.executeUpdate();
			p.close();
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p2 = c.prepareStatement(query);
			ResultSet rs = p2.executeQuery();
			Integer lastId = rs.getInt("lastId");
			h.setId(lastId);
			p2.close();
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}

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
			String sql = "SELECT * FROM hospital WHERE name = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name_hosp = rs.getString("name");
				String location = rs.getString("location");
				Hospital h = new Hospital(id, name_hosp, location);
				list.add(h);
			}
			p.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}

		return list;
	}
	
	@Override
	public Hospital search1ByName(String name) {

		Hospital h = new Hospital();
		try {
			String sql = "SELECT * FROM hospital WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name_hosp = rs.getString("name");
				String location = rs.getString("location");
				h.setId(id);
				h.setName(name_hosp);
				h.setLocation(location);
			}
			p.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		return h;
	}

	@Override
	public Hospital getHospital(Integer hospitalId) {
		Hospital hosp = null;
		try {
			String sql = "SELECT * FROM hospital WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setInt(1, hospitalId);
			ResultSet result = statement.executeQuery();
			result.next();
			Integer id = result.getInt("id");
			String name = result.getString("name");
			String location = result.getString("location");
			hosp = new Hospital(id, name, location);
			statement.close();
		} catch (SQLException e) {
			System.out.println("  NO HOSPITAL WITH THAT ID\n");
		}
		return hosp;
	}
	
	@Override
	public List<Hospital> getHospitals(){
		List<Hospital> listHospitals = new ArrayList<Hospital>();
		try {
			String sql = "SELECT * FROM hospital;";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String location = rs.getString("location");
				Hospital h = new Hospital(id, name, location);
				listHospitals.add(h);
			}
			p.close();
			rs.close();
		}catch(SQLException e){
			System.out.println("Database error");
			e.printStackTrace();
		}
		return listHospitals;
	}
}
