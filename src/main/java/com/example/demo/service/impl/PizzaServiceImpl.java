package com.example.demo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.PizzaRepository;
import com.example.demo.UserRepository;
import com.example.demo.entity.PizzaEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.PizzaService;
import com.example.demo.shared.PizzaDto;
import com.example.demo.shared.UserDto;
import com.example.demo.shared.Utils;
import com.google.common.collect.Lists;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public PizzaDto createPizza(PizzaDto pizza) {
//		if(userRepository.findByEmail(user.getEmail()) != null) 
//			throw new RuntimeException("Record already exists");
		
		PizzaEntity pizzaEntity = new PizzaEntity();
		BeanUtils.copyProperties(pizza, pizzaEntity);
		
//		String publicUserId = utils.generateUserId(30);
//		userEntity.setEncryptedPassword("test");
//		userEntity.setUserId(publicUserId);
		
//		if(userRepository == null) {
//			throw new InternalError("USER REPOSITORY IS NULL");
//		}
		
		PizzaEntity storedPizzaDetails = pizzaRepository.save(pizzaEntity);
		
		PizzaDto returnValue = new PizzaDto();
		BeanUtils.copyProperties(storedPizzaDetails, returnValue);
		
		return returnValue;
	}
	
	@Override
	public ArrayList<PizzaEntity> getPizzas() {
		Iterable<PizzaEntity> it = new ArrayList<>();
		
		it = pizzaRepository.findAll();
		ArrayList<PizzaEntity> pizzasEntities = new ArrayList<>();

		pizzasEntities = Lists.newArrayList(it);
		
		
		return pizzasEntities;
	}

}
