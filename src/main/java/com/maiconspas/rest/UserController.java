package com.maiconspas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maiconspas.application.UserApplication;
import com.maiconspas.entity.User;

/**
 * @author Maicon Santana
 *
 */
@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

	@Autowired
	private UserApplication application;

	/**
	 * Returns all users
	 * 
	 * @return Users
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/adminUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAdminUsers() {
		return application.findUsers();
	}

	/**
	 * @param limit
	 * @param page
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUsersLimitedPage(Integer limit, Integer page) {
		if (limit == null) {
			return new ResponseEntity<List<User>>(application.findUsers(), null, HttpStatus.OK);
		}
		// TUDO:NOT_IMPLEMENTED
		return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * @param id
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> deleteUsers(String id) {
		// TUDO:NOT_IMPLEMENTED
		return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * @param id
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(String id) {
		// TUDO:NOT_IMPLEMENTED
		return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * @param id
	 * @param user
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> updateUser(String id, @RequestBody User user) {
		// TUDO:NOT_IMPLEMENTED
		return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * @param user
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> newUser(@RequestBody User user) {
		// TUDO:NOT_IMPLEMENTED
		return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
	}
}
