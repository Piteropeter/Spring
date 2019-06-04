package com.example.demo.ui.controller;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PizzaEntity;
import com.example.demo.service.PizzaService;
import com.example.demo.shared.PizzaDto;
import com.example.demo.shared.UserDto;
import com.example.demo.ui.model.request.PizzaDetailsRequestModel;
import com.example.demo.ui.model.response.PizzaRest;
import com.example.demo.ui.model.response.UserRest;


@RestController
@RequestMapping("menu") // http://localhost:8080/menu
public class PizzaController {
	@Autowired
	PizzaService pizzaService;
	
	@GetMapping
	public String get() {
		ArrayList<PizzaEntity> pizzas = new ArrayList<PizzaEntity>();
		
		String menu = " <select>\n" + 
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
		
		site = site + "<table style=\"border-color: black; float: left;\" border=\"black\" cellspacing=\"5\" cellpadding=\"5\"><tbody>";
		
		for(PizzaEntity x : pizzas) {
			site = site + "<tr><td>" + x.getName() + "</td><td>" + x.getIngredients() + "</td><td>" + menu + "</td></tr>";
		}
		site = site + "</tbody></table><hr>";
		
		site = site + "<p><button type=\"\\&quot;button\\&quot;\">ZAMÓW!</button></p>";
		
		return site;
	}

	@PostMapping
	public String order() {		
		return "MENU POST!";
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
