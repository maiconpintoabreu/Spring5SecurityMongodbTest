package com.maiconspas.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Maicon Santana
 *
 */
public class User {

    @Id
	private final String id;
	private String name;
	private String userName;
	@JsonIgnore
	private String password;
	
	private Set<String> roles;
	public User() {
		super();
		id = UUID.randomUUID().toString();
	}

	public User(String name, String userName) {
		super();
		this.name = name;
		this.userName = userName;
		id = UUID.randomUUID().toString();
		roles = new HashSet<>();
		roles.add("USER");
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getRoles() {
		return roles;
	}
	
}
