package object;

import model.Calendar_dates_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Obj_Calendar_dates {
	private Calendar_dates_pojo calendar_dates_objects[];
	private static String table_name = "calendar_dates";

	public Calendar_dates_pojo[] getCalendar_datesObjects() {
		initialise_calendar_dates_objects();
		makeObjectsFromDatabaseTable();
		return calendar_dates_objects;
	}

	public void initialise_calendar_dates_objects() {
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
		calendar_dates_objects = new Calendar_dates_pojo[rowCount];
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
				Calendar_dates_pojo calendar_dates = new Calendar_dates_pojo();
				calendar_dates.setService_id(rslt.getString("service_id"));
				calendar_dates.setDate(rslt.getString("date"));
				calendar_dates.setException_type(rslt.getString("exception_type"));
				calendar_dates_objects[index++] = calendar_dates;
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