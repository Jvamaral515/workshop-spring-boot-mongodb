package com.jvamaral.workshopmongo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvamaral.workshopmongo.domain.Post;
import com.jvamaral.workshopmongo.domain.User;
import com.jvamaral.workshopmongo.dto.PostDTO;
import com.jvamaral.workshopmongo.dto.UserDTO;
import com.jvamaral.workshopmongo.repositories.UserRepository;
import com.jvamaral.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		List<User> list = repository.findAll();
		return list.stream().map(x-> new UserDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(String id) {
		User user = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO insert(UserDTO obj) {
		User user = new User();
		copyDtoToEntity(obj, user);
		user = repository.insert(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	@Transactional
	public UserDTO update(String id, UserDTO dto) {
		User entity = repository.findById((id)).get();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public List<PostDTO> findPosts(String id){
		User entity = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
		List<Post> list = entity.getPosts();
		return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
