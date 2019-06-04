package com.example.demo.shared;

import java.io.Serializable;

public class PizzaDto implements Serializable {
	private static final long serialVersionUID = 1497297925565809836L;

	private long id;
	private String name;
	private String ingredients;
	private double price;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
