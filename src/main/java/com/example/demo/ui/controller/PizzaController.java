package com.example.demo.ui.controller;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.PizzaEntity;
import com.example.demo.service.OrderService;
import com.example.demo.service.PizzaService;
import com.example.demo.shared.OrderDto;
import com.example.demo.shared.PizzaDto;
import com.example.demo.shared.UserDto;
import com.example.demo.shared.Utils;
import com.example.demo.ui.model.request.PizzaDetailsRequestModel;
import com.example.demo.ui.model.response.PizzaRest;
import com.example.demo.ui.model.response.UserRest;


@RestController
@RequestMapping("menu") // http://localhost:8080/menu
public class PizzaController {
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	Utils utils;
	
	@GetMapping
	public String getPizzaMenu() {
		ArrayList<PizzaEntity> pizzas = new ArrayList<PizzaEntity>();
		
		String menu = "_amount\">\n" + 
				"  <option value=\"0\">0</option>\n" + 
				"  <option value=\"1\">1</option>\n" + 
				"  <option value=\"2\">2</option>\n" + 
				"  <option value=\"3\">3</option>\n" + 
				"  <option value=\"4\">4</option>\n" + 
				"  <option value=\"5\">5</option>\n" + 
				"  <option value=\"6\">6</option>\n" + 
				"  <option value=\"7\">7</option>\n" + 
				"  <option value=\"8\">8</option>\n" + 
				"  <option value=\"9\">9</option>\n" + 
				"</select> ";
		
		pizzas = pizzaService.getPizzas();
		
		String site = "<h1><a href=\"http://localhost:8080/orders\">ZAMÓWIENIA</a></h1>";
		
		site = site + "<form><table style=\"border-color: black; float: left;\" border=\"black\" cellspacing=\"5\" cellpadding=\"5\"><tbody>";
		
		int i = 0;
		for(PizzaEntity x : pizzas) {
			site = site + "<tr><td>" + x.getName() + "</td><td>" + x.getIngredients() + "</td><td>" + x.getPrice() + "</td><td><select name=\"" + i + menu + "</td></tr>";
			i++;
		}
		site = site + "</tbody></table><hr>";
		
		site = site + "<p><button type=\"submit\" formmethod=\"post\">ZAMÓW!</button></p></form>";
//		site = site + "<p><button type=\"submit\">ZAMÓW!</button></p></form>";
		
		return site;
	}

	@PostMapping
	public RedirectView place_order(@RequestBody String cos) {
		String[] arguments = cos.split("&");
		int size = Array.getLength(arguments);
		
		ArrayList<String[]> values = new ArrayList<String[]>();
		for(int i = 0; i < size; i++) {
			values.add(arguments[i].split("="));
		}
		
		String result = "";
		
		for(int i = 0; i < size; i++) {
			result = result + values.get(i)[1];
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		OrderDto order = new OrderDto();
		
		if(utils.getCurrentUserEmail().equals("")) {
			order.setOrderClient("None");
		}
		else {
			order.setOrderClient(utils.getCurrentUserEmail());
		}
		order.setOrderContent(result);
		order.setOrderDate(dtf.format(now));
		
		ArrayList<PizzaEntity> pizzas = new ArrayList<PizzaEntity>();
		pizzas = pizzaService.getPizzas();
		
		double cost = 0;
		
		for(int i = 0; i < size; i++) {
			cost = cost + (pizzas.get(i).getPrice() * Double.parseDouble(values.get(i)[1]));
		}
		
		order.setCost(cost);
		
		orderService.createOrder(order);
		
		return new RedirectView("orders");
	}
	
	@PutMapping
	public PizzaRest addPizza(@RequestBody PizzaDetailsRequestModel pizza) {
		PizzaRest returnValue = new PizzaRest();
		
		PizzaDto pizzaDto = new PizzaDto();
		BeanUtils.copyProperties(pizza, pizzaDto);
		
		PizzaDto createdPizza = pizzaService.createPizza(pizzaDto);
		BeanUtils.copyProperties(createdPizza, returnValue);
		
		return returnValue;
	}
	
}
