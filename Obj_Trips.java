package object;

import model.Trips_pojo;
import MySqlDB_management.MySqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Obj_Trips {
	private Trips_pojo trips_objects[];
	private static String table_name = "trips";

	public Trips_pojo[] getTripsObjects() {
		initialise_trips_objects();
		makeObjectsFromDatabaseTable();
		return trips_objects;
	}

	public void initialise_trips_objects() {
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
		trips_objects = new Trips_pojo[rowCount];
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
				Trips_pojo trips = new Trips_pojo();
				trips.setRoute_id(rslt.getString("route_id"));
				trips.setService_id(rslt.getString("service_id"));
				trips.setTrip_id(rslt.getString("trip_id"));
				trips.setTrip_headsign(rslt.getString("trip_headsign"));
				trips.setTrip_short_name(rslt.getString("trip_short_name"));
				trips.setDirection_id(rslt.getString("direction_id"));
				trips.setBlock_id(rslt.getString("block_id"));
				trips.setShape_id(rslt.getString("shape_id"));
				trips.setBikes_allowed(rslt.getString("bikes_allowed"));
				trips.setAttributes_ch(rslt.getString("attributes_ch"));
				trips_objects[index++] = trips;
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
