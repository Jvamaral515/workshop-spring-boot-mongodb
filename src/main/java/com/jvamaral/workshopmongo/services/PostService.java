package com.jvamaral.workshopmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvamaral.workshopmongo.domain.Post;
import com.jvamaral.workshopmongo.dto.PostDTO;
import com.jvamaral.workshopmongo.repositories.PostRepository;
import com.jvamaral.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	@Transactional(readOnly = true)
	public PostDTO findById(String id) {
		Post entity = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
		return new PostDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostDTO> findByTitle(String text){
		return repository.searchTitle(text);
	}
	
	@Transactional(readOnly = true)
	public List<PostDTO> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
