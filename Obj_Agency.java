package object;

import model.Agency_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Obj_Agency {
	private Agency_pojo agency_objects[];
	private static String table_name = "agency";

	public Agency_pojo[] getAgencyObjects() {
		initialise_agency_objects();
		makeObjectsFromDatabaseTable();
		return agency_objects;
	}

	public void initialise_agency_objects() {
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
		agency_objects = new Agency_pojo[rowCount];

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
				Agency_pojo agency = new Agency_pojo();
				agency.setAgency_id(rslt.getString("agency_id"));
				agency.setAgency_name(rslt.getString("agency_name"));
				agency.setAgency_url(rslt.getString("agency_url"));
				agency.setAgency_timezone(rslt.getString("agency_timezone"));
				agency.setAgency_lang(rslt.getString("agency_lang"));
				agency.setAgency_phone(rslt.getString("agency_phone"));
				agency_objects[index++] = agency;

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
