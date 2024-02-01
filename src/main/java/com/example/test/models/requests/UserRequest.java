package com.example.test.models.requests;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.test.models.Phone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	private String name;
	private String email;
	private String password;
	private List<UserRequestPhone> phones;
	
	public List<Phone> getUserPhones() {
		return phones.stream().map(p ->{
			return new Phone(UUID.randomUUID(),p.getNumber(), p.getCitycode(), p.getCountrycode());
		}).collect(Collectors.toList());
	}
}
