package com.maiconspas.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.maiconspas.entity.User;

/**
 * @author Maicon Santana
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findUsersById(String idUser);
    Page<User> findAll(Pageable pageable);
    List<User> findUsersByName(String name);
    Optional<User> findByUserName(String userName);
		
}
