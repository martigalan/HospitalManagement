package hospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hospital.db.ifaces.IllnessManager;
import hospital.db.pojos.Illness;

public class JDBCIllnessManager implements IllnessManager {

	private Connection c;

	public JDBCIllnessManager(Connection c) {
		this.c = c;
		if (this.getIllnesses().isEmpty()){
			//Illness i1 = new Illness(); set parameters and repeat with each illness
			//connections between tables (patient and doctor)
		}
	}

	// This is used in the constructor to insert pre-made info in db
	@Override
	public void insertIllness(Illness i) {
		try {
			String sql = "INSERT INTO illness (condition)" + "VALUES (?);";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, i.getCondition());
			p.executeUpdate();
			p.close();
			String query = "SELECT last_insert_rowid() AS lastId"; //comprobar último ID de esa tabla
			PreparedStatement p2 = c.prepareStatement(query);
			ResultSet rs = p2.executeQuery();
			Integer lastId = rs.getInt("lastId");
			i.setId(lastId);
			p2.close();
		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
	} 
	
	@Override
	public Illness getIllness(int id) {
		try {
			String sql = "SELECT * FROM illness WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer iId = rs.getInt("id");
			String condition = rs.getString("condition");
			Illness i = new Illness(iId, condition);
			rs.close();
			p.close();
			return i;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Illness> getIllnesses(){
		List<Illness> listIllnesses = new ArrayList<Illness>();
		try {
			String sql = "SELECT * FROM illness";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String condition = rs.getString("condition");
				Illness i = new Illness(id, condition);
				listIllnesses.add(i);
			}
		}catch(SQLException e){
			System.out.println("Database error");
			e.printStackTrace();
		}
		return listIllnesses;
	}
}
