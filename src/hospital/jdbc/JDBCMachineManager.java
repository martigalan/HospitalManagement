package hospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import hospital.db.ifaces.MachineManager;
import hospital.db.pojos.Doctor;
import hospital.db.pojos.Hospital;
import hospital.db.pojos.Illness;
import hospital.db.pojos.Machine;
import hospital.jpa.JPADoctorManager;

public class JDBCMachineManager implements MachineManager {

	private Connection c;

	public JDBCMachineManager(Connection c) {
		this.c = c;
	}

	@Override
	public List<Machine> searchByName(String name) {
		List<Machine> list = new ArrayList<Machine>();
		JDBCHospitalManager managerH = new JDBCHospitalManager(c);
		try {
			String sql = "SELECT * FROM machine WHERE name = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(0, "%" + name + "%");
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name_ma = rs.getString("name");
				Integer hospID = rs.getInt("hospital.id");
				Hospital hosp = managerH.getHospital(hospID);
				Machine m = new Machine(id, name_ma, hosp);
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
			String sql = "INSERT INTO machine (name, hospitalId) " + "VALUES (?, ?);";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, machine.getName());
			st.setInt(2, machine.getHospital().getId());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}

	}
	
	@Override
	public Machine getMachine(Integer id) {
		JDBCHospitalManager hospM = new JDBCHospitalManager(c);
		try {
			String sql = "SELECT * FROM machine WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer mId = rs.getInt("id");
			String name = rs.getString("name");
			Integer hId = rs.getInt("hospitalId");
			Hospital h = hospM.getHospital(hId);
			Machine m = new Machine(mId, name, h);
			rs.close();
			p.close();
			return m;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//TODO ask if this is right
	public List<Machine> machineTreatsIllness(Illness i) {
		List<Machine> machines = new ArrayList();
		try {
			String sql = "SELECT m.id FROM machine AS m JOIN treats AS t ON m.id = t.machineId "
					+ "WHERE t.illnessId = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, i.getId());
			ResultSet rs = st.executeQuery();
			List<Integer> mIds = new ArrayList();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				mIds.add(id);
			}
			//i have a list of machine ids that i have to convert to machines
			for (Integer mId : mIds){
				machines.add(this.getMachine(mId));
			}
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
		return machines;
	}

}
