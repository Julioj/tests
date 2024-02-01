package com.example.test.models;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.example.test.models.requests.UserRequestPhone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="USER_SEQUENCE", allocationSize=1)
	private Long id;
	@Column(unique = true, updatable = false)
	private UUID uuid;
	private String name;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Phone> phones = new ArrayList<Phone>();
	private ZonedDateTime created;
	private ZonedDateTime modified;
	private ZonedDateTime last_login;
	private String token;
	private Boolean isactive;
	
	public User() {}
}
