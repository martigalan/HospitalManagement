package hospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hospital.db.ifaces.MachineManager;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Machine;

public class JDBCMachineManager implements MachineManager{

	private Connection c;
	
	public JDBCMachineManager (Connection c) {
		this.c = c;
	}

	@Override
	public List<Machine> searchByName(String name) {
		List<Machine> list = new ArrayList<Machine>();
		JDBCHospitalManager managerH = new JDBCHospitalManager(c);
		try {
			String sql ="SELECT * FROM machine WHERE name = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(0, "%" + name + "%");
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
				String name_ma = rs.getString("name");
				Integer hospID = rs.getInt("hospital.id");
				Hospital hosp = managerH.getHospital(hospID);
				Machine m = new Machine(name_ma, hosp);
				list.add(m);
			}
		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void assignHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertMachine(Machine machine) {
		try {
			String sql = "INSERT INTO machine (name, hospitalId) " +
		           "VALUES (?, ?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, machine.getName());
			st.setInt(2, machine.getHospital().getId());
			st.executeUpdate();
			st.close();
		}catch(SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
		
	}
	
}
