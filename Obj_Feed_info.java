package object;

import model.Feed_info_pojo;
import MySqlDB_management.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Obj_Feed_info {
	private Feed_info_pojo feed_info_objects[];
	private static String table_name = "feed_info";

	public Feed_info_pojo[] getFeed_infoObjects() {
		initialise_feed_info_objects();
		makeObjectsFromDatabaseTable();
		return feed_info_objects;
	}

	public void initialise_feed_info_objects() {
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
		feed_info_objects = new Feed_info_pojo[rowCount];
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
				Feed_info_pojo feed_info = new Feed_info_pojo();
				feed_info.setFeed_publisher_name(rslt.getString("feed_publisher_name"));
				feed_info.setFeed_publisher_url(rslt.getString("feed_publisher_url"));
				feed_info.setFeed_lang(rslt.getString("feed_lang"));
				feed_info.setFeed_start_date(rslt.getString("feed_start_date"));
				feed_info.setFeed_end_date(rslt.getString("feed_end_date"));
				feed_info.setFeed_version(rslt.getString("feed_version"));
				feed_info_objects[index++] = feed_info;
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
