package com.example.demo.ui.model.request;

public class OrderDetailRequestModel {
	private String orderContent;
	private String orderClient;
	private String orderDate;
	private double cost;
	
	
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	public String getOrderClient() {
		return orderClient;
	}
	public void setOrderClient(String orderClient) {
		this.orderClient = orderClient;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
