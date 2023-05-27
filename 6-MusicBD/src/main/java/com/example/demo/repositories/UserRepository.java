package com.example.demo.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
	User findOneByUsernameOrEmail(String username, String email);
}
