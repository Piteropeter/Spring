package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="pizzas")
public class PizzaEntity implements Serializable {
	private static final long serialVersionUID = -2274368228324605213L;

	@Id
	@GeneratedValue
	private long id;
		
	@Column(nullable=false, length=50)
	private String name;
		
	@Column(nullable=false, length=255)
	private String ingredients;

	@Column(nullable=false)
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
