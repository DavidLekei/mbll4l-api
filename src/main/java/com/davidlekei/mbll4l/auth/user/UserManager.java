package com.davidlekei.mbll4l.auth.user;

import com.davidlekei.mbll4l.database.Database;
import com.davidlekei.mbll4l.database.DatabaseConnection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

	private static UserManager INSTANCE;
	private Database db;
	private PasswordEncoder passwordEncoder;

	private UserManager(){
		db = DatabaseConnection.get();
		passwordEncoder = new BCryptPasswordEncoder();
	}

	public static UserManager get(){
		if(INSTANCE == null){
			INSTANCE = new UserManager();
		}
		return INSTANCE;
	}

	public boolean login(String username, String password){
		try {
			ResultSet results = db.isValidLogin(username, password);

			if(results.next()){
				if(passwordEncoder.matches(password, results.getString("hashed_password"))){
					return true;
				}
			}
		}catch(SQLException sqle){
			System.out.println("[ERROR]: " + sqle.getMessage());
			return false;
		}

		return false;
	}

	public boolean register(String username, String password){
		try{
			//Check to see if the users email already exists in the database
			if(login(username, password)){
				return false;
			}

			db.registerUser(username, passwordEncoder.encode(password));
			return true;
		}catch(SQLException sqle){
			System.out.println("[ERROR]: " + sqle.getMessage());
			return false;
		}
	}

}
