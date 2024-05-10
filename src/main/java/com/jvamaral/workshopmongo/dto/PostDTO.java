package com.jvamaral.workshopmongo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jvamaral.workshopmongo.domain.Post;

public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private LocalDate date;
	private String title;
	private String body;
	private AuthorDTO author;
	private List<CommentDTO> comments = new ArrayList<>();
	
	public PostDTO() {
		
	}

	public PostDTO(String id, LocalDate date, String title, String body, AuthorDTO author) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}
	
	public PostDTO(Post obj) {
		id = obj.getId();
		date = obj.getDate();
		title = obj.getTitle();
		body = obj.getBody();
		author = obj.getAuthor();
		for (CommentDTO co : obj.getComments()) {
			comments.add(co);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}
}
