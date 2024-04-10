package com.davidlekei.mbll4l.auth;

import com.davidlekei.mbll4l.auth.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	private TokenProvider tokenProvider = JwtProvider.get();
	private UserManager userManager = UserManager.get();

	@CrossOrigin
	@PostMapping(value = "/auth/login", produces = "application/json")
	public ResponseEntity login(@RequestBody LoginForm form){
		System.out.println("Login Request Recieved: " + form.getEmail());
		if(userManager.login(form.getEmail(), form.getPassword())){

			User user = new User(new JWT(
					tokenProvider.getNewToken(form.getEmail()), ""),
					form.getEmail()
			);

			return ResponseEntity.ok(user);

		}else{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@CrossOrigin
	@PostMapping(value = "/auth/signup", produces = "application/json")
	public ResponseEntity signup(@RequestBody RegisterForm form){
		System.out.println("[DEBUG] - Signup Request for Email: " + form.getEmail());
		System.out.println("[DEBUG] - Signup Request for Email: " + form.getEmail());
		if(userManager.register(form.getEmail(), form.getPassword())) {
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
