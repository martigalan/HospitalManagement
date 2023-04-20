package hospital.jdbc;

import java.sql.Connection;

import hospital.db.ifaces.MachineManager;

public class JDBCMachineManager implements MachineManager{

	private Connection c;
	
	public JDBCMachineManager (Connection c) {
		this.c = c;
	}
	
}
