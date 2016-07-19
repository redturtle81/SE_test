package object;

import model.Transfers_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Obj_Transfers {
	private Transfers_pojo transfers_objects[];
	private static String table_name = "transfers";

	public Transfers_pojo[] getTransfersObjects() {
		initialise_transfers_objects();
		makeObjectsFromDatabaseTable();
		return transfers_objects;
	}

	public void initialise_transfers_objects() {
		Connection conn = null;
		int rowCount = 0;
		try {
			conn = MySqlConnection.getConnectionToMYSQL();
			String query = "SELECT COUNT(*) FROM " + table_name;
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			ResultSet rslt = stmt.getResultSet();
			rslt.next();
			rowCount = rslt.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		transfers_objects = new Transfers_pojo[rowCount];
	}

	public void makeObjectsFromDatabaseTable() {
		Connection conn = null;
		try {
			conn = MySqlConnection.getConnectionToMYSQL();
			String query = "SELECT * FROM " + table_name;
			executeQueryAndMakeObjects(conn, query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean executeQueryAndMakeObjects(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(command);
			rslt = stmt.getResultSet();
			int index = 0;
			while (rslt.next()) {
				Transfers_pojo transfers = new Transfers_pojo();
				transfers.setFrom_stop_id(rslt.getString("from_stop_id"));
				transfers.setTo_stop_id(rslt.getString("to_stop_id"));
				transfers.setTransfer_type(rslt.getString("transfer_type"));
				transfers.setMin_transfer_time(rslt.getString("min_transfer_time"));
				transfers_objects[index++] = transfers;
			}
			return true;
		} finally {
			if (rslt != null) {
				rslt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
