package com.example.test.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Phone {

	@Id
	private UUID id;
	private Long number;
	private Integer citycode;
	private Integer countrycode;
	
	public Phone() {}
}
