package com.davidlekei.mbll4l;

import com.davidlekei.mbll4l.database.Database;
import com.davidlekei.mbll4l.database.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@RestController
public class Mbll4lApplication {

	Database db = DatabaseConnection.get();

	public static void main(String[] args) {
		SpringApplication.run(Mbll4lApplication.class, args);
	}

	@CrossOrigin
	@GetMapping(value = "/lawyers", produces = "application/json")
	public ResponseEntity getLawyers(){
		try{
			return ResponseEntity.ok(db.getLawyers());
		}catch(SQLException sqle){
			System.out.println("[ERROR] - " + sqle.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@CrossOrigin
	@GetMapping(value = "/lawyers/{id}", produces = "application/json")
	public ResponseEntity getLawyer(@PathVariable int id){
		try{
			return ResponseEntity.ok(db.getLawyer(id));
		}catch(SQLException sqle){
			System.out.println("[ERROR] - " + sqle.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
