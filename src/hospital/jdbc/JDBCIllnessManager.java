package hospital.jdbc;

import java.sql.Connection;

import hospital.db.ifaces.IllnessManager;

public class JDBCIllnessManager implements IllnessManager {
	
	private Connection c;
	
	public JDBCIllnessManager(Connection c) {
		this.c = c;
	}

	@Override
	public void searchIllnessByName(String name) {
		// TODO Auto-generated method stub
	}

}
