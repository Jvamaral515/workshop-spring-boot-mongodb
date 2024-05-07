package com.jvamaral.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvamaral.workshopmongo.domain.User;
import com.jvamaral.workshopmongo.repositories.UserRepository;
import com.jvamaral.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<User> findAll(){
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public User findById(String id) {
		User user = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
		return user;
	}
}
