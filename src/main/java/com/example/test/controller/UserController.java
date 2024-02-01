package com.example.test.controller;

import java.net.URI;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.models.dtos.MessageDto;
import com.example.test.models.dtos.UserDto;
import com.example.test.models.requests.UserRequest;
import com.example.test.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	private ResponseEntity<?> createUser(@RequestBody UserRequest request){
		try {
			validateRequest(request);
			UserDto newUser = userService.createUser(request);
			return new ResponseEntity<UserDto>(newUser, HttpStatus.CREATED);
		}catch(Exception e) {
			MessageDto messageDto = new MessageDto(e.getMessage());
			return new ResponseEntity<MessageDto>(messageDto, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PatchMapping
	private ResponseEntity<?> updateUser(@RequestBody UserRequest request){
		try {
			validateRequest(request);
			UserDto newUser = userService.updateUser(request);
			return new ResponseEntity<UserDto>(newUser, HttpStatus.OK);
		}catch(Exception e) {
			MessageDto messageDto = new MessageDto(e.getMessage());
			return new ResponseEntity<MessageDto>(messageDto, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{email}")
	private ResponseEntity<?> getUser(@PathVariable String email){
		try {
			UserDto newUser = userService.getUser(email);
			return new ResponseEntity<UserDto>(newUser, HttpStatus.OK);
		}catch(Exception e) {
			MessageDto messageDto = new MessageDto(e.getMessage());
			return new ResponseEntity<MessageDto>(messageDto, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/{email}")
	private ResponseEntity<?> deleteUser(@PathVariable String email){
		try {
			 userService.deleteUser(email);
			return new ResponseEntity<String>(email, HttpStatus.OK);
		}catch(Exception e) {
			MessageDto messageDto = new MessageDto(e.getMessage());
			return new ResponseEntity<MessageDto>(messageDto, HttpStatus.BAD_REQUEST);
		}
	}
	
	private void validateRequest(UserRequest request) throws Exception {
	    String regexPattern = "^(.+)@(\\S+)$";
	    if (!Pattern.compile(regexPattern)
	      .matcher(request.getEmail())
	      .matches()) {
	    	throw new Exception("Correo Invalido");
	    }
	    String passwordRegExpr = "^(?=.*\\d).{5,}$";
	    if (!Pattern.compile(passwordRegExpr)
	      .matcher(request.getPassword())
	      .matches()) {
	    	throw new Exception("Password Invalido");
	    }
		
	}
}
