package object;

import model.Frequencies_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Obj_Frequencies {
	private Frequencies_pojo frequencies_objects[];
	private static String table_name = "frequencies";

	public Frequencies_pojo[] getFrequenciesObjects() {
		initialise_frequencies_objects();
		makeObjectsFromDatabaseTable();
		return frequencies_objects;
	}

	public void initialise_frequencies_objects() {
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
		frequencies_objects = new Frequencies_pojo[rowCount];
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
				Frequencies_pojo frequencies = new Frequencies_pojo();
				frequencies.setTrip_id(rslt.getString("trip_id"));
				frequencies.setStart_time(rslt.getString("start_time"));
				frequencies.setEnd_time(rslt.getString("end_time"));
				frequencies.setHeadway_secs(rslt.getString("headway_secs"));
				frequencies_objects[index++] = frequencies;
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
