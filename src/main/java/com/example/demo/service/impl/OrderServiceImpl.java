package com.example.demo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.shared.OrderDto;
import org.springframework.stereotype.Service;

import com.example.demo.OrderRepository;
import com.example.demo.PizzaRepository;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.PizzaEntity;
import com.example.demo.service.OrderService;
import com.example.demo.shared.PizzaDto;
import com.example.demo.shared.Utils;
import com.google.common.collect.Lists;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public OrderDto createOrder(OrderDto order) {
		OrderEntity orderEntity = new OrderEntity();
		BeanUtils.copyProperties(order, orderEntity);
		
//		String publicUserId = utils.generateUserId(30);
//		userEntity.setEncryptedPassword("test");
//		userEntity.setUserId(publicUserId);
		
//		if(userRepository == null) {
//			throw new InternalError("USER REPOSITORY IS NULL");
//		}
		
		OrderEntity storedOrderDetails = orderRepository.save(orderEntity);
		
		OrderDto returnValue = new OrderDto();
		BeanUtils.copyProperties(storedOrderDetails, returnValue);
		
		return returnValue;
	}
	
	@Override
	public ArrayList<OrderEntity> getOrders() {
		Iterable<OrderEntity> it = new ArrayList<>();
		
		it = orderRepository.findAll();
		ArrayList<OrderEntity> orderEntities = new ArrayList<>();

		orderEntities = Lists.newArrayList(it);
		
		return orderEntities;
	}

}
