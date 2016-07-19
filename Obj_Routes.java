package object;

import model.Routes_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Obj_Routes {
	private Routes_pojo routes_objects[];
	private static String table_name = "routes";

	public Routes_pojo[] getRoutesObjects() {
		initialise_routes_objects();
		makeObjectsFromDatabaseTable();
		return routes_objects;
	}

	public void initialise_routes_objects() {
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
		routes_objects = new Routes_pojo[rowCount];
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
				Routes_pojo routes = new Routes_pojo();
				routes.setRoute_id(rslt.getString("route_id"));
				routes.setAgency_id(rslt.getString("agency_id"));
				routes.setRoute_short_name(rslt.getString("route_short_name"));
				routes.setRoute_long_name(rslt.getString("route_long_name"));
				routes.setRoute_desc(rslt.getString("route_desc"));
				routes.setRoute_type(rslt.getString("route_type"));
				routes.setRoute_url(rslt.getString("route_url"));
				routes.setRoute_color(rslt.getString("route_color"));
				routes.setRoute_text_color(rslt.getString("route_text_color"));
				routes_objects[index++] = routes;
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
