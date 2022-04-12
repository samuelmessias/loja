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

import com.example.loja.dto.UserDTO;
import com.example.loja.entities.User;
import com.example.loja.repositories.UserRepository;
import com.example.loja.services.exceptions.DataBaseException;
import com.example.loja.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable){
		Page<User> lista = repository.findAll(pageable);
		return lista.map(x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id){
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() ->  new ResourceNotFoundException("Entidade não encontrada!"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto){
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto){
		try {
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
			
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
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setPassword(dto.getPassword());
	}
	
}
