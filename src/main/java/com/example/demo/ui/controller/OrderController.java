package com.example.demo.ui.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.PizzaEntity;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("orders") // http://localhost:8080/orders
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping
	public String get() {
		ArrayList<OrderEntity> orders = new ArrayList<OrderEntity>();
	
		orders = orderService.getOrders();
		
		String site = "<h1><a href=\"http://localhost:8080/menu\">MENU</a></h1>";
		
		site = site + "<form><table style=\"border-color: black; float: left;\" border=\"black\" cellspacing=\"5\" cellpadding=\"5\"><tbody>";
		
		for(OrderEntity x : orders) {
			site = site + "<tr><td>" + x.getOrderClient() + "</td><td>" + x.getOrderDate() + "</td><td>" + x.getOrderContent() + "</td><td>" + x.getCost() + "</td></tr>";
		}
		site = site + "</tbody></table><hr>";

		return site;

	}
}

