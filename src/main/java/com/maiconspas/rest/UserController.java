package com.maiconspas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maiconspas.application.UserApplication;
import com.maiconspas.entity.User;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
	
	@Autowired
	private UserApplication application;

	/**
	 * Returns all users
	 * @return      Users
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/adminUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAdminUsers() {
	    return application.findUsers();
	}
	/**
	 * Returns all users
	 * @return      Users
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers() {
	    return application.findUsers();
	}
	
}
