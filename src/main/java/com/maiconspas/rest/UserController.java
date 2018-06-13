package com.maiconspas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<User> userList = application.findUsers(limit,page);
		if(userList.isEmpty())
			return new ResponseEntity<List<User>>(userList,null,HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(userList,null,HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> deleteUsers(@PathVariable String id) {
		application.deleteUser(id);
		return new ResponseEntity<List<User>>(HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> getUser(@PathVariable String id) {
		User user = application.findUserById(id);
		if(user != null)
			return new ResponseEntity<User>(user,null,HttpStatus.OK);
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param id
	 * @param user
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		application.updateUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	/**
	 * @param user
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value = "/users/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> newUser(@RequestBody User user) {
		if(application.findUserById(user.getId()) != null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		application.createUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
