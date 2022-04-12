package com.example.loja.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loja.dto.CategoryDTO;
import com.example.loja.dto.ProductDTO;
import com.example.loja.entities.Category;
import com.example.loja.entities.Product;
import com.example.loja.repositories.CategoryRepository;
import com.example.loja.repositories.ProductRepository;
import com.example.loja.services.exceptions.DataBaseException;
import com.example.loja.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable){
		Page<Product> lista = repository.findAll(pageable);
		return lista.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id){
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() ->  new ResourceNotFoundException("Entidade não encontrada!"));
		return new ProductDTO(entity, entity.getCategories());
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto){
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity, entity.getCategories());
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto){
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity, entity.getCategories());
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}	
		
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
		
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
		entity.getCategories().clear();
		
		for(CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}
	
}
