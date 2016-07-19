package object;

import model.Calendar_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Obj_Calendar {
	private Calendar_pojo calendar_objects[];
	private static String table_name = "calendar";

	public Calendar_pojo[] getCalendarObjects() {
		initialise_calendar_objects();
		makeObjectsFromDatabaseTable();
		return calendar_objects;
	}

	public void initialise_calendar_objects() {
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
		calendar_objects = new Calendar_pojo[rowCount];
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
				Calendar_pojo calendar = new Calendar_pojo();
				calendar.setService_id(rslt.getString("service_id"));
				calendar.setMonday(rslt.getString("monday"));
				calendar.setTuesday(rslt.getString("tuesday"));
				calendar.setWednesday(rslt.getString("wednesday"));
				calendar.setThursday(rslt.getString("thursday"));
				calendar.setFriday(rslt.getString("friday"));
				calendar.setSaturday(rslt.getString("saturday"));
				calendar.setSunday(rslt.getString("sunday"));
				calendar.setStart_date(rslt.getString("start_date"));
				calendar.setEnd_date(rslt.getString("end_date"));
				calendar_objects[index++] = calendar;
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