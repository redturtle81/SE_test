package MySqlDB_management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProcessingValue {
	private static final String zipFilePath = "raw_data/GTFS.zip";
	private static final String destDirectory = "raw_data/GTFS";

	public void processingValue() {
		System.out.println("");
		UnzipTool.unzip(zipFilePath, destDirectory);
		System.out.println("Extracted all GTFS files from GTFS.zip");

		// Reading files process
		File[] listOfFiles = getFiles();

		try {
			System.out.println("Starting processing all GTFS files into mysql database");
			System.out.println("");
			for (File listOfFile : listOfFiles) {
				//System.out.println("");
				//System.out.println("Saving file " + listOfFile.getName() + " to database ");

				// Processing file process
				saveToDatabase(listOfFile);

				// System.out.println("File " + listOfFile.getName() + " saved
				// to database");
			}
			System.out.println("");
			System.out.println("All GTFS files has been saved into the database as tables");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	// Function to read files
	public File[] getFiles() {
		File folder = new File(destDirectory);
		File[] listOfFiles = folder.listFiles();
		boolean error = false;
		for (File file : listOfFiles) {
			if (!(file.isFile() && file.getName().endsWith(".txt"))) {
				error = true;
				break;
			}
		}
		if (error)
			return null;
		return listOfFiles;
	}

	// Method to process files to DB
	public void saveToDatabase(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String tableName = file.getName().replaceFirst("[.][^.]+$", "");
			// The above expression will remove the last dot followed by one or
			// more characters.

			String tableHeading = br.readLine();
			//System.out.println("The field of the table are the following:");
			//System.out.println(tableHeading);

			// Create table process
			createTable(tableName, tableHeading);
			System.out.println("");
			System.out.println("The table '" + tableName + "' has been created from file '" + tableName + ".txt'");				
			System.out.println("The field of the table are the following:");
			System.out.println(tableHeading);

			// Insert values process
			insertValuesIntoTable(tableName, file);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// Method to create table into DB
	public void createTable(String tableName, String tableHeading) {

		// Extract names of fields of a table
		String tableHead[] = getTokens(tableHeading);

		Connection conn = null;
		try {
			conn = MySqlConnection.getConnectionToMYSQL();

			// Delete the table if it exists
			String dropString = "DROP TABLE IF EXISTS " + tableName;

			executeUpdate(conn, dropString);

			String createString;

			// Query sql to create the table
			createString = "CREATE TABLE " + tableName + " ( ";
			for (int i = 0; i < tableHead.length; i++)
				if (i == 0)
					createString += tableHead[i] + " varchar(255) ";
				else
					createString += ", " + tableHead[i] + " varchar(255) ";
			createString += ")";
			// System.out.println(createString);

			executeUpdate(conn, createString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to insert values into table of DB
	public void insertValuesIntoTable(String tableName, File file) {
		Connection conn = null;
		try {
			conn = MySqlConnection.getConnectionToMYSQL();

			String fileAbsolutePath = file.getAbsolutePath();

			fileAbsolutePath = fileAbsolutePath.replace('\\', '/');

			String insertString;

			insertString = "LOAD DATA INFILE '" + fileAbsolutePath + "' INTO TABLE " + tableName
					+ " FIELDS TERMINATED BY \',\' OPTIONALLY ENCLOSED BY '\"'" + " LINES TERMINATED BY '\\r\\n'"
					+ " IGNORE 1 LINES";

			executeUpdate(conn, insertString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will run a SQL command which does not return a recordset.
	 * for eg, CREATE,INSERT,UPDATE,DELETE,DROP etc..
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command);
			return true;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	/**
	 * This function will take a csv line and extract the values in that line.
	 * After extracting the values, return a String[] containing those values.
	 */
	public String[] getTokens(String line) {
		ArrayList<String> arr = new ArrayList<String>();

		line = line.trim() + ",";

		while (line.length() > 0) {
			line = line.trim();

			String s;

			if (line.charAt(0) == '\"') {
				int p = line.indexOf("\"", 1);

				while (line.charAt(p + 1) == '\"') {
					p = p + 2;
					p = line.indexOf("\"", p);
				}

				s = line.substring(1, p);

				line = line.substring(1 + line.indexOf(",", p + 1));

				p = 0;

				while ((p = s.indexOf("\"\"", p)) >= 0) {
					s = s.substring(0, p) + "\"" + s.substring(p + 2);
					p++;
				}
			} else {
				s = line.substring(0, line.indexOf(","));
				s = s.trim();
				line = line.substring(line.indexOf(",") + 1);
			}
			arr.add(s);
		}
		return arr.toArray(new String[arr.size()]);

	}
}
