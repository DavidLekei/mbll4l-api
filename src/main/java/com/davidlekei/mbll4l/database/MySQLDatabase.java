package com.davidlekei.mbll4l.database;

import com.davidlekei.mbll4l.Contact;
import com.davidlekei.mbll4l.Lawyer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabase implements Database {
	private final String DB_URL = "jdbc:mysql://localhost:3306/mbll4l?";
	private final String USER = "user=root";
	private final String PASSWORD = "password=";

	private Connection connection;

	public MySQLDatabase() {
		try {
			String fullURL = DB_URL + USER + "&" + PASSWORD;
			connection = DriverManager.getConnection(fullURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Lawyer> getLawyers() throws SQLException{
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lawyer");

		ResultSet results = preparedStatement.executeQuery();

		List<Lawyer> lawyers = new ArrayList<Lawyer>();

		while(results.next()){
			lawyers.add(buildLawyer(results));
		}

		return lawyers;
	}

	public Lawyer getLawyer(int id) throws SQLException{
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lawyer WHERE id = ?");

		preparedStatement.setInt(1, id);

		ResultSet results = preparedStatement.executeQuery();

		if(results.next()){
			return buildLawyer(results);
		}else{
			throw new SQLException("Lawyer with ID " + id + " not found.");
		}
	}

	public Lawyer buildLawyer(ResultSet results) throws SQLException{

		return new Lawyer(
				results.getInt("id"),
				results.getString("last_name"),
				results.getString("first_name"),
				results.getString("firm"),
				new Contact(
						results.getString("email"),
						results.getString("phone"),
						results.getString("fax"),
						results.getString("address"),
						results.getString("city"),
						results.getString("province"),
						results.getString("postal_code")
				),
				results.getString("status"),
				results.getString("history")
		);

	}
}