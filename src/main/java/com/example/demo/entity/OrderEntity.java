package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="orders")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1078586792491815765L;
	
	@Id
	@GeneratedValue
	private long id;
		
	@Column(nullable=false, length=100)
	private String orderContent;
		
	@Column(nullable=false, length=32)
	private String orderDate;
	
	@Column(nullable=false, length=100)
	private String orderClient;

	@Column(nullable=false)
	private double cost;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderClient() {
		return orderClient;
	}

	public void setOrderClient(String orderClient) {
		this.orderClient = orderClient;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
