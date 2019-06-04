package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.PizzaEntity;
import com.example.demo.shared.PizzaDto;

public interface PizzaService {
	PizzaDto createPizza(PizzaDto pizza);
    ArrayList<PizzaEntity> getPizzas();
}
