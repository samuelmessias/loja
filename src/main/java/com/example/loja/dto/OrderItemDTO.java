package com.example.loja.dto;

import java.io.Serializable;

import com.example.loja.entities.OrderItem;


public class OrderItemDTO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantity;
	private Double price;
	private ProductDTO product;
	private OrderDTO order;
	
	public OrderItemDTO() {
		
	}	

	public OrderItemDTO(Long id, Integer quantity, Double price, ProductDTO product,  OrderDTO order) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.order = order;
	}
	
	public OrderItemDTO(OrderItem entity) {
		id = entity.getId();
		quantity = entity.getQuantity();
		price = entity.getPrice();
		product = new ProductDTO(entity.getProduct());
		//order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public Double getSubTotal() {
		return quantity * price;
	}
	

}
