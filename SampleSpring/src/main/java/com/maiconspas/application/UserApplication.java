package com.maiconspas.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maiconspas.entity.User;
import com.maiconspas.repository.UserRepository;

@Component
public class UserApplication {

	@Autowired
	private UserRepository repository;
	
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

}
