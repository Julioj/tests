package com.example.test.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestPhone {

	private Long number;
	private Integer citycode;
	private Integer countrycode;
}
