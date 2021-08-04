package sait.mms.drivers;

import java.sql.*;

import sait.mms.contracts.DatabaseDriver;

public class MariaDBDriver implements DatabaseDriver {
	
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	Connection conn;
	
	//String with the database url
	final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s",
			SERVER, PORT, DATABASE, USERNAME, PASSWORD);

	@Override
	public void connect() throws SQLException {
		conn = DriverManager.getConnection(DB_URL);
	}

	@Override
	public ResultSet get(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		return result;
	}

	@Override
	public int update(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		int rows = stmt.executeUpdate(query);
		return rows;
	}

	@Override
	public void disconnect() throws SQLException {
		conn.close();
	}

}
