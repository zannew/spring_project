package com.aia.mvc.order.model;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

public class OrderCommand {

	// 변수명 일치시킴 (orderForm.jsp)
	// @ModelAttribute("")로 따로 이름 지정하지 않은 상태
	private List<OrderItem> orderItems;
	private Address address;
	
	
	public OrderCommand() {
		super();
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "OrderCommand [orderItems=" + orderItems + ", address=" + address + "]";
	}
	
	
	
	
}
