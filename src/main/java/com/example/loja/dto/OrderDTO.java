package com.example.loja.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.example.loja.entities.Order;
import com.example.loja.entities.OrderItem;
import com.example.loja.entities.enums.OrderStatus;


public class OrderDTO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Instant moment;
	private OrderStatus orderStatus;	
	private UserDTO user;
	
	
	private Set<OrderItemDTO> items = new HashSet<>();

	public OrderDTO() {
		
	}

	public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, UserDTO user) {
		this.id = id;
		this.moment = moment;
		this.orderStatus = orderStatus;
		this.user = user;
	}
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		moment = entity.getMoment();
		orderStatus = entity.getOrderStatus();
		user = new UserDTO(entity.getUser());		
		
	}
	
	
	public OrderDTO(Order entity, Set<OrderItem> items) {
		id = entity.getId();
		moment = entity.getMoment();
		orderStatus = entity.getOrderStatus();
		user = new UserDTO(entity.getUser());
		
		items.forEach(i -> this.items.add(new OrderItemDTO(i)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<OrderItemDTO> getItems() {
		return items;
	}
	
	public double getTotal() {
		double total = 0;
		
		for(OrderItemDTO i : items) {
			total += i.getSubTotal();
		}
		
		return total;
	}

	
	

}
