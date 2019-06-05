package com.example.demo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.UserRepository;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.shared.UserDto;
import com.example.demo.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;

	
	@Override
	public UserDto createUser(UserDto user) {
		if(userRepository.findByEmail(user.getEmail()) != null) 
			throw new RuntimeException("Record already exists");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(user.getPassword());
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}
	
	@Override
	public UserDto logInUser(UserDto user) {
		String email = user.getEmail();
		String password = user.getPassword();
		UserEntity userEntity = new UserEntity();
		userEntity = userRepository.findByEmail(email);
		if(userEntity == null) 
			throw new RuntimeException("User does not exist");
		
		if(!(password.equals(userEntity.getEncryptedPassword())))
			throw new RuntimeException("Wrong password");
		
		Utils.setCurrentUserEmail(email);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
	}
	
}