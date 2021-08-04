package sait.mms.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import sait.mms.drivers.MariaDBDriver;

public class AppDriver {

	public static void main(String[] args) throws SQLException {
		MariaDBDriver driver = new MariaDBDriver();
		driver.connect();
		ResultSet data = driver.get("SELECT * FROM movies");
		while(data.next()) {
			System.out.printf("%d, %d, %s, %d %n",data.getInt("id"),data.getInt("duration"),data.getString("title"),data.getInt("year"));
		}
		driver.disconnect();
	}

}
