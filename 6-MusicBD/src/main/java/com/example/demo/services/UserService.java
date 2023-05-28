package com.example.demo.services;

import com.example.demo.models.dtos.users.RegisterUserDTO;
import com.example.demo.models.dtos.users.UpdateUserDTO;
import com.example.demo.models.entities.User;

import java.util.List;

public interface UserService {
    void save(RegisterUserDTO userInfo) throws Exception;
    void update(UpdateUserDTO userInfo) throws Exception;
    void deleteById(String code) throws Exception;
    User findOneById(String code);
    List<User> findAll();
}