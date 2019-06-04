package com.example.demo.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders") // http://localhost:8080/orders
public class OrderController {
//	@Autowired
//	OrdersService ordersService;
	
	@GetMapping
	public String get() {
		
		return "ORDERS GET!";
	}
}

