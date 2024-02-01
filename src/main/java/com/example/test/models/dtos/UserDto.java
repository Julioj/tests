package com.example.test.models.dtos;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
	private Long id;
	private UUID uuid;
	private ZonedDateTime created;
	private ZonedDateTime modified;
	private ZonedDateTime last_login;
	private String token;
	private Boolean isactive;
}
