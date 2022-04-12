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

import com.example.loja.dto.OrderDTO;
import com.example.loja.entities.Order;
import com.example.loja.entities.User;
import com.example.loja.repositories.OrderRepository;
import com.example.loja.repositories.UserRepository;
import com.example.loja.services.exceptions.DataBaseException;
import com.example.loja.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<OrderDTO> findAll(Pageable pageable){
		Page<Order> lista = repository.findAll(pageable);
		return lista.map(x -> new OrderDTO(x, x.getItems()));
	}
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id){
		Optional<Order> obj = repository.findById(id);
		Order entity = obj.orElseThrow(() ->  new ResourceNotFoundException("Entidade não encontrada!"));
		return new OrderDTO(entity, entity.getItems());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		Order entity = new Order();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new OrderDTO(entity, entity.getItems());
	}
	
	@Transactional
	public OrderDTO update(Long id, OrderDTO dto){
		try {
			Order entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new OrderDTO(entity, entity.getItems());
			
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
	
	@Transactional(readOnly = true)
	private User getUser(Long id) {		
		Optional<User> obj = userRepository.findById(id);		
		User entity = obj.orElseThrow(() ->  new ResourceNotFoundException("Id usuário não encontrada: " + id));
		return entity;
	}
	
	private void copyDtoToEntity(OrderDTO dto, Order entity) {
		entity.setMoment(dto.getMoment());
		entity.setOrderStatus(dto.getOrderStatus());
		entity.setUser(getUser(dto.getUser().getId()));
	}
	
}
