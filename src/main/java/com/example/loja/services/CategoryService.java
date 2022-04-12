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
import com.example.loja.entities.Category;
import com.example.loja.repositories.CategoryRepository;
import com.example.loja.services.exceptions.DataBaseException;
import com.example.loja.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAll(Pageable pageable){
		Page<Category> lista = repository.findAll(pageable);
		return lista.map(x -> new CategoryDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id){
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() ->  new ResourceNotFoundException("Entidade não encontrada!"));
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto){
		Category entity = new Category();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto){
		try {
			Category entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CategoryDTO(entity);
			
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
	
	private void copyDtoToEntity(CategoryDTO dto, Category entity) {
		entity.setName(dto.getName());		
	}
	
}
