package com.example.test.service;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.models.User;
import com.example.test.models.dtos.UserDto;
import com.example.test.models.requests.UserRequest;
import com.example.test.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDto createUser(UserRequest request) throws Exception {
		if(userRepository.findByEmail(request.getEmail()) != null) {
			throw new Exception("El correo ya registrado");
		}
		User newUser = new User(null, 
				UUID.randomUUID(),
				request.getName(),
				request.getEmail(),
				request.getPassword(),
				request.getUserPhones(),
				ZonedDateTime.now(),
				ZonedDateTime.now(),
				null, 
				UUID.randomUUID().toString(),
				true);
		return toUserDto(userRepository.save(newUser));
	}
	
	public UserDto updateUser(UserRequest request) throws Exception {
		User oldUser = findUser(request.getEmail());
		oldUser.setName(request.getName());
		oldUser.setPassword(request.getPassword());
		oldUser.setEmail(request.getEmail());
		oldUser.setPhones(request.getUserPhones());
		oldUser.setModified(ZonedDateTime.now());
		return toUserDto(userRepository.save(oldUser));
	}
	
	public UserDto getUser(String email) throws Exception {
		return toUserDto(findUser(email));
	}
	
	public Boolean logInUser(String email, String password) throws Exception {
		User maybeUser = findUser(email);
		if(maybeUser != null && maybeUser.getPassword().equals(password)) {
			maybeUser.setLast_login(ZonedDateTime.now());
			userRepository.save(maybeUser);
			return true;
		}
		return false;
	}
	
	public void deleteUser(String email) throws Exception {
		User oldUser = findUser(email);
		userRepository.delete(oldUser);
	}
	
	private User findUser(String email) throws Exception {
		User oldUser = userRepository.findByEmail(email);
		if(oldUser == null) {
			throw new Exception("Usuario no existe");
		}
		return oldUser;
	}
	
	private UserDto toUserDto(User newUser) {
		return new UserDto(newUser.getId(), 
				newUser.getUuid(),
				newUser.getCreated(), 
				newUser.getModified(),
				newUser.getLast_login(),
				newUser.getToken(),
				newUser.getIsactive());
	}
}
