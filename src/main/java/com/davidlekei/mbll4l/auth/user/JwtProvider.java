package com.davidlekei.mbll4l.auth.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class JwtProvider implements TokenProvider{

	private static JwtProvider INSTANCE;

	private KeyPairGenerator kpg;
	private KeyPair kp;
	private Algorithm algorithm;
	private JWTVerifier verifier;

	private String issuer = "tracker.io";
	private String HMACSecretKey = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0cmFja2VyaW8ifQ.DoA_yMKsGyBF1NJOSyU48katfP07xxpNrOCFpxpw_OGvDqXlcXsyAAubZm1t32Kx32M2jZEmXds8jDegWKO9YA";
	private long JWT_EXPIRATION = 36000L;

	private JwtProvider(boolean generateNew){
		if(generateNew) {
			generateNewKeyPair();
		}else{
			loadExistingKeyPair();
		}
		algorithm = Algorithm.HMAC512(HMACSecretKey);
		verifier = JWT.require(algorithm).withIssuer(issuer).build();
		//algorithm = Algorithm.RSA256((RSAPublicKey)kp.getPublic(), (RSAPrivateKey)kp.getPrivate());
	}



	public static JwtProvider get(){
		if(INSTANCE == null){
			INSTANCE = new JwtProvider(true);
		}
		return INSTANCE;
	}



	private void generateNewKeyPair(){
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			kp = kpg.generateKeyPair();
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
	}



	//TODO: Implement this https://stackoverflow.com/questions/11787571/how-to-read-pem-file-to-get-private-and-public-key/14177328
	private void loadExistingKeyPair(){
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(new File("path")));
//			byte[] keyBytes = new byte[(int) file.length];
//			dis.readFully(keyBytes);
//			dis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	public String getNewToken(String username){
		try{
			String token = JWT.create()
					.withIssuer(issuer)
					.withSubject("tracker.io auth token")
					.withClaim("username", username)
					.withIssuedAt(new Date())
					.withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
					.sign(algorithm);
			return token;
		}catch(JWTCreationException jce){
			jce.printStackTrace();
			return null;
		}
	}

	public String verifyToken(String token){
		DecodedJWT decodedJWT;
		try{
			JWT.require(algorithm).withIssuer(issuer).build();
			decodedJWT = verifier.verify(token);
			return decodedJWT.getClaim("username").asString();
		}catch(JWTVerificationException jwtve){
			jwtve.printStackTrace();
			return null;
		}
	}

}
