package com.example.loja.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.example.loja.entities.Category;
import com.example.loja.entities.Product;


public class ProductDTO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;	
	
	private Set<CategoryDTO> categories = new HashSet<>();
	
	public ProductDTO() {
		
	}

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	
	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();		
		
	}
	
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);		
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

	public Set<CategoryDTO> getCategories() {
		return categories;
	}

}
