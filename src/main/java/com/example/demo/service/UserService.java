package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.shared.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto logInUser(UserDto user);
}
