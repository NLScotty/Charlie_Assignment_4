package sait.mms.managers;

import sait.mms.drivers.MariaDBDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MovieManagementSystem {
	MariaDBDriver database = new MariaDBDriver();
	Scanner input = new Scanner(System.in);
	
	public void displayMenu() {
		while(true) {
			System.out.println("Jim's Movie Manager");	
			System.out.printf("%-6d%-30s%n",1,"Add new movie");
			System.out.printf("%-6d%-30s%n",2,"Print movies released in year");
			System.out.printf("%-6d%-30s%n",3,"Print random list of movies");
			System.out.printf("%-6d%-30s%n",4,"Delete a movie");
			System.out.printf("%-6d%-30s%n",5,"Exit");
			System.out.print("\nEnter an option: ");
			String userInput = input.next();
			System.out.println("");
			if(userInput.equals("1")) {
				addMovie();
			}else if(userInput.equals("4")) {
				deleteMovie();
			}else if(userInput.equals("2")) {
				printMoviesInYear();
			}else if(userInput.equals("3")) {
				printRandomMovies();
			}else if(userInput.equals("5")) {
				System.exit(0);
			}else {
				System.out.print("Invalid Input, please try again!");
			}
		}
	}
	
	public void addMovie() {
		try {
			database.connect();
			System.out.print("Enter movie title: ");
			input.nextLine();
			String title = input.nextLine();
			System.out.print("Enter duration: ");
			int duration = input.nextInt();
			System.out.print("Enter year: ");
			int year = input.nextInt();
			String sqlStmt = "INSERT INTO movies (duration, title, year) VALUES ('" + duration +"','" + title  + "','" + year + "') ";
			database.update(sqlStmt);
			database.disconnect();
			System.out.println("Added movie to database");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void printMoviesInYear() {
		try {
			database.connect();
			System.out.print("Enter In Year: ");
			int year = input.nextInt();
			ResultSet data = database.get("SELECT * FROM movies WHERE year = "+year);
			int totalDuration = 0;
			System.out.println("\nMovie List");
			System.out.printf("%-15s%-8s%-255s%n","Duration","Year","Title");
			while(data.next()) {
				System.out.printf("%-15d%-8d%-255s%n",data.getInt("duration"),data.getInt("year"),data.getString("title"));
				totalDuration += data.getInt("duration");
			}
			System.out.println("\nTotal duration: "+totalDuration +" minutes");
			database.disconnect();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void printRandomMovies() {
		try {
			database.connect();
			System.out.print("Enter Number of Movies: ");
			int number = input.nextInt();
			ResultSet data = database.get("SELECT * FROM movies ORDER BY RAND() LIMIT "+number);
			int totalDuration = 0;
			System.out.println("\nMovie List");
			System.out.printf("%-15s%-8s%-255s%n","Duration","Year","Title");
			while(data.next()) {
				System.out.printf("%-15d%-8d%-255s%n",data.getInt("duration"),data.getInt("year"),data.getString("title"));
				totalDuration += data.getInt("duration");
			}
			System.out.println("\nTotal duration: "+totalDuration +" minutes");
			database.disconnect();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMovie() {
		try {
			database.connect();
			System.out.print("Enter Movie Id: ");
			int id = input.nextInt();
			String sqlStmt = "DELETE FROM movies WHERE ID = " + id;
			database.update(sqlStmt);
			database.disconnect();
			System.out.println("Database updated!");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
