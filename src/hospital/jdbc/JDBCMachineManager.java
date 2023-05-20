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
			p.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void assignHospital(Hospital hospital, Integer machineID) {
		String sql = "UPDATE machine SET hospital=? WHERE id=?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);
			prep.setObject(machineID, hospital);
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
		
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
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p2 = c.prepareStatement(query);
			ResultSet rs = p2.executeQuery();
			Integer lastId = rs.getInt("lastId");
			machine.setId(lastId);
			p2.close();
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
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
		return machines;
	}
	
	@Override
	public List<Machine> machinesInHospital(Hospital h) {
		List<Machine> machines = new ArrayList();
		try {
			String sql = "SELECT * FROM machine WHERE hospitalId = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, h.getId());
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
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
		return machines;
	}
	
	@Override
	public List<Machine> getMachines(){
		List<Machine> listM = new ArrayList<Machine>();
		JDBCHospitalManager hospitalM = new JDBCHospitalManager(c);
		try {
			String sql = "SELECT * FROM machine";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int hId = rs.getInt("hospitalId");
				Hospital h = new Hospital();
				h = hospitalM.getHospital(hId);
				Machine m = new Machine(id, name, h);
				listM.add(m);
			}
			p.close();
			rs.close();
		}catch(SQLException e){
			System.out.println("Database error");
			e.printStackTrace();
		}
		return listM;
	}

}
