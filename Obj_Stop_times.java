package object;

import model.Stop_times_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Obj_Stop_times {
	private Stop_times_pojo stop_times_objects[];
	private static String table_name = "stop_times";

	public Stop_times_pojo[] getStop_timesObjects() {
		initialise_stop_times_objects();
		makeObjectsFromDatabaseTable();
		return stop_times_objects;
	}

	public void initialise_stop_times_objects() {
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
		stop_times_objects = new Stop_times_pojo[rowCount];
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
				Stop_times_pojo stop_times = new Stop_times_pojo();
				stop_times.setTrip_id(rslt.getString("trip_id"));
				stop_times.setArrival_time(rslt.getString("arrival_time"));
				stop_times.setDeparture_time(rslt.getString("departure_time"));
				stop_times.setStop_id(rslt.getString("stop_id"));
				stop_times.setStop_sequence(rslt.getString("stop_sequence"));
				stop_times.setStop_headsign(rslt.getString("stop_headsign"));
				stop_times.setPickup_type(rslt.getString("pickup_type"));
				stop_times.setDrop_off_type(rslt.getString("drop_off_type"));
				stop_times.setShape_dist_traveled(rslt.getString("shape_dist_traveled"));
				stop_times.setAttributes_ch(rslt.getString("attributes_ch"));
				stop_times_objects[index++] = stop_times;
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
