package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.shared.OrderDto;

import com.example.demo.entity.OrderEntity;

public interface OrderService {
	OrderDto createOrder(OrderDto order);
    ArrayList<OrderEntity> getOrders();
}
