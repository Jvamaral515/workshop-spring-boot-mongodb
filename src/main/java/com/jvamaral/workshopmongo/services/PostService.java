package com.jvamaral.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvamaral.workshopmongo.domain.Post;
import com.jvamaral.workshopmongo.repositories.PostRepository;
import com.jvamaral.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	@Transactional(readOnly = true)
	public Post findById(String id) {
		Post post = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
		return post;
	}
	
	@Transactional(readOnly = true)
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
}
