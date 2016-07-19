package object;

import model.Stops_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Obj_Stops {
	private Stops_pojo stops_objects[];
	private static String table_name = "stops";

	public Stops_pojo[] getStopsObjects() {
		initialise_stops_objects();
		makeObjectsFromDatabaseTable();
		return stops_objects;
	}

	public void initialise_stops_objects() {
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
		stops_objects = new Stops_pojo[rowCount];
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
				Stops_pojo stops = new Stops_pojo();
				stops.setStop_id(rslt.getString("stop_id"));
				stops.setStop_code(rslt.getString("stop_code"));
				stops.setStop_name(rslt.getString("stop_name"));
				stops.setStop_desc(rslt.getString("stop_desc"));
				stops.setStop_lat(rslt.getString("stop_lat"));
				stops.setStop_lon(rslt.getString("stop_lon"));
				stops.setStop_elevation(rslt.getString("stop_elevation"));
				stops.setZone_id(rslt.getString("zone_id"));
				stops.setStop_url(rslt.getString("stop_url"));
				stops.setLocation_type(rslt.getString("location_type"));
				stops.setParent_station(rslt.getString("parent_station"));
				stops.setPlatform_code(rslt.getString("platform_code"));
				stops.setCh_station_long_name(rslt.getString("ch_station_long_name"));
				stops.setCh_station_synonym1(rslt.getString("ch_station_synonym1"));
				stops.setCh_station_synonym2(rslt.getString("ch_station_synonym2"));
				stops.setCh_station_synonym3(rslt.getString("ch_station_synonym3"));
				stops.setCh_station_synonym4(rslt.getString("ch_station_synonym4"));
				stops_objects[index++] = stops;
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
