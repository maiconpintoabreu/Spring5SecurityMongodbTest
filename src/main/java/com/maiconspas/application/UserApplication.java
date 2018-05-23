package com.maiconspas.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.maiconspas.entity.User;
import com.maiconspas.repository.UserRepository;

/**
 * @author Maicon Santana
 *
 */
@Component
public class UserApplication {

	@Autowired
	private UserRepository repository;
	
	public List<User> findUsers(Integer limit, Integer page){
		Pageable pageable = PageRequest.of(page-1, limit);
		return repository.findAll(pageable).getContent();
	}
	public List<User> findUsers(){
		return repository.findAll();
	}
	public User findByUserName(String userName) {
		Optional<User> user = repository.findByUserName(userName);
		if(user.isPresent())
			return user.get();
		else
			return null;
	}
	public User updateUser(User user){
		return repository.save(user);
	}
	public void deleteUser(String id) {
		repository.deleteById(id);
	}
	public User findUserById(String id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent())
			return user.get();
		return null;
	}
	public void createUser(User user) {
		repository.save(user);
		
	}

}
