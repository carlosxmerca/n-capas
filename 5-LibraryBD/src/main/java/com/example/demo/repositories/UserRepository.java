package com.example.demo.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
	// @Query("SELECT u.* FROM 'user' u WHERE u.username = :id OR u.email = :id")
    User findOneByUsernameOrEmail(String username, String email);
}
