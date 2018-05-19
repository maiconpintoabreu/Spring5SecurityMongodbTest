package com.maiconspas.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maiconspas.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findUsersById(String idUser);
    List<User> findUsersByName(String name);
    Optional<User> findByUserName(String userName);
		
}
